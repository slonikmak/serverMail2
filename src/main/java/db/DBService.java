package db;

import context.Contex;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Anton on 24.09.2016.
 */
public class DBService {
    private Connection connection;
    private UserDAO userDAO;

    public DBService(){
        this.connection = getSqLiteConnection();
        userDAO = new UserDAO(this.connection);

    }

    public User getUser(long id) throws DBException {
        try {
            return userDAO.get(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long getUserByLogin(String login) throws DBException {
        try {
            return userDAO.getUserId(login);
        } catch (SQLException e) {
            //throw new DBException(e);
            return -1;
        }
    }

    public long addUser(String login, String password) throws DBException {
        try {
            connection.setAutoCommit(false);

            userDAO.createTable();
            userDAO.insertUser(login, password);
            connection.commit();
            return userDAO.getUserId(login);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void cleanUp() throws DBException {

        try {
            userDAO.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }





    public static Connection getH2Connection() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:./test","test", "test");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public static Connection getSqLiteConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ Contex.getDBPath());
            System.out.println("Connection open");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
