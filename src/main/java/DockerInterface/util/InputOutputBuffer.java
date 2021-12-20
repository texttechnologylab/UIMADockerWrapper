package DockerInterface.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static com.google.common.primitives.Ints.min;


class SizedOutputStream extends OutputStream {
    InputOutputBuffer _out;
    SizedOutputStream(InputOutputBuffer out) {
        _out = out;
    }
    public void close() {}
    public void flush() {}

    private int get_needed_size(int atleast, int current) {
        while(atleast>current) {
            current<<=1;
        }
        return current;
    }
    public void write(byte[] bytes) {
        if(bytes.length+_out._writeptr > _out._arr.length) {
            int newsize = get_needed_size(bytes.length+_out._writeptr,_out._arr.length);
            byte[] newarr = new byte[newsize];
            System.arraycopy(_out._arr,0,newarr,0,_out._arr.length);
            _out._arr = newarr;
        }
        System.arraycopy(bytes,0,_out._arr,_out._writeptr,bytes.length);
        _out._writeptr+=bytes.length;
    }

    public void write(byte[] bytes, int off, int length) {
        if(length+_out._writeptr > _out._arr.length) {
            int newsize = get_needed_size(length+_out._writeptr,_out._arr.length);
            byte[] newarr = new byte[newsize];
            System.arraycopy(_out._arr,0,newarr,0,_out._arr.length);
            _out._arr = newarr;
        }
        System.arraycopy(bytes,off,_out._arr,_out._writeptr,length);
        _out._writeptr+=length;
    }

    public void write(int by) {
        if(1+_out._writeptr > _out._arr.length) {
            int newsize = get_needed_size(1+_out._writeptr,_out._arr.length);
            byte[] newarr = new byte[newsize];
            System.arraycopy(_out._arr,0,newarr,0,_out._arr.length);
            _out._arr = newarr;
        }
        _out._arr[_out._writeptr] = (byte)by;
        _out._writeptr+=1;
    }
}

class SizedInputStream extends InputStream {
    InputOutputBuffer _out;
    int _readsize;

    SizedInputStream(InputOutputBuffer out, int readsize) {
        _out = out;
        _readsize = readsize;
    }

    public int available() {
        return _readsize-_out._readptr;
    }
    public void close() {}

    public boolean markSupported() {
        return false;
    }

    public int read() {
        if(_out._readptr >= _readsize) {
            return -1;
        }
        _out._readptr+=1;
        return _out._arr[_out._readptr-1];
    }

    public int read(byte[] b) {
        if(b.length==0) {
            return 0;
        }
        int read = min(_readsize-_out._readptr,b.length);
        if(read<=0) {
            return -1;
        }
        System.arraycopy(_out._arr,_out._readptr,b,0,read);
        _out._readptr+=read;
        return read;
    }

    public int read(byte[] b,int off, int length) {
        if(length==0) {
            return 0;
        }
        int read = min(_readsize-_out._readptr,length);
        if(read<=0) {
            return -1;
        }
        System.arraycopy(_out._arr,_out._readptr,b,0,read);
        _out._readptr+=read;
        return read;
    }

    public long skip(long n) {
        if(_readsize <= _out._readptr) {
            return -1;
        }
        _out._readptr+=n;
        if(_out._readptr>= _readsize) {
            return _readsize-(_out._readptr-n);
        }
        return n;
    }
}


public class InputOutputBuffer {
    public byte[] _arr;
    public int _readptr;
    public int _writeptr;

    public InputOutputBuffer() {
        _arr = new byte[4096];
        _readptr = 0;
        _writeptr = 0;
    }

    public void reset() {
        _writeptr = 0;
        _readptr = 0;
    }

    public void atleast(int size) {
        if(_arr.length < size) {
            _arr = new byte[size];
        }
    }

    public OutputStream getOutputStream() {
        return new SizedOutputStream(this);
    }

    public InputStream getSizedInputStream(int size) {
        return new SizedInputStream(this,size);
    }
}