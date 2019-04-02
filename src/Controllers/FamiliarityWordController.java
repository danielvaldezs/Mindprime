package Controllers;

import DatabaseConnection.dbConnection;
import Model.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FamiliarityWordController implements Initializable {

    @FXML
    public Text texto;
    public Text algo;
    public Button button1, button2, button3, button4, button5;
    PrimingInstructionController primingInstructionController;

    Connection connect = dbConnection.getConnection();
    Stage familiarityWordStage = new Stage();
    int idf, num;
    int i=0;

    ArrayList<Word> wordList = new ArrayList<Word>();

    public FamiliarityWordController() throws SQLException {
    }

    //Uso de metodo initialize para que en cuanto se cargue la vista, se ejecute metodo getWords y poner la primera
    //palabra del arraylist en el label que corresponde
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getWords();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        algo.setText(wordList.get(i).getWord());
    }

    //Obtener palabras de la base de datos y guardarlas en el arraylist wordList de tipo word
    public void getWords() throws SQLException {
        connect = dbConnection.getConnection();

        String sqlSelectWord = "select * from word";
        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectWord);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            wordList.add(new Word( rs.getInt("idWord"), rs.getString("word"),
                    rs.getString("category"), rs.getInt("quantitySyllables"),
                    rs.getString("initialLetter").charAt(0), rs.getBoolean("mainWord")));
        }
        printWordList(wordList);
    }

    // Imprime los valores guardados en el arraylist wordList
    public void printWordList(ArrayList<Word> wordList ){
        for(int i = 0; i < wordList.size(); i++){
            System.out.println(wordList.get(i));
        }
    }

    //Metodo para detectar el boton que ha sido seleccionado y asignar el valor de familiaridad que tenga el usuario
    @FXML
    public void ActionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(button1)) {
            num = 1;
        } else if (actionEvent.getSource().equals(button2)) {
            num = 2;
        }
        else if (actionEvent.getSource().equals(button3)) {
            num = 3;
        }
        else if (actionEvent.getSource().equals(button4)) {
            num = 4;
        }
        else if (actionEvent.getSource().equals(button5)) {
            num = 5;
        }
        displayWord();
    }

    // Actualiza la palabra que se muestran en el label
    public void displayWord(){
        if(i < 10) {
            algo.setText(wordList.get(i).getWord());
            insertFamiliarityValue(wordList.get(i).getIdWord(), wordList.get(i).getWord(), num);
            i = i + 1;
        }else {
        		try {
					primingInstructionController = new PrimingInstructionController();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            primingInstructionController.showPrimingInstruction();
        }
    }

    // Hace insercion a la tabla Familiarity obteniendo desde el metodo displayWord el id de la palabra, la palabra y
    // el rango de familiaridad
    private void insertFamiliarityValue(Integer idWord, String word, int num) {
        System.out.println(idWord + "  " + word + "  " +num);
        String sqlInsert = "INSERT INTO Familiarity(score, idWord, idTest) values (?,?,1)";
        try{
            Connection connect = dbConnection.getConnection();
            PreparedStatement sqlStatement = connect.prepareStatement(sqlInsert);
            sqlStatement.setInt(1, num);
            sqlStatement.setInt(2,idWord);
            sqlStatement.execute();
            connect.close();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
        	try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public void showFamiliarityWord(){
        try{
            FXMLLoader familiarityWordLoader = new FXMLLoader();
            Pane familiarityWord = (Pane)familiarityWordLoader.load(getClass().getResource("../Layouts/FamiliarityWord.fxml").openStream());

            Scene scene = new Scene(familiarityWord);
            this.familiarityWordStage.setScene(scene);
            this.familiarityWordStage.setTitle("FamiliarityWord");
            this.familiarityWordStage.setResizable(false);
            this.familiarityWordStage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void closeFamiliarityWord(){
        this.familiarityWordStage.close();
    }

}