package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String BD = "rolgame";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";  // "admin"; //

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL+BD, USER, PASSWORD);
    }
}
