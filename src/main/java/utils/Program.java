package utils;

import model.Users;

import java.sql.SQLException;

public class Program {
    public static void startProgram() throws SQLException {
        Users users = new Users();
        users.setUsername("chris");
        String username = users.getUsername();
        DBAccess dbAccess = new DBAccess();
        Boolean usernameExists = dbAccess.usernameDBCheck(username);

        if (usernameExists) {
            Integer retrieveUserId = dbAccess.retrieveUserId(username);
            users.setUserId(retrieveUserId);
            ShowUserMenu.userMenu(users);
        } else {
            System.out.println("Sorry, the username does not exist on the database.");
            System.out.println("Please contact the administrator");
        }
    }
}
