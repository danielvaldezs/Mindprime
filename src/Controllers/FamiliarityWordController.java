package Controllers;

import Controllers.AdminController;
import DatabaseConnection.dbConnection;
import Model.Word;
import com.sun.corba.se.impl.protocol.INSServerRequestDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javax.swing.*;
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

    Connection connect = dbConnection.getConnection();
    Stage familiarityWordStage = new Stage();
    int idf;

//
//    ObservableList<Word> wordObservableList;
    ArrayList<Word> wordList = new ArrayList<Word>();

    public FamiliarityWordController() throws SQLException {
    }

    @FXML
    private void initialize() throws SQLException{
    ResultSet palabra = null;
        try{
            palabra = connect.createStatement().executeQuery("SELECT word,idWord from Word where idWord in (select idWord from word order by random() limit 1);\n");
        }catch (SQLException e){
            e.printStackTrace();
        }
        String s =  palabra.getString("word");
        //texto.setValue(s);
        algo.setText(s);

        //Obtengo el id de la palabra
        idf =  palabra.getInt("idWord");
        System.out.println(idf);
    }


    public void showFamiliarityWord(){
        try{

            FXMLLoader familiarityWordLoader = new FXMLLoader();
            Pane familiarityWord = (Pane)familiarityWordLoader.load(getClass().getResource("/Layouts/FamiliarityWord.fxml").openStream());

//            AdminController adminController = (AdminController)familiarityWordLoader.getController();
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

    public void botonUno(ActionEvent actionEvent) {
        int num = 1;
        insertFamiliarityValue(num, idf);
    }

    public void insertFamiliarityValue(int value, int idf){
        String sqlInsert = "INSERT INTO Familiarity(score, idWord, idTest) values (?,?,1)";
        try{
//            Connection connect = dbConnection.getConnection();
            PreparedStatement sqlStatement = connect.prepareStatement(sqlInsert);
            sqlStatement.setInt(1, value);
            sqlStatement.setInt(2,idf);
            sqlStatement.execute();
            connect.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public void botonDos(ActionEvent actionEvent) {
        int num = 2;
        insertFamiliarityValue(num,idf);
    }

    public void botonTres(ActionEvent actionEvent) {
        int num = 3;
        insertFamiliarityValue(num, idf);
    }

    public void botonCuatro(ActionEvent actionEvent) {
        int num = 4;
        insertFamiliarityValue(num, idf);
    }

    public void botonCinco(ActionEvent actionEvent) {
        int num = 5;
        insertFamiliarityValue(num, idf);
    }

    public void getWords() throws SQLException {
        connect = dbConnection.getConnection();

        String sqlSelectWord = "select * from word";

        PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectWord);
        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            wordList.add(new Word( rs.getInt("idWord"), rs.getString("word"), rs.getString("category"),
                    rs.getInt("quantitySyllables"), rs.getString("initialLetter").charAt(0), rs.getBoolean("mainWord")));
        }
        printWordList(wordList);

    }

//    @Override
//    public void setScreenParent(ScreensController screenParent) {
//        myController = screenParent;
//    }
    public void printWordList(ArrayList<Word> wordList ){
        for(int i = 0; i < wordList.size(); i++){
            System.out.println(wordList.get(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getWords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}