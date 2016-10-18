package db;

import db.executor.Executor;
import model.Record;
import model.Record.RecordType;
import model.RecordFull;
import model.Session;
import util.Utills;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public List<RecordFull> getRecordsBySession(long sessionId) throws SQLException {
        List<RecordFull> list = new ArrayList<>();
        executor.execQuery("select * from record where session_id="+sessionId, resultSet -> {
            while (resultSet.next()){
                RecordFull recordFull = new RecordFull();

                //Motion
                String[] value = Utills.prepareValue(resultSet.getString(3).split("__,__"));
                recordFull.setMotion1(Double.parseDouble(value[0]));
                recordFull.setMotion1(Double.parseDouble(value[1]));
                recordFull.setMotion1(Double.parseDouble(value[2]));
                recordFull.setMotion1(Double.parseDouble(value[3]));
                //ID
                recordFull.setId(resultSet.getLong(1));
                //Time
                recordFull.setTime(resultSet.getLong(4));
                //Date
                recordFull.setDate(resultSet.getString(5));
                //Session id
                recordFull.setSessionId(resultSet.getLong(6));

                //GPS
                resultSet.next();
                String[] valueGps = Utills.prepareValue(resultSet.getString(3).split("__,__"));
                recordFull.setLongtitude1(Double.parseDouble(valueGps[1]));
                recordFull.setLatitude1(Double.parseDouble(valueGps[0]));
                recordFull.setLongtitude2(Double.parseDouble(valueGps[3]));
                recordFull.setLatitude2(Double.parseDouble(valueGps[2]));

                //HULL
                resultSet.next();
                String[] valueHull = Utills.prepareValue(resultSet.getString(3).split("__,__"));
                recordFull.setHull1(Double.parseDouble(valueHull[0]));
                recordFull.setHull2(Double.parseDouble(valueHull[1]));
                recordFull.setHull3(Double.parseDouble(valueHull[2]));
                recordFull.setHull4(Double.parseDouble(valueHull[3]));

                //DEPTH
                resultSet.next();
                String[] valueDepth = Utills.prepareValue(resultSet.getString(3).split("__,__"));





                //list.add(new RecordFull(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6)));
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
            case GPS: query = String.format(query, sessionId, RecordType.GPS.name()) + " AND value != 'null__,__null__,__0.0__,__0.0'";
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

    public List<Record> getSelfGPSRecords(long sessionId) throws SQLException {
        List<Record> recordsList = getRecords(sessionId, RecordType.GPS);
        List<Record> result = new ArrayList<>();
        return recordsList.stream().filter(new Predicate<Record>() {
            @Override
            public boolean test(Record record) {
                String coordsString = record.getValue();
                String[] coordsArr = coordsString.split("__,__");
                return coordsArr[0].equals("") && !coordsArr[2].equals("");
            }
        }).collect(Collectors.toList());

    }

}
