package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public Connection connect() {
		String path = System.getProperty("user.dir"); // Get current local directory
		String url = "jdbc:sqlite:" + path + "/src/database/MindPrimeDB.db"; // SQLite url para hacer conexion
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Hubo un error al conectarse a la base de datos: " + e.getMessage());
		}
		return conn;
	}

}
