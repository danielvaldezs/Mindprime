package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    public static Connection getConnection() {
        Connection conn = null;
        String path = System.getProperty("user.dir");
        String SQLCONNECTION = "jdbc:sqlite:" + path + "/src/DatabaseConnection/MindPrimeDBCambios.db";

        try {

//            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(SQLCONNECTION);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException ex) {
            System.out.println("Hubo un error al conectarse a la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }
        return conn;
    }
}
