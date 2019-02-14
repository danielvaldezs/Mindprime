package main;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

import controllers.hideletters.HideWords;
import controllers.hideletters.SelectMainWords;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatabaseConnection dbConnection = new DatabaseConnection();
		SelectMainWords selectMainWords = new SelectMainWords();

		try (Connection conn = dbConnection.connect()) {
			System.out.println("Connection to database has been established!");

			// Obtener palabras de la base de datos
			String[] palabras = selectMainWords.selectWords(dbConnection.connect());
			for (int i = 0; i < palabras.length; i++) {
				System.out.println(palabras[i]);
			}

			HideWords actividadPriming = new HideWords();
			String palabraAleatoria = actividadPriming.getRandomWord(palabras); // Obtener palabra aleatoria
			System.out.println("palabra aleatoria: " + palabraAleatoria); // 
			System.out.println(actividadPriming.manipulateWord(palabraAleatoria)); // Imprimir palabra con letras ocultas
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Actividad Priming
		// Se realizara la busqueda de palabras y se seleccionara una de manera
		// aleatoria.
		// Se elegiran de forma aleatoria los caracteres a ocultar.
	}

}
