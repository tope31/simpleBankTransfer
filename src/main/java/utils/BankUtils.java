package utils;

import dbConnection.DBConnection;
import model.TransactionHistory;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankUtils {
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public Integer depositBalance(Users users, Integer deposit) throws SQLException {
        Integer balance = users.getBalance() + deposit;
        String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        preparedStatement.setInt(2, users.getUserId());

        Integer rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Deposit Successful");
        }
        return balance;
    }

    public Integer withdrawBalance(Users users, Integer withdraw) throws SQLException {
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

    public Integer transferMoney(Users users, Integer transactionMoney) throws SQLException {
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


    public Integer receiveMoney(Users users, Integer receivingMoney) throws SQLException {
        Integer balance = users.getBalance() + receivingMoney;

        String sql = "UPDATE users SET balance = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        preparedStatement.setInt(2, users.getUserId());
        preparedStatement.executeUpdate();

        return balance;
    }

    public Integer addTransactionCount(Users users, Integer numOfTransactions) throws SQLException {
        Integer transactionCount = users.getTransactionCount() + numOfTransactions;

        String sql = "UPDATE users SET num_of_transactions = ? WHERE user_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, transactionCount);
        preparedStatement.setInt(2, users.getUserId());
        preparedStatement.executeUpdate();

        return transactionCount;
    }

    public void addTransactionHistory(TransactionHistory transactionHistory) throws SQLException {
        Integer senderId = transactionHistory.getSenderId();
        Integer receiverId = transactionHistory.getReceiverId();
        Integer amount = transactionHistory.getAmount();
        String date = transactionHistory.getDate();
        String time = transactionHistory.getTime();

        String sql = "INSERT INTO transaction_history (sender_id,receiver_id,amount,date,time) VALUES (?,?,?,?,?)";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, senderId);
        preparedStatement.setInt(2, receiverId);
        preparedStatement.setInt(3, amount);
        preparedStatement.setString(4, date);
        preparedStatement.setString(5, time);
        preparedStatement.executeUpdate();
    }

    public void printTransactionHistory(Integer userId) throws SQLException {

        String sql = "SELECT * FROM transaction_history WHERE sender_id = ?";

        preparedStatement = DBConnection.getDBConnection().prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer receiverId = resultSet.getInt("receiver_id");
            Integer amount = resultSet.getInt("amount");
            String date = resultSet.getString("date");
            String time = resultSet.getString("time");
            System.out.println("UID: " + receiverId + " Amount: " + amount + " Date: " + date + " Time: " + time);
        }
    }
}
