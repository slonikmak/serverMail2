package db;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
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


        System.out.println(sessionDAO.getSessions());
        connection.close();
    }

}