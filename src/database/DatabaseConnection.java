package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection connect() {
		
		Connection conn = null;
		String path = System.getProperty("user.dir"); // Get current local directory
		
		try {
			String url = "jdbc:sqlite:" + path + "/src/database/MindPrimeDB.db"; // SQLite url para hacer conexion
			conn = DriverManager.getConnection(url);
			
            System.out.println("Connection to SQLite has been established.");
            
		} catch (SQLException e) {
			System.out.println("Hubo un error al conectarse a la base de datos: " + e.getMessage());
		}
		return conn;
	}

}
