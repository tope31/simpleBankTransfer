package utils;

import model.Users;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ShowUserMenu {

    public static void userMenu(Users users) throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm a");

//        System.out.println(localDate);
//        System.out.println(localTime);
//        DateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");


//        TransactionHistory transactionHistory = new TransactionHistory();
//        transactionHistory.setTime(localTime.toString());
        BankUtils bankUtils = new BankUtils();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        Integer balance;
        Integer transactionCount;
//        timestamp.getTime();
        System.out.println("Hi " + users.getUsername());
        System.out.println("Welcome to Ivory Bank");


        while (choice <= 4) {
            System.out.println("------------------");
            System.out.println("Your current balance is: " + users.getBalance());
            System.out.println("Enter 1 to Deposit\nEnter 2 to Withdraw\nEnter 3 to Transfer Money\nEnter 4 to View Transaction History");
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
                    System.out.println("Enter amount to withdraw");
                    Integer withdraw = scanner.nextInt();
                    balance = bankUtils.withdrawBalance(users, withdraw);
                    users.setBalance(balance);
                    break;
                case 3:
                    //TODO exception handling
                    System.out.println("Who do you want to send it to?");
                    String receivingUsername = scanner.nextLine();
                    DBAccess dbAccess = new DBAccess();
                    Users receivingAccount = new Users();
                    transactionCount = 0;
                    Boolean receivingUsernameExists = dbAccess.usernameDBCheck(receivingUsername);
                    if (receivingUsernameExists) {
                        Integer retrievedReceivingUserId = dbAccess.retrieveUserId(receivingUsername);
                        String retrievedReceivingUserPass = dbAccess.retrieveUserPass(retrievedReceivingUserId);
                        Integer retrievedReceivingUserBalance = dbAccess.retrieveUserBalance(retrievedReceivingUserId);
                        Integer retrievedReceivingTransactionCount = dbAccess.retrieveUserTransactionCount(retrievedReceivingUserId);
                        System.out.println("How much amount?");
                        Integer sendingAmount = scanner.nextInt();
                        scanner.nextLine();

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
                        users.setTransactionCount(transactionCount);
                        System.out.println("Transfer Successful");
                    } else {
                        System.out.println("There is no registered username in the database. Please try again");
                    }
                    break;
                case 4:
                    transactionCount = users.getTransactionCount();
                    if (transactionCount == 0) {
                        System.out.println("You have made no transactions right now");
                    } else if (transactionCount == 1) {
                        System.out.println("You have made " + transactionCount + " transaction in total.");
                    } else {
                        System.out.println("You have made " + transactionCount + " transactions in total.");
                    }
                    break;
            }
        }
    }
}
