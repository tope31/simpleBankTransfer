package utils;

import dbConnection.DBConnection;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankUtils {
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static Integer depositBalance(Users users, Integer deposit) throws SQLException {
        Integer balance = users.getBalance() + deposit;

        String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        preparedStatement.setInt(2, users.getUserId());

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Deposit Successful");
        }
        return balance;
    }

    public static Integer withdrawBalance(Users users, Integer withdraw) throws SQLException {
        Integer balance = users.getBalance() - withdraw;

        String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        preparedStatement.setInt(2, users.getUserId());

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Withdraw Successful");
        }
        return balance;
    }

    public static Integer transferMoney(Users users, Integer transactionMoney) throws SQLException {
        //TODO transferMoney logic here
        int balance = users.getBalance() - transactionMoney;

        String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        preparedStatement.setInt(2, users.getUserId());
        preparedStatement.executeUpdate();

        return balance;
    }

    public static Integer receiveMoney(Users users, Integer receivingMoney) throws SQLException {
        int balance = users.getBalance() + receivingMoney;

        String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        preparedStatement.setInt(2, users.getUserId());
        preparedStatement.executeUpdate();

        return balance;
    }
}
