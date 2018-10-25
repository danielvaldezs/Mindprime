package main;

public class PrimingActivity {
	
	private String palabras[] = { "papalote", "popote", "violacion", "felicidad", "diversion", "hola", "amigo" };
	
	public static void main(String[] args) {
		// Actividad Priming
		// Se realizara la busqueda de palabras y se seleccionara una de manera aleatoria.
		// Se elegiran de forma aleatoria los caracteres a ocultar.
				
		PrimingActivity actividadPriming = new PrimingActivity();
		
		String palabraAleatoria = actividadPriming.getRandomWord(actividadPriming.getPalabras());
		actividadPriming.manipulateWord(palabraAleatoria);
	}
	
	public String getRandomWord(String palabras[]) {
		
		int longitudPalabras = palabras.length; // Conocer la longitud del arreglo de palabras
		int numeroAleatorio = (int)(Math.random() * longitudPalabras); // Generar numero aleatorio con respecto a longitud del arreglo
		
		return palabras[numeroAleatorio]; // Obtener palabra aleatoria
	}
	
	public void manipulateWord(String palabraSeleccionada) {
		
		int removePositions[];
		char replaceChar = '_';
		
		// Conocer el numero de caracteres de la palabra
		int longitudPalabra = palabraSeleccionada.length();
		
		if (longitudPalabra <= 4) {
			removePositions = new int[1];
			removePositions[0] = 1;
		} else if (longitudPalabra > 4 && longitudPalabra <= 6) {
			
			removePositions = new int[2];
			removePositions[0] = 1;
			removePositions[1] = longitudPalabra - 2;
					
		} else if (longitudPalabra > 6 && longitudPalabra <= 8) {
			
			removePositions = new int[3];
			removePositions[0] = 1;
			removePositions[1] = 4;
			removePositions[2] = longitudPalabra - 1;
			
		} else {
			
			removePositions = new int[4];
			removePositions[0] = 2;
			removePositions[1] = 4;
			removePositions[2] = 6;
			removePositions[3] = 8;
			
		}
		
		String palabraManipulada = replaceCharAt(palabraSeleccionada, removePositions, replaceChar);
		System.out.println(palabraManipulada);
	}
	
	public static String replaceCharAt(String palabra, int positions[], char replaceChar) {
		
		StringBuilder nuevaPalabra = new StringBuilder(palabra);
		
		for (int position : positions) {
			nuevaPalabra.setCharAt(position, replaceChar);
		}
		
		return nuevaPalabra.toString();
		
	}

	public String[] getPalabras() {
		return palabras;
	}

	public void setPalabras(String[] palabras) {
		this.palabras = palabras;
	}
	
	

}
