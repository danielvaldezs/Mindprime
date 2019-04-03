package Controllers;

import DatabaseConnection.dbConnection;
import Model.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewWordController implements Initializable{

    public Button saveWordButton;
    @FXML private TextField txtWord;
    @FXML private TextField txtQuantitySyllables;
    @FXML private ToggleGroup mainWordGroup;
    @FXML private ToggleGroup categoryGroup;
    Connection connect;

    public void showAddNewWord() {
        try {

            FXMLLoader addNewWordLoader = new FXMLLoader();
            Pane primingInstructionRoot = (Pane) addNewWordLoader.load(getClass().getResource("/Layouts/AddNewWord.fxml").openStream());

//            AdminController adminController = (AdminController) addNewWordLoader.getController();
            Stage addNewWordStage = new Stage();
            Scene scene = new Scene(primingInstructionRoot);
            addNewWordStage.setScene(scene);
            addNewWordStage.setTitle("Add New Word");
            addNewWordStage.setResizable(false);
            addNewWordStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getCategorySelected(){
        RadioButton selectedRadioButton = (RadioButton) categoryGroup.getSelectedToggle();
        String stringCategorySelected = selectedRadioButton.getText();
        return stringCategorySelected;
    }

    private boolean isMainWord(){
        RadioButton selectedRadioButton = (RadioButton) mainWordGroup.getSelectedToggle();
        boolean mainWordSelected = false;
        if(selectedRadioButton.getText().equals("Si")){
            mainWordSelected = true;
        }
        return mainWordSelected;
    }

    private char getWordFirstLetter(){
        String word = txtWord.getText();
        char initialLetter = word.charAt(0);
        return initialLetter;
    }

    public void addNewWord(ActionEvent actionEvent) {
        String string_word = txtWord.getText();

        if(!txtWord.getText().equals("") && !txtQuantitySyllables.getText().equals("")
                && mainWordGroup.getSelectedToggle().isSelected() && categoryGroup.getSelectedToggle().isSelected()) {

            try {
                connect = dbConnection.getConnection();
                if (!wordExists(string_word)) {
                    if(selectBeforeAddWord().showAndWait().get() == ButtonType.OK) {
                        String sqlInsert = "INSERT INTO word (word, category, quantitySyllables,initialLetter,mainWord) VALUES (?,?,?,?,?)";

                        PreparedStatement sqlStatement = connect.prepareStatement(sqlInsert);

                        sqlStatement.setString(1, this.txtWord.getText());
                        sqlStatement.setString(2, getCategorySelected());
                        sqlStatement.setInt(3, Integer.parseInt(txtQuantitySyllables.getText()));
                        sqlStatement.setString(4, String.valueOf(getWordFirstLetter()));
                        sqlStatement.setBoolean(5, isMainWord());

                        sqlStatement.executeUpdate();

                        Stage stage = (Stage) this.saveWordButton.getScene().getWindow();
                        showAlertWordAdded();
                        stage.close();
                    }
                } else {
                    showAlertWordExists();
                }
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            showAlertFieldEmpty();
        }
    }

    private void showAlertWordExists() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Palabra Existente");
        alert.setContentText("La palabra que desea agregar, ya existe");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private Alert selectBeforeAddWord() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Palabra Agregada");
        alert.setContentText("Seguro que desea agregar la palabra?");
        alert.setHeaderText(null);
        return alert;
//        if(alert.showAndWait().get() == ButtonType.OK){
//            addNewWord();
//            Stage stage = (Stage)this.saveWordButton.getScene().getWindow();
//            stage.close();
//        }
    }


    private void showAlertWordAdded() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Palabra Agregada");
        alert.setContentText("La palabra ha sido agregada exitosamente");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void showAlertFieldEmpty() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Campos Vacios");
        alert.setContentText("Debe llenar todos los campos");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    //Metodo para validar si la palabra ya existe en la base de datos
    private boolean wordExists(String word) {
        boolean word_exists = false;
        String sqlDuplicityWord = "SELECT word from word where word ='" + word + "'";
        try{
            ResultSet rs = connect.createStatement().executeQuery(sqlDuplicityWord);
            if(rs.next()){
                word_exists = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return word_exists;
    }

    public void onlyOneLetter(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        TextField tx = (TextField) keyEvent.getSource();
        if((Character.isDigit(c)) || (tx.getText().length() >= 1)){
            keyEvent.consume();
        }
    }

    public void onlyLetters(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        TextField tx = (TextField) keyEvent.getSource();
        if(!Character.isLetter(c)){
            keyEvent.consume();
        }
    }

    public void onlyNumbers(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        if(!Character.isDigit(c)){
            keyEvent.consume();
        }
    }

//    @Override
//    public void setScreenParent(ScreensController screenParent) {
//        myController = screenParent;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
