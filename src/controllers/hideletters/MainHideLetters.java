package controllers.hideletters;

import database.DatabaseConnection;
import controllers.hideletters.SelectMainWords;
import controllers.hideletters.HideLetters;

public class MainHideLetters {
	// Actividad Priming
	// Se realizara la busqueda de palabras y se seleccionara una de manera
	// aleatoria.
	// Se elegiran de forma aleatoria los caracteres a ocultar.
	public void hideLetters() {
	
		DatabaseConnection dbConnection = new DatabaseConnection();
		SelectMainWords selectMainWords = new SelectMainWords();
		HideLetters actividadPriming = new HideLetters();
		
		String[] palabras = selectMainWords.selectWords(dbConnection.connect());
		
		System.out.println("Palabras obtenidas de la base de datos: ");
		for (int i = 0; i < palabras.length; i++) {
			System.out.println(palabras[i]);
		}
		
		String palabraAleatoria = actividadPriming.getRandomWord(palabras); // Obtener palabra aleatoria
		System.out.println("Palabra aleatoria: " + palabraAleatoria); // Imprimir palabra aleatoria
		System.out.println(actividadPriming.manipulateWord(palabraAleatoria)); // Imprimir palabra con letras ocultas
	}
}
