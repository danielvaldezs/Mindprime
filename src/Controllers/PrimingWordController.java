package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DatabaseConnection.dbConnection;

public class PrimingWordController implements Initializable {

    Stage primingWordStage = new Stage();
    public Label hideLetterWord;

    public void showPrimingWord(){
    	
        try{

            FXMLLoader primingWordLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)primingWordLoader.load(getClass().getResource("/Layouts/FocusPoint.fxml").openStream());

//            AdminController adminController = (AdminController)primingWordLoader.getController();
            Scene scene = new Scene(focusPointRoot);
            this.primingWordStage.setScene(scene);
            this.primingWordStage.setTitle("Focus Point");
            this.primingWordStage.setResizable(false);
            this.primingWordStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
//  public ArrayList selectFamiliarityWords(Connection connection) {
//		
////		ArrayList<> palabras = new ArrayList<>; // Arreglo para almacenar palabras de la base de datos
//		String sql = "SELECT idWord, word, category, quantitySyllables " + "FROM Word "
//				+ "order by random() " + "limit ;"; // SQL Query para leer las palabras
//
//		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
//
//			// loop through the result set
//			int i = 0;
//			while (rs.next()) {
//				palabras[i] = rs.getString("word");
//				i++;
//			}
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//		return palabras;
//	}
  
  
	// Metodo para leer palabras de la base de datos
  public String[] selectWords(Connection connection) {
		
		String palabras[] = new String[2]; // Arreglo para almacenar palabras de la base de datos
		String sql = "SELECT idWord, word, category, quantitySyllables " + "FROM Word "
				+ "order by random() " + "limit 2;"; // SQL Query para leer las palabras

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			int i = 0;
			while (rs.next()) {
				palabras[i] = rs.getString("word");
				i++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return palabras;
	}
  
	// Metodo para obtener una palabra aleatoria
  public String getRandomWord(String palabras[]) {

		int longitudPalabras = palabras.length; // Conocer la longitud del arreglo de palabras
		int numeroAleatorio = (int) (Math.random() * longitudPalabras); // Generar numero aleatorio con respecto a
																		// longitud del arreglo
		return palabras[numeroAleatorio]; // Obtener palabra aleatoria
	}
  
  // Metodo para establecer la posicion de las letras a ocultar
  // Retorna la palabra sin las letras correspondientes
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

		// Llamar al metodo y devolver la palabra que retorna ese metodo
		return replaceCharAt(palabraSeleccionada, removePositions, replaceChar);
	}
	
	// Metodo para remplazar las letras indicadas por el caracter establecido
	public static String replaceCharAt(String palabra, int positions[], char replaceChar) {

		StringBuilder nuevaPalabra = new StringBuilder(palabra);
		
		// Sustituir la letra por el caracter de reemplazo: '_'
		for (int position : positions) {
			nuevaPalabra.setCharAt(position, replaceChar);
		}
		
		// Devuelve la palabra con las letras sustituidas por el caracter
		return nuevaPalabra.toString();

	}

	// Metodo que se ejectura automaticamente cuando se llame a este controlador
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String[] palabras;
		
		try {
			palabras = this.selectWords(dbConnection.getConnection());
			System.out.println("Palabras obtenidas de la base de datos: ");
			for (int i = 0; i < palabras.length; i++) {
				System.out.println(palabras[i]);
			}
			
			String palabraAleatoria = this.getRandomWord(palabras); // Obtener palabra aleatoria
			System.out.println("Palabra aleatoria: " + palabraAleatoria); // Imprimir palabra aleatoria
			String word = this.manipulateWord(palabraAleatoria); // Imprimir palabra con letras ocultas
			
			System.out.println("Palabra escondida: " + word);
			
			hideLetterWord.setText(word);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
