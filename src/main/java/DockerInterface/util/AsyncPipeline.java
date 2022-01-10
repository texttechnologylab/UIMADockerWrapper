package DockerInterface.util;

import DockerInterface.DockerWrapper;
import DockerInterface.ScaleoutType;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.MetaDataObject;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

class Worker extends Thread {
    Vector<ConcurrentLinkedQueue<AnalysisEngine>> _flow;
    ConcurrentLinkedQueue<CAS> _instancesToBeLoaded;
    ArrayBlockingQueue<CAS> _loadedInstances;
    AtomicInteger _threadsAlive;
    AtomicBoolean _shutdown;
    Function<CAS,CAS> _callback;

    Worker(Vector<ConcurrentLinkedQueue<AnalysisEngine>> engineFlow, ConcurrentLinkedQueue<CAS> emptyInstance, ArrayBlockingQueue<CAS> loadedInstances, AtomicBoolean shutdown, AtomicInteger error, Function<CAS, CAS> callback) {
        super();
        _flow = engineFlow;
        _instancesToBeLoaded = emptyInstance;
        _loadedInstances = loadedInstances;
        _shutdown = shutdown;
        _threadsAlive = error;
        _callback = callback;
    }

    @Override
    public void run() {
        _threadsAlive.addAndGet(1);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        while(true) {
            CAS object = null;

            try {
                object = _loadedInstances.poll(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace(pw);
                _threadsAlive.addAndGet(-1);
                return;
            }

            if (object == null) {
                if (_shutdown.get() && _loadedInstances.size() == 0) {
                    _threadsAlive.addAndGet(-1);
                    return;
                }
                else {
                    continue;
                }
            }

            for (int i = 0; i < _flow.size(); i++) {
                AnalysisEngine engine = null;
                try {
                    while((engine = _flow.get(i).poll())==null){
                        Thread.yield();
                    }
                    engine.process(object);
                    _flow.get(i).add(engine);
                } catch (UIMAException e) {
                    _flow.get(i).add(engine);
                    System.exit(-1);
                }
            }
            this._callback.apply(object);
            object.reset();
            _instancesToBeLoaded.add(object);
        }
    }
}

public class AsyncPipeline {
    private static void addEngineToExecution(AnalysisEngineDescription engineDescription, Vector<ConcurrentLinkedQueue<AnalysisEngine>> _flow) throws ResourceInitializationException, InterruptedException {
        AnnotatorDescription desc = new AnnotatorDescription(engineDescription);
        Integer wrp = (Integer)desc.get_unlisted_parameter(DockerWrapper.PARAM_ASYNC_SCALEOUT_MAX_DEPLOYMENTS);
        String type = (String)desc.get_unlisted_parameter(DockerWrapper.PARAM_ASYNC_SCALEOUT_ASYNC_SCALEOUT_TYPE);
        ScaleoutType scaleoutType = ScaleoutType.DUPLICATED_ANNOTATOR;

        if(type==null && wrp != null) {
            throw new IllegalArgumentException("The annotator has a scaleout number but no scaleout type!");
        }
        else if(type!=null) {
            scaleoutType = ScaleoutType.valueOf(type);
        }

        int deployments = 1;
        if(wrp != null) {
            deployments = wrp;
        }

        ConcurrentLinkedQueue blck = new ConcurrentLinkedQueue();
        if(scaleoutType.equals(ScaleoutType.SHARED_ANNOTATOR)) {
            AnalysisEngine engine = AnalysisEngineFactory.createEngine(engineDescription);
            for (int i = 0; i < deployments; i++) {
                blck.add(engine);
            }
        }
        else {
            for (int i = 0; i < deployments; i++) {
                AnalysisEngine engine = AnalysisEngineFactory.createEngine(engineDescription);
                blck.add(engine);
            }
        }
        _flow.add(blck);
    }


    public static void run(CollectionReaderDescription rd, AnalysisEngineDescription eng, Function<CAS,CAS> callback) throws UIMAException, IOException, InterruptedException {
        Vector<ConcurrentLinkedQueue<AnalysisEngine>> _flow;
        ConcurrentLinkedQueue<CAS> _instancesToBeLoaded;
        ArrayBlockingQueue<CAS> _loadedInstances;

        AtomicInteger _threadsAlive;
        AtomicBoolean _shutdownFlag;
        int _max_concurrent;
        long _queue_size;
        CollectionReaderDescription _reader;


        _flow = new Vector<>();
        _reader = rd;
        _threadsAlive = new AtomicInteger(0);
        _shutdownFlag = new AtomicBoolean(false);
        if(eng.isPrimitive()) {
            addEngineToExecution(eng,_flow);
        }
        else {
            Map<String, ResourceSpecifier> spec = eng.getDelegateAnalysisEngineSpecifiers();
            for(String x : spec.keySet()) {
                ResourceSpecifier res = spec.get(x);
                if (res instanceof AnalysisEngineDescription) {
                    addEngineToExecution((AnalysisEngineDescription) res,_flow);
                }
            }
        }
        _max_concurrent = 1;
        for(ConcurrentLinkedQueue<AnalysisEngine> a : _flow) {
            if(a.size()>_max_concurrent) {
                _max_concurrent=a.size();
            }
        }
        if(_max_concurrent==1) {
            throw new IllegalArgumentException("Could not find a parralel annotator please use SimplePipeline.run(...) instead!");
        }

        _queue_size = Math.round(_max_concurrent * 1.5);

        _instancesToBeLoaded = new ConcurrentLinkedQueue<>();
        _loadedInstances = new ArrayBlockingQueue<CAS>((int)_queue_size);
        CollectionReader reader = CollectionReaderFactory.createReader(_reader);
        List<MetaDataObject> lst = new LinkedList<>();
        lst.add(reader.getMetaData());
        lst.add(eng.getMetaData());
        for(int i =0; i < _queue_size; i++) {
            final CAS cas = CasCreationUtils.createCas(lst, null, reader.getResourceManager());
            reader.typeSystemInit(cas.getTypeSystem());
            _instancesToBeLoaded.add(cas);
        }

        System.out.printf("Maximum scaleout is %d\n",_max_concurrent);


        _shutdownFlag.set(false);
        Thread []arr = new Thread[_max_concurrent];
        for(int i = 0; i < _max_concurrent; i++) {
            arr[i] = new Worker(_flow,_instancesToBeLoaded,_loadedInstances,_shutdownFlag,_threadsAlive,callback);
            arr[i].start();
        }

        while(reader.hasNext()) {
            CAS instance = _instancesToBeLoaded.poll();
            if(instance==null) {
                continue;
            }
            reader.getNext(instance);
            _loadedInstances.put(instance);
        }
        _shutdownFlag.set(true);

        while(_instancesToBeLoaded.size()!=_queue_size) {
            System.out.printf("Threads alive: %d\n",_threadsAlive.get());
            System.out.printf("Instances to be loaded: %d\n",_instancesToBeLoaded.size());
            System.out.printf("Instances to be loaded: %d\n",_loadedInstances.size());
            Thread.sleep(300);
        }
        for(int i = 0; i < _flow.size(); i++) {
            Iterator<AnalysisEngine> iter = _flow.get(i).iterator();
            while(iter.hasNext()) {
                AnalysisEngine e = iter.next();
                e.destroy();
            }
        }

    }
}
