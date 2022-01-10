package org.hucompute.uimadockerwrapper.util;

import org.hucompute.uimadockerwrapper.DockerWrappedEnvironment;

import java.util.Iterator;
import java.util.List;

public class DockerWrappedEnvironmentIterator implements Iterator {
    List<DockerWrappedEnvironment> _environments;
    int _index;

    DockerWrappedEnvironmentIterator(List<DockerWrappedEnvironment> envs) {
        _environments = envs;
        _index = 0;
    }

    public boolean hasNext() {
        return _index < _environments.size();
    }

    public DockerWrappedEnvironment next() {
        _index+=1;
        if(_index>_environments.size()) {
            return null;
        }
        return _environments.get(_index-1);
    }


    public void remove() {
        _environments.remove(_index);
    }
}
