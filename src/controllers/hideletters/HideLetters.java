package controllers.hideletters;

public class HideLetters {
	
	// Metodo para obtener una palabra aleatoria
	public String getRandomWord(String palabras[]) {

		int longitudPalabras = palabras.length; // Conocer la longitud del arreglo de palabras
		int numeroAleatorio = (int) (Math.random() * longitudPalabras); // Generar numero aleatorio con respecto a
																		// longitud del arreglo
		return palabras[numeroAleatorio]; // Obtener palabra aleatoria
	}
	
	// Metodo para establecer la posicion de las letras a ocultar
	public String manipulateWord(String palabraSeleccionada) {

		int removePositions[];
		char replaceChar = '_';

		// Conocer el numero de caracteres de la palabra
		int longitudPalabra = palabraSeleccionada.length();
		
		
		if (longitudPalabra <= 4) { // En caso de que sea menor o igual a 4
			removePositions = new int[1];
			removePositions[0] = 1;
		} else if (longitudPalabra > 4 && longitudPalabra <= 6) { // En caso de que sea mayor a 4 y menor o igual a 6

			removePositions = new int[2];
			removePositions[0] = 1;
			removePositions[1] = longitudPalabra - 2;

		} else if (longitudPalabra > 6 && longitudPalabra <= 8) { // En caso de que sea mayor a 6 o menor o igual a 8

			removePositions = new int[3];
			removePositions[0] = 1;
			removePositions[1] = 4;
			removePositions[2] = longitudPalabra - 1;

		} else { // En caso de que sea mayor a 8

			removePositions = new int[4];
			removePositions[0] = 2;
			removePositions[1] = 4;
			removePositions[2] = 6;
			removePositions[3] = 8;

		}

		return replaceCharAt(palabraSeleccionada, removePositions, replaceChar); // Llamar al metodo y devolver la palabra que retorna ese metodo
	}

	// Metodo para remplazar las letras indicadas por el caracter establecido
	public static String replaceCharAt(String palabra, int positions[], char replaceChar) {

		StringBuilder nuevaPalabra = new StringBuilder(palabra);
		
		// Sustituir la letra por el caracter de reemplazo: '_'
		for (int position : positions) {
			nuevaPalabra.setCharAt(position, replaceChar);
		}

		return nuevaPalabra.toString(); // Devuelve la palabra con las letras sustituidas por el caracter

	}

}
