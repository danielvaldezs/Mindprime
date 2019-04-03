package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	// Metodo para obtener conexion a la base de datos
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        String path = System.getProperty("user.dir");
        String SQLCONNECTION = "jdbc:sqlite:" + path +"/src/DatabaseConnection/MindPrimeDBCambios.db";

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLCONNECTION);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
