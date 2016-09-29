package accountService;

import db.DBException;
import db.DBService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton on 23.09.2016.
 */
public class AccountService {
    private Map<String, String> users;
    private DBService dbService;

    public AccountService(){

        users = new HashMap<>();
        dbService = new DBService();

    }

    public boolean isRegistred(String login) throws DBException {
        long id = dbService.getUserByLogin(login);
        //return users.containsKey(login);
        //System.out.println(id);
        if (id<0) return false;
        else return true;
    }

    public void add(String log, String pass) throws DBException {
        dbService.addUser(log, pass);
        //users.put(log, pass);
    }
    public void closeConn() throws SQLException {
        dbService.closeConnection();
    }
}
