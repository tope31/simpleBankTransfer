package interfaces;

import java.sql.SQLException;

public interface DBAccessInterface {
    Boolean usernameDBCheck(String username) throws SQLException;

    Integer retrieveUserId(String username) throws SQLException;

    String retrieveUserPass(Integer userId) throws SQLException;

    Integer retrieveUserBalance(Integer userId) throws SQLException;

    Integer retrieveUserTransactionCount(Integer userId) throws SQLException;
}
