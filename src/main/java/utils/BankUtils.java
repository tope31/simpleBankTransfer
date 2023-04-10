package utils;

import dbConnection.DBConnection;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BankUtils {
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    private static Timestamp timestamp;

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
        Integer balance = users.getBalance();
        if (withdraw > balance) {
            System.out.println("The amount you wish to withdraw exceeds your current balance. Withdrawal of fund will not proceed, please try again.");
        } else {
            balance = users.getBalance() - withdraw;

            String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

            preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
            preparedStatement.setInt(1, balance);
            preparedStatement.setInt(2, users.getUserId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Withdraw Successful");
            }
        }
        return balance;
    }

    public static Integer transferMoney(Users users, Integer transactionMoney) throws SQLException {
        //TODO transferMoney logic here
        //TODO transactionCount here!
        Integer balance = users.getBalance();
        if (transactionMoney > balance) {
            System.out.println("The amount you wish to transfer exceeds your current balance. Transfer of fund will not proceed, please try again.");
            System.exit(0);
        } else {
            balance = users.getBalance() - transactionMoney;
            String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

            preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
            preparedStatement.setInt(1, balance);
            preparedStatement.setInt(2, users.getUserId());
            preparedStatement.executeUpdate();
        }
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

    public static Integer addTransactionCount(Users users, Integer numOfTransactions) throws SQLException {
        int transactionCount = users.getTransactionCount() + numOfTransactions;

        String sql = "UPDATE users SET num_of_transactions = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, transactionCount);
        preparedStatement.setInt(2, users.getUserId());
        preparedStatement.executeUpdate();

        return transactionCount;
    }
}
