package interfaces;

import java.sql.SQLException;

public interface DBAccessInterface {
    Boolean usernameDBCheck(String username) throws SQLException;

    Integer retrieveUserId(String username) throws SQLException;
}
