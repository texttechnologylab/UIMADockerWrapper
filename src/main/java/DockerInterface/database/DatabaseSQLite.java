package DockerInterface.database;

import org.apache.uima.jcas.JCas;
import org.json.JSONObject;

import java.sql.*;

public class DatabaseSQLite implements DatabaseInterface {
    Connection _database;
    String _dbname;

    public DatabaseSQLite(String dbName) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        _database = DriverManager.getConnection("jdbc:sqlite:"+dbName);
        Statement state = _database.createStatement();
        String creat_table = "CREATE TABLE IF NOT EXISTS engine_storage(id INT AUTOINCREMENT PRIMARY KEY, hash INTEGER, description TEXT)";
        state.executeUpdate(creat_table);
        state.close();
        _dbname = dbName;
    }

    public long store(long hash, String value) throws Exception {
        PreparedStatement ps = _database
                .prepareStatement("INSERT INTO engine_storage VALUES (?,?);");
        ps.setLong(1,hash);
        ps.setString(2,value);
        ps.addBatch();
        return ps.getGeneratedKeys().getLong(1);
    }

    public String load(long id) throws Exception {
        String sql = "SELECT description FROM engine_storage WHERE id = ?";
        PreparedStatement stmt  = _database.prepareStatement(sql);
        stmt.setLong(1,id);
        ResultSet rs    = stmt.executeQuery(sql);

        while (rs.next()) {
            return rs.getString(2);
        }
        return null;
    }

    public void close() throws Exception {
        _database.close();
    }

    public String getClassName() {
        return this.getClassName();
    }

    public String toString() {
        JSONObject js = new JSONObject();
        js.put("dbName",_dbname);
        return js.toString();
    }
}
