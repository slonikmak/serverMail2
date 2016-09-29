package db;

import model.User;
import db.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Anton on 23.09.2016.
 */
public class UserDAO {
    private Executor executor;

    public UserDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public User get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, resultSet -> {
            resultSet.next();
            return new User(resultSet.getString(2), resultSet.getString(3));
        });
    }

    public long getUserId(String login) throws SQLException {
        return executor.execQuery("select * from users where login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String login, String password) throws SQLException {
        executor.execUpdate("insert into users (login, password) values ('" + login + "','"+password+"' )");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256),password varchar(110), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
