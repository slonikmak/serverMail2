package db;

import db.executor.Executor;
import model.Record;
import model.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class RecordDAO {
    private Executor executor;

    public RecordDAO(Connection connection){

        this.executor = new Executor(connection);
    }

    public Record get(long id) throws SQLException {
        return executor.execQuery("select * from record where id=" + id, resultSet -> {
            resultSet.next();
            return new Record(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6));
        });
    }

    public List<Record> getRecordsBySession(long sessionId) throws SQLException {
        List<Record> list = new ArrayList<>();
        executor.execQuery("select * from record where session_id="+sessionId, resultSet -> {
            while (resultSet.next()){
                list.add(new Record(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6)));
            }
            return list;
        });
        return list;
    }
    public List<Record> getGpsCoordsBySession(long sessionId) throws SQLException {
        List<Record> list = new ArrayList<>();
        executor.execQuery("select * from record where session_id="+sessionId +" and name = GPS", resultSet -> {
            while (resultSet.next()){
                list.add(new Record(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6)));
            }
            return list;
        });
        return list;
    }
}
