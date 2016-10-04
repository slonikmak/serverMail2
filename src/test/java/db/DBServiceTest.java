package db;

import model.Record;
import org.junit.Test;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class DBServiceTest {
    @Test
    public void getSqLiteConnection() throws Exception {
        DBService dbService = new DBService();
        Connection connection = dbService.getConnection();
        SessionDAO sessionDAO = new SessionDAO(connection);

        RecordDAO recordDAO = new RecordDAO(connection);
        //System.out.println(recordDAO.getRecordsCountBySession(5, Record.RecordType.ALL));
        System.out.println(recordDAO.getRecords(86, Record.RecordType.GPS));

        //System.out.println(sessionDAO.getSessions());
        connection.close();
    }



}