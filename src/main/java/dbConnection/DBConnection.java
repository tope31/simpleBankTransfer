package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getDBConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/ivory_bank";
        String username = "root";
        String password = "MyP@ssword1";
        Connection conn = DriverManager.getConnection(dbURL, username, password);
        return conn;
    }
}
