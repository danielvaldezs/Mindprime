package controllers.fiorella;

import database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminController {
    @FXML private TextField nameId;
    @FXML private TextField lastnameId;
    @FXML private TextField birthDateId;
    @FXML private TextField institutionId;
    @FXML private RadioButton rbTeptAsiId;
    @FXML private RadioButton rbCHId;
    @FXML private RadioButton rbCFId;
    @FXML private Button startButtonId;
    private String group;

    FamiliarityInstructionController familiarityInstruction = new FamiliarityInstructionController();
    FamiliarityWordController familiarityWord;

//    Stage focusPointStage = new Stage();

    public void startActivity(ActionEvent actionEvent) {
        System.out.println(
                "Nombre: " + nameId.getText() +
                "\n Apellido: " + lastnameId.getText() +
                        "\n Fecha de Nacimiento: " + birthDateId.getText() +
                        "\n institucion: " + institutionId.getText() +
                        "\n grupo: " + this.group
        );
        addUser();
        showInstruction();
//        setTimer();

    }

    public void radioSelect(ActionEvent actionEvent) {
        if(rbTeptAsiId.isSelected()){
            this.group = rbTeptAsiId.getText();
        }
        else if(rbCHId.isSelected()){
            this.group = rbCHId.getText();
        }
        else if(rbCFId.isSelected()){
            this.group = rbCFId.getText();
        }
    }

    public void addUser(){
        String sqlInsert = "INSERT INTO User (firstname, lastname, institution,userGroup,birthdate) VALUES (?,?,?,?,?)";
        try{
            Connection connect = DatabaseConnection.connect();
            PreparedStatement sqlStatement = connect.prepareStatement(sqlInsert);
            sqlStatement.setString(1,this.nameId.getText());
            sqlStatement.setString(2,this.lastnameId.getText());
            sqlStatement.setString(3,institutionId.getText() );
            sqlStatement.setString(4,this.group);
            sqlStatement.setString(5,this.birthDateId.getText());

            sqlStatement.execute();
            connect.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void clearFields(){
        this.nameId.setText("");
        this.lastnameId.setText("");
        this.birthDateId.setText("");
        this.institutionId.setText("");
    }

    public void showInstruction(){
        familiarityInstruction.showFamiliarityInstruction();
    }

    public void generateStatistics(ActionEvent actionEvent) {
        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("sqlite3 -header -csv MindPrimeDb.db \"select * from word;\" > out.csv");
//            Process pr = rt.exec("java -jar map.jar time.rel test.txt debug");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewWord(ActionEvent actionEvent) {
       AddNewWordController addNewWord = new AddNewWordController();
       addNewWord.showAddNewWord();
    }


}
