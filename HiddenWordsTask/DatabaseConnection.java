package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/FernandoHdz/Documents/School/Mindprime/MindPrimeDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
	}
	
}
