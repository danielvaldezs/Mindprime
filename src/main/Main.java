package main;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

import controllers.hideletters.MainHideLetters;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatabaseConnection dbConnection = new DatabaseConnection();
		MainHideLetters hideLetters = new MainHideLetters();

		try (Connection conn = dbConnection.connect()) {
			System.out.println("Connection to database has been established!");

			// Tarea: Ocultar letras de las palabras
			hideLetters.hideLetters();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
