package sessionService;

import db.DBService;
import db.RecordDAO;
import db.SessionDAO;
import model.Record;
import model.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class SessionService {
    DBService dbService;
    SessionDAO sessionDAO;
    RecordDAO  recordDAO;

    public SessionService(){
        dbService = new DBService();
        sessionDAO = new SessionDAO(dbService.getConnection());
        recordDAO = new RecordDAO(dbService.getConnection());
    }

    public List<Session> getSessionsList() throws SQLException {
        return sessionDAO.getSessions();
    }
}
