package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static final String SQLCONNECTION = "jdbc:sqlite:D:\\Users\\Fiorella\\Desktop\\CETYS5\\DISENO SOFTWARE\\MindPrimePrueba\\src\\MindPrimeDB2.db";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLCONNECTION);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
