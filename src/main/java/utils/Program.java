package utils;

import model.Users;

import java.sql.SQLException;

public class Program {
    public static void startProgram() throws SQLException {
        Users users = new Users();
        users.setUsername("kei");
        String username = users.getUsername();
        DBAccess dbAccess = new DBAccess();
        Boolean usernameExists = dbAccess.usernameDBCheck(username);

        if (usernameExists) {
            Integer retrieveUserId = dbAccess.retrieveUserId(username);
            String retrieveUserPass = dbAccess.retrieveUserPass(retrieveUserId);
            Integer retrieveUserBalance = dbAccess.retrieveUserBalance(retrieveUserId);
            users.setUserId(retrieveUserId);
            users.setPassword(retrieveUserPass);
            users.setBalance(retrieveUserBalance);
            ShowUserMenu.userMenu(users);
        } else {
            System.out.println("Sorry, the username does not exist on the database.");
            System.out.println("Please contact the administrator");
        }
    }
}
