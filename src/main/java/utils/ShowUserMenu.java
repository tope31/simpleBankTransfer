package utils;

import model.TransactionHistory;
import model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowUserMenu {

    public static void userMenu(Users users) throws SQLException {
        BankUtils bankUtils = new BankUtils();
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        ExcelExportUtil excelExportUtil = new ExcelExportUtil();
        TransactionHistory transactionHistory = new TransactionHistory();
        DBAccess dbAccess = new DBAccess();
        Scanner scanner = new Scanner(System.in);
        Integer choice = 0;
        Integer balance;
        Integer userId;
        Integer transactionCount;
        System.out.println("Hi " + users.getUsername());
        System.out.println("Welcome to KalemBank");


        while (choice <= 4) {
            System.out.println("------------------");
            System.out.println("Your current balance is: " + users.getBalance());
            System.out.println("Enter 1 to Deposit\nEnter 2 to Withdraw\nEnter 3 to Transfer Money\nEnter 4 to View Transfer History\nEnter 5 to Export Transfer History");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter amount to deposit");
                    Integer deposit = scanner.nextInt();
                    balance = bankUtils.depositBalance(users, deposit);
                    users.setBalance(balance);
                    break;
                case 2:
                    //TODO Withdrawal Limit = 20_000
                    //TODO Balance Limit = 100_000
                    System.out.println("Enter amount to withdraw");
                    Integer withdraw = scanner.nextInt();
                    balance = bankUtils.withdrawBalance(users, withdraw);
                    users.setBalance(balance);
                    break;
                case 3:
                    //TODO exception handling
                    System.out.println("Who do you want to send it to?");
                    String receivingUsername = scanner.nextLine();
                    Users receivingAccount = new Users();
                    String date = dateTimeUtil.getCurrentDate();
                    String time = dateTimeUtil.getCurrentTime();
                    transactionCount = 0;
                    Boolean receivingUsernameExists = dbAccess.usernameDBCheck(receivingUsername);
                    if (receivingUsernameExists) {
                        //retrieving currentAccount Values
                        Integer retrievedReceivingUserId = dbAccess.retrieveUserId(receivingUsername);
                        String retrievedReceivingUserPass = dbAccess.retrieveUserPass(retrievedReceivingUserId);
                        Integer retrievedReceivingUserBalance = dbAccess.retrieveUserBalance(retrievedReceivingUserId);
                        Integer retrievedReceivingTransactionCount = dbAccess.retrieveUserTransactionCount(retrievedReceivingUserId);
                        System.out.println("How much amount?");
                        Integer sendingAmount = scanner.nextInt();
                        scanner.nextLine();

                        //setting receivingAccount Values
                        receivingAccount.setUserId(retrievedReceivingUserId);
                        receivingAccount.setUsername(receivingUsername);
                        receivingAccount.setPassword(retrievedReceivingUserPass);
                        receivingAccount.setBalance(retrievedReceivingUserBalance);
                        receivingAccount.setTransactionCount(retrievedReceivingTransactionCount);


                        balance = bankUtils.transferMoney(users, sendingAmount);

                        users.setBalance(balance);

                        Integer receivingAccountBalance = bankUtils.receiveMoney(receivingAccount, sendingAmount);
                        receivingAccount.setBalance(receivingAccountBalance);
                        transactionCount++;

                        bankUtils.addTransactionCount(users, transactionCount);
                        System.out.println("Transfer Successful");

                        //setting values on transaction history
                        transactionHistory.setSenderId(users.getUserId());
                        transactionHistory.setReceiverId(retrievedReceivingUserId);
                        transactionHistory.setAmount(sendingAmount);
                        transactionHistory.setDate(date);
                        transactionHistory.setTime(time);
                        bankUtils.addTransactionHistory(transactionHistory);
                    } else {
                        System.out.println("There is no registered username in the database. Please try again");
                    }
                    break;
                case 4:
                    userId = users.getUserId();
                    transactionCount = dbAccess.retrieveUserTransactionCount(userId);
                    if (transactionCount == 0) {
                        System.out.println("You have made no transactions right now");
                    } else if (transactionCount == 1) {
                        System.out.println("You have made " + transactionCount + " transaction in total.");
                    } else {
                        System.out.println("You have made " + transactionCount + " transactions in total.");
                    }
                    bankUtils.printTransactionHistory(userId);
                    break;
                case 5:
                    userId = users.getUserId();
                    try {
                        excelExportUtil.exportToExcel(userId);
                        System.out.println("Exported Successfully!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }
}
