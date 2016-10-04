package db;

import db.executor.Executor;
import model.Record;
import model.Record.RecordType;
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
    public long getRecordsCountBySession(long sessionId, RecordType type) throws SQLException {
        long count = 0;
        return executor.execQuery("SELECT * FROM record WHERE session_id ="+sessionId, resultSet -> {
            long size = 0;
            while (resultSet.next()){
                size++;
            }
            return size;
        });
    }

    public List<Record> getRecords(long sessionId, RecordType type) throws SQLException {

        String query = "SELECT * FROM record WHERE session_id = %d AND name = '%s'";
        switch (type) {
            case  ALL: query = "SELECT * FROM record WHERE session_id ="+sessionId;
                break;
            case GPS: query = String.format(query, sessionId, RecordType.GPS.name()) + " AND value != '__,__'";
                break;
        }

        System.out.println(query);

        return executor.execQuery(query, resultSet -> {
            List<Record> records = new ArrayList<>();
            while (resultSet.next()){
               records.add(new Record(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6)));
            }
            return records;
        });
    }
}
