package utils;

import dbUtils.DBAccess;
import model.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {
    public static void startProgram() throws SQLException {
        //TODO add login feature
//        Scanner scanner = new Scanner(System.in);
        Users users = new Users();
        // uncomment for username verification
//        System.out.print("Please enter the username: ");
//        String user = scanner.next();
//        System.out.println("------------------");
        users.setUsername("deacon");
        String username = users.getUsername();
        DBAccess dbAccess = new DBAccess();
        boolean usernameExists = dbAccess.usernameDBCheck(username);

        if (usernameExists) {
            Integer retrievedUserId = dbAccess.retrieveUserId(username);
            String retrievedUserPass = dbAccess.retrieveUserPass(retrievedUserId);
            Integer retrievedUserBalance = dbAccess.retrieveUserBalance(retrievedUserId);
            Integer retrievedUserTransactionCount = dbAccess.retrieveUserTransactionCount(retrievedUserId);
            users.setUserId(retrievedUserId);
            users.setPassword(retrievedUserPass);
            users.setBalance(retrievedUserBalance);
            users.setTransactionCount(retrievedUserTransactionCount);
            ShowUserMenu.userMenu(users);
        } else {
            System.out.println("Sorry, the username does not exist on the database.");
            System.out.println("Please contact the administrator");
        }
    }
}
