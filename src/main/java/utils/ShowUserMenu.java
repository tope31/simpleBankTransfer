package utils;

import model.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class ShowUserMenu {

    public static void userMenu(Users users) throws SQLException {
        BankUtils bankUtils = new BankUtils();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        Integer balance;
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
                    System.out.println("Who do you want to send it to?");
                    String receivingUsername = scanner.nextLine();
                    DBAccess dbAccess = new DBAccess();
                    Boolean receivingUsernameExists = dbAccess.usernameDBCheck(receivingUsername);
                    if (receivingUsernameExists) {
                        Integer retrieveUserId = dbAccess.retrieveUserId(receivingUsername);
                        Integer retrieveUserBalance = dbAccess.retrieveUserBalance(retrieveUserId);
                        users.setUserId(retrieveUserId);
                        users.setBalance(retrieveUserBalance);
                    } else {
                        System.out.println("Sorry, the username does not exist on the database.");
                        System.out.println("Please contact the administrator");
                    }
                    break;
                case 4:
                    System.out.println("TBC");
                    break;
            }
        }
    }
}
