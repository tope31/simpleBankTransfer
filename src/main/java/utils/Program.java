package utils;

import model.Users;

import java.sql.SQLException;

public class Program {
    public static void startProgram() throws SQLException {
        Users users = new Users();
        users.setUsername("deacon");
        String username = users.getUsername();
        DBAccess dbAccess = new DBAccess();
        Boolean usernameExists = dbAccess.usernameDBCheck(username);

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
