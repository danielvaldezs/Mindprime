package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	// Metodo para obtener la conexion a la base de datos
    public static Connection getConnection() {
    	
        Connection conn = null;
        String path = System.getProperty("user.dir");
        String SQLCONNECTION = "jdbc:sqlite:" + path + "/src/DatabaseConnection/MindPrimeDBCambios.db";

        try { // En caso de que se realice la conexion de manera exitosa

//          Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(SQLCONNECTION);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException ex) { // En caso de que no se realice la conexion
        	
            System.out.println("Hubo un error al conectarse a la base de datos: " + ex.getMessage());
            
        }
        return conn;
    }
}
