package org.hucompute.uimadockerwrapper.database;

import org.apache.uima.jcas.JCas;

import java.sql.SQLException;

public interface DatabaseInterface {
    long store(long hash, String value) throws Exception;
    String load(long id) throws Exception;
    void close() throws Exception;
    String toString();
    String getClassName();
}
