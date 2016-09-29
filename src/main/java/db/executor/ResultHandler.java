package db.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anton on 24.09.2016.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
