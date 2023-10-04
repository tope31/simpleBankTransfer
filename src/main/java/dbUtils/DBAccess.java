package dbUtils;

import dbConnection.DBConnection;
import interfaces.DBAccessInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAccess implements DBAccessInterface {
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    @Override
    public Boolean usernameDBCheck(String username) throws SQLException {
        String sql = "SELECT username FROM users WHERE username = ?";
        boolean IsUsernameValid = false;

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            resultSet.getString(1);
            IsUsernameValid = true;
        }
        return IsUsernameValid;
    }

    @Override
    public Integer retrieveUserId(String username) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        int userId = 0;

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            userId = resultSet.getInt(1);
        }
        return userId;
    }

    @Override
    public String retrieveUserPass(Integer userId) throws SQLException {
        String sql = "SELECT password FROM users WHERE user_id = ?";
        String password = "";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            password = resultSet.getString(1);
        }
        return password;
    }

    @Override
    public Integer retrieveUserBalance(Integer userId) throws SQLException {
        String sql = "SELECT balance FROM users WHERE user_id = ?";
        int balance = 0;

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            balance = resultSet.getInt(1);
        }
        return balance;
    }

    @Override
    public Integer retrieveUserTransactionCount(Integer userId) throws SQLException {
        int transactionCount = 0;

        String sql = "SELECT num_of_transactions FROM users WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            transactionCount = resultSet.getInt(1);
        }
        return transactionCount;
    }
}
