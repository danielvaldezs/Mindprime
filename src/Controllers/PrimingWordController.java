package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import DatabaseConnection.dbConnection;
import Model.Word;
import java.util.ArrayList;

public class PrimingWordController implements Initializable {

    Stage primingWordStage = new Stage();
    public Label hideLetterWord;
    public Button option1, option2, option3;
    private Connection connect;
    FamiliarityWordController familiarityWord = new FamiliarityWordController();
    
    public PrimingWordController() throws SQLException {
    }


    public void showPrimingWord(){
    	
        try{

            FXMLLoader primingWordLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)primingWordLoader.load(getClass().getResource("/Layouts/PrimingWord.fxml").openStream());

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
  
  	// Metodo para obtener la palabra en base al nombre de la palabra y
  	// crear una instancia del mismo con las propiedades
  	public Word getWordByName(String wordName) throws SQLException {
  		
  		System.out.println("Obtener palabra por nombre");
        connect = dbConnection.getConnection();
        Word newWord = new Word();
  		String sql = "select idWord, word, category, quantitySyllables, initialLetter from Word where word = ? order by idWord asc limit 1";
  		PreparedStatement preparedStatement = connect.prepareStatement(sql);
  		preparedStatement.setString(1, wordName);
        ResultSet rs = preparedStatement.executeQuery();
        
        while(rs.next()){            
            newWord.setIdWord(rs.getInt("idWord"));
            newWord.setWord(rs.getString("word"));
            newWord.setCategory(rs.getString("category"));
            newWord.setQuantitySyllables(rs.getInt("quantitySyllables"));
            newWord.setInitialLetter(rs.getString("initialLetter").charAt(0));
        }
        
        return newWord;
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

		int removePositions[]; // Arreglo para almacenar las posiciones de las letras a remover de cierta palabra
		char replaceChar = '_'; // Caracter a sustituir

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
	
	// Metodo para seleccionar las 2 opciones de respuesta
	public String[] selectTwoAnswerOptions(Word hiddenWord) throws SQLException {
		
		connect = dbConnection.getConnection();
		
		String[] answerOptions = new String[2];
		
		// Obtener opciones en caso de que la palabra escondida sea Positiva
		if (hiddenWord.getCategory().equals("Positiva")) {
			
			String sql1 = "select word\r\n" + 
					"from word\r\n" + 
					"where category = 'Neutra' and quantitySyllables = ? and initialLetter = ?" + 
					"order by random()\r\n" + 
					"limit 1;";
			
			 PreparedStatement preparedStatement1 = connect.prepareStatement(sql1);
			 preparedStatement1.setInt(1, hiddenWord.getQuantitySyllables());
			 preparedStatement1.setString(2, Character.toString(hiddenWord.getInitialLetter()));
		     ResultSet rs1 = preparedStatement1.executeQuery();
		     
		     if (rs1.next()) {
		    	 answerOptions[0] = rs1.getString("word");
		     }
		     
		     String sql2 = "select word\r\n" + 
						"from word\r\n" + 
						"where category = 'Negativa' and quantitySyllables = ? and initialLetter = ?" +
						"order by random()\r\n" + 
						"limit 1;";
				
				 PreparedStatement preparedStatement2 = connect.prepareStatement(sql2);
				 preparedStatement2.setInt(1, hiddenWord.getQuantitySyllables());
				 preparedStatement2.setString(2, Character.toString(hiddenWord.getInitialLetter()));
			     ResultSet rs2 = preparedStatement2.executeQuery();
			     
			     if (rs2.next()) {
			    	 answerOptions[1] = rs2.getString("word");
			     }
		}
		
		// Obtener opciones en caso de que la palabra escondida sea Negativa
		else if (hiddenWord.getCategory().equals("Negativa") || hiddenWord.getCategory().equals("Negativas")) {
					
					String sql1 = "select word\r\n" + 
							"from word\r\n" + 
							"where category = 'Neutra' and quantitySyllables = ? and initialLetter = ?" + 
							"order by random()\r\n" + 
							"limit 1;";
					
					 PreparedStatement preparedStatement1 = connect.prepareStatement(sql1);
					 preparedStatement1.setInt(1, hiddenWord.getQuantitySyllables());
					 preparedStatement1.setString(2, Character.toString(hiddenWord.getInitialLetter()));

				     ResultSet rs1 = preparedStatement1.executeQuery();
				     
				     if (rs1.next()) {
				    	 answerOptions[0] = rs1.getString("word");
				     }
				     
				     String sql2 = "select word\r\n" + 
								"from word\r\n" + 
								"where category = 'Positiva' and quantitySyllables = ? and initialLetter = ?" +
								"order by random()\r\n" + 
								"limit 1;";
						
						 PreparedStatement preparedStatement2 = connect.prepareStatement(sql2);
						 preparedStatement2.setInt(1, hiddenWord.getQuantitySyllables());
						 preparedStatement2.setString(2, Character.toString(hiddenWord.getInitialLetter()));
					     ResultSet rs2 = preparedStatement2.executeQuery();
					     
					     if (rs2.next()) {
					    	 answerOptions[1] = rs2.getString("word");
					     }
				}
		
				// Obtener opciones en caso de que la palabra escondida sea Neutra
		else if (hiddenWord.getCategory().equals("Neutra")) {
					
					String sql1 = "select word\r\n" + 
							"from word\r\n" + 
							"where category = 'Positiva' and quantitySyllables = ? and initialLetter = ?" + 
							"order by random()\r\n" + 
							"limit 1;";
					
					 PreparedStatement preparedStatement1 = connect.prepareStatement(sql1);
					 preparedStatement1.setInt(1, hiddenWord.getQuantitySyllables());
					 preparedStatement1.setString(2, Character.toString(hiddenWord.getInitialLetter()));
				     ResultSet rs1 = preparedStatement1.executeQuery();
				     
				     if (rs1.next()) {
				    	 answerOptions[0] = rs1.getString("word");
				     }
				     
				     String sql2 = "select word\r\n" + 
								"from word\r\n" + 
								"where category = 'Negativa' and quantitySyllables = ? and initialLetter = ?" +
								"order by random()\r\n" + 
								"limit 1;";
						
						 PreparedStatement preparedStatement2 = connect.prepareStatement(sql2);
						 preparedStatement2.setInt(1, hiddenWord.getQuantitySyllables());
						 preparedStatement2.setString(2, Character.toString(hiddenWord.getInitialLetter()));
					     ResultSet rs2 = preparedStatement2.executeQuery();
					     
					     if (rs2.next()) {
					    	 answerOptions[1] = rs2.getString("word");
					     }
				}	
		
		return answerOptions;
	}
	
	public void showWordsInView(String hiddenWord, String[] answerOptions, Word answer) {
		
//		 TODO: Asignar de manera aleatoria las palabras a los botones para que no aparezcan siempre en el mismo lugar
		
		option1.setText(answerOptions[0]); // Mostrar primera posible respuesta
		option2.setText(answerOptions[1]); // Mostrar segunda posible respuesta
		option3.setText(answer.getWord()); // Mostrar respuesta
		hideLetterWord.setText(hiddenWord); // Mostrar palabra oculta
		
	}

	// Metodo que se ejectura automaticamente cuando se llame a este controlador
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String[] palabras;
		
//		ArrayList<Word> familiarityWords = familiarityWord.getWordList();
//		System.out.println(familiarityWords);

		try {
			connect = dbConnection.getConnection();
			palabras = this.selectWords(connect);
			
			System.out.println("Palabras obtenidas de la base de datos: ");
			for (int i = 0; i < palabras.length; i++) {
				System.out.println((i + 1) + ". - " + palabras[i]);
			}
			
			String palabraAleatoria = this.getRandomWord(palabras); // Obtener palabra aleatoria
			String hiddenWord = this.manipulateWord(palabraAleatoria); // Asignar palabra oculta a variable
			Word answerWord = this.getWordByName(palabraAleatoria); // Obtener las propiedades de la palabra (creando un objeto de tipo Word)
			System.out.println(answerWord);
			String[] answerOptions = this.selectTwoAnswerOptions(answerWord);
			
			System.out.println("Opciones de respuesta: ");
			for (int i = 0; i < answerOptions.length; i++) {
				System.out.println(answerOptions[i]);;
			}
			
			this.showWordsInView(hiddenWord, answerOptions, answerWord); // Mostrar los valores en la vista
			
//			System.out.println("Palabra aleatoria: " + palabraAleatoria);
//			System.out.println("Palabra escondida: " + hiddenWord);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
}
