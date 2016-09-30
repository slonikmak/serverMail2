package db;

import db.executor.Executor;
import model.Session;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class SessionDAO {
    private Executor executor;

    public SessionDAO(Connection connection){

        this.executor = new Executor(connection);
    }

    public Session get(long id) throws SQLException {
        return executor.execQuery("select * from session where id=" + id, resultSet -> {
            resultSet.next();
            return new Session(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5));
        });
    }

    public List<Session> getSessions() throws SQLException {
        List<Session> list = new ArrayList<>();
        executor.execQuery("select * from session", resultSet -> {
            while (resultSet.next()){
                list.add(new Session(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5)));
            }
            return list;

        });
        return list;
    }
}
