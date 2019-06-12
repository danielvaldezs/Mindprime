package Controllers;

import DatabaseConnection.dbConnection;
import Model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Connection;
import java.util.ResourceBundle;


public class PrimingWord2Controller implements Initializable{

    public Label hideLetterWord;
    public Button option1, option2, option3;
    private String completeWord, incompleteWord;
    int turns=1, i = 0, lastTest;
    long init, click, total, mouse, movementTime;
    ArrayList<Long> movementList = new ArrayList<Long>();
    String[] answerOptions = new String[2];
    private ArrayList<Word> wordList = new ArrayList<>();
    Stage primingWordStage = new Stage();
    FamiliarityWordController familiarityWord = new FamiliarityWordController();
    int totalWords = 30;

    private Connection connect;

    public void showPrimingWord(){
        try{
            FXMLLoader primingWordLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)primingWordLoader.load(getClass().getResource("/Layouts/PrimingWord.fxml").openStream());

            Scene scene = new Scene(focusPointRoot);
            this.primingWordStage.setScene(scene);
            this.primingWordStage.setTitle("Priming Word");
            this.primingWordStage.setResizable(false);
            this.primingWordStage.show();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void getWords(){
        connect = dbConnection.getConnection();
        try{
            String sqlSelectWord = "select * from word order by random() limit 60;";
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectWord);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                wordList.add(new Word(rs.getInt("idWord"), rs.getString("word"),
                        rs.getString("category"), rs.getInt("quantitySyllables"),
                        rs.getString("initialLetter").charAt(0), rs.getBoolean("mainWord")));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    @FXML
    public void ActionPerformed(ActionEvent actionEvent)
    {
        movementList.clear();
//        System.out.println("rnfr" + movementList.get(0));
        click = System.currentTimeMillis();
        total = click - init;
        boolean answer=false;
		if (actionEvent.getSource().equals(option1))
		{
		    if(option1.getText() == completeWord){
		        System.out.println("correcto!!!");
		        answer=true;
            }else {
                System.out.println("incorrecto!!");
            }
			//Checar si esta opcion es igual a la respuesta
		}
		else if (actionEvent.getSource().equals(option2)) {
            if(option2.getText() == completeWord){
                System.out.println("correcto!!!");
                answer=true;
            }
            else {
                System.out.println("incorrecto!!");
            }
		} else if (actionEvent.getSource().equals(option3)) {
            if(option3.getText() == completeWord){
                answer = true;
                System.out.println("correcto!!!");
            }
            else {
                System.out.println("incorrecto!!");
            }
		}
        i=0;
		if(turns < totalWords){
            insertActivityPriming(answer, lastTest, wordList.get(turns).getIdWord());
            turns++;
            hideLetters();
            getOptions(wordList.get(turns));
            showWordsInView();
//            if(turns<totalWords){
//                insertActivityPriming(answer, lastTest, wordList.get(turns).getIdWord());
//            }
            init = System.currentTimeMillis();
            movementList.add(mouse);

        }else {
            insertActivityPriming(answer, lastTest, wordList.get(turns).getIdWord());

            showFinishedTestView();
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        }
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

    public void hideLetters(){
        completeWord = wordList.get(turns).getWord();
        incompleteWord = manipulateWord(wordList.get(turns).getWord());
    }

    public void getOptions(Word word){
        connect = dbConnection.getConnection();
        String sql = "select word from word where quantitySyllables = ? and word != ? limit 2";

        try{
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, word.getQuantitySyllables());
            preparedStatement.setString(2, word.getWord());
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                answerOptions[i] = rs.getString("word");
                i++;
            }
        }catch (SQLException sq){
            System.out.println(sq);
        }
    }

    public void showWordsInView(){
        int numero = (int) (Math.random() * 3) + 1;
        if(numero == 1) {
            option1.setText(completeWord);
            option2.setText(answerOptions[0]);
            option3.setText(answerOptions[1]);
        }else if(numero == 2) {
            option2.setText(completeWord);
            option1.setText(answerOptions[0]);
            option3.setText(answerOptions[1]);
        }else if(numero == 3){
            option3.setText(completeWord);
            option1.setText(answerOptions[1]);
            option2.setText(answerOptions[0]);
        }
        hideLetterWord.setText(incompleteWord);
    }

    private void insertActivityPriming(boolean answer, int idTest, int idWord){
        String sqlInsert = "Insert into ActivityPriming(answerTime, movementTime,answer, idTest, idWord) values (?,?,?,?,?)";
        try{
            PreparedStatement sqlStatement = connect.prepareStatement(sqlInsert);
            sqlStatement.setDouble(1, total);
            sqlStatement.setDouble(2, 3.5);
            sqlStatement.setBoolean(3, answer);
            sqlStatement.setInt(4,idTest);
            sqlStatement.setInt(5, idWord);

            sqlStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void showFinishedTestView(){
        FinishedTestController finishedTestController = new FinishedTestController();
        finishedTestController.showFinishedTestView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init = System.currentTimeMillis();
        getWords();
        hideLetters();
        getOptions(wordList.get(turns));
        showWordsInView();
        lastTest = getLastTest();
    }
    public int getLastTest(){

        String sqlSelect = "select idTest from Test order by idTest DESC limit 1;";

        try{
            PreparedStatement ps = connect.prepareStatement(sqlSelect);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lastTest = rs.getInt("idTest");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return lastTest;
    }

    public void mouseMouvement(MouseEvent mouseEvent) {
        mouse = System.currentTimeMillis();
        movementList.add(mouse);
        movementTime = movementList.get(0)-init;
        System.out.println("movementTime---" + movementTime);
    }
}

