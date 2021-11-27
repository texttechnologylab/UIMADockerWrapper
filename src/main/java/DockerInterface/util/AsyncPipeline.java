package DockerInterface.util;

import DockerInterface.DockerWrapper;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

class Worker extends Thread {
    Vector<ArrayBlockingQueue<AnalysisEngine>> _flow;
    ArrayBlockingQueue<JCas> _instancesToBeLoaded;
    ArrayBlockingQueue<JCas> _loadedInstances;
    AtomicBoolean _shutdown;

    Worker(Vector<ArrayBlockingQueue<AnalysisEngine>> engineFlow, ArrayBlockingQueue<JCas> emptyInstance, ArrayBlockingQueue<JCas> loadedInstances, AtomicBoolean shutdown) {
        super();
        _flow = engineFlow;
        _instancesToBeLoaded = emptyInstance;
        _loadedInstances = loadedInstances;
        _shutdown = shutdown;
    }

    @Override
    public void run() {
        while(true) {
            int in_section = 0;
            JCas object = null;
            try {
                object = _loadedInstances.poll(400, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            if (object == null && _shutdown.get()) {
                if (_loadedInstances.size() == 0) {
                    return;
                }
            }


            for (int i = 0; i < _flow.size(); i++) {
                try {
                    AnalysisEngine engine = _flow.get(i).poll(100, TimeUnit.HOURS);
                    engine.process(object);
                    _flow.get(i).put(engine);
                } catch (InterruptedException | AnalysisEngineProcessException e) {
                    e.printStackTrace();
                    return;
                }
            }
            object.reset();
            try {
                _instancesToBeLoaded.put(object);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}

public class AsyncPipeline {
    CollectionReader _reader;
    Vector<ArrayBlockingQueue<AnalysisEngine>> _flow;
    ArrayBlockingQueue<JCas> _instancesToBeLoaded;
    ArrayBlockingQueue<JCas> _loadedInstances;
    AtomicBoolean _shutdownFlag;
    int _max_concurrent;

    private void addEngineToExecution(AnalysisEngineDescription engineDescription) throws ResourceInitializationException, InterruptedException {
        AnnotatorDescription desc = new AnnotatorDescription(engineDescription);
        AnnotatorParameterWrapper wrp = desc.get_parameter(DockerWrapper.PARAM_ASYNC_SCALEOUT_MAX_DEPLOYMENTS);
        int deployments = 1;
        if(wrp != null) {
            deployments = (int)wrp.get_value();
        }
        AnalysisEngine engine = AnalysisEngineFactory.createEngine(engineDescription);
        ArrayBlockingQueue blck = new ArrayBlockingQueue(deployments);
        for(int i = 0; i < deployments; i++) {
            blck.put(engine);
        }
        _flow.add(blck);
    }

    public AsyncPipeline(CollectionReaderDescription rd, AnalysisEngineDescription eng) throws UIMAException, InterruptedException {
        _reader = CollectionReaderFactory.createReader(rd);
        _flow = new Vector<>();
        _shutdownFlag = new AtomicBoolean(false);
        if(eng.isPrimitive()) {
            addEngineToExecution(eng);
        }
        else {
            Map<String, ResourceSpecifier> spec = eng.getDelegateAnalysisEngineSpecifiers();
            for(String x : spec.keySet()) {
                ResourceSpecifier res = spec.get(x);
                if (res instanceof AnalysisEngineDescription) {
                    addEngineToExecution((AnalysisEngineDescription) res);
                }
            }
        }
        _max_concurrent = 1;
        for(ArrayBlockingQueue<AnalysisEngine> a : _flow) {
            if(a.size()>_max_concurrent) {
                _max_concurrent=a.size();
            }
        }
        if(_max_concurrent==1) {
            throw new IllegalArgumentException("Could not find a parralel annotator please use SimplePipeline.run(...) instead!");
        }

        _instancesToBeLoaded = new ArrayBlockingQueue<>(_max_concurrent*2);
        _loadedInstances = new ArrayBlockingQueue<>(_max_concurrent*2);
        for(int i =0; i < _max_concurrent*2; i++) {
            _instancesToBeLoaded.put(JCasFactory.createJCas());
        }
        System.out.printf("Maximum scaleout is %d\n",_max_concurrent);
    }

    public void run() throws ResourceInitializationException, CollectionException, IOException, InterruptedException {
        _shutdownFlag.set(false);
        Thread []arr = new Thread[_max_concurrent];
        for(int i = 0; i < _max_concurrent; i++) {
            arr[i] = new Worker(_flow,_instancesToBeLoaded,_loadedInstances,_shutdownFlag);
            arr[i].start();
        }

        while(_reader.hasNext()) {
            JCas instance = _instancesToBeLoaded.poll(1800, TimeUnit.SECONDS);
            _reader.getNext(instance.getCas());
            _loadedInstances.put(instance);
        }
        _shutdownFlag.set(true);

        while(_instancesToBeLoaded.size()!=_max_concurrent*2) {
            Thread.sleep(300);
        }
    }
}
