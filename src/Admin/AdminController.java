package Admin;

import DatabaseConnection.dbConnection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    Stage focusPointStage = new Stage();


    public void startActivity(ActionEvent actionEvent) {
        System.out.println(
                "Nombre: " + nameId.getText() +
                "\n Apellido: " + lastnameId.getText() +
                        "\n Fecha de Nacimiento: " + birthDateId.getText() +
                        "\n institucion: " + institutionId.getText() +
                        "\n grupo: " + this.group
        );
        addUser();
        showFocusPoint();
        setTimer();

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
            Connection conn = dbConnection.getConnection();
            PreparedStatement sqlStatement = conn.prepareStatement(sqlInsert);
            sqlStatement.setString(1,this.nameId.getText());
            sqlStatement.setString(2,this.lastnameId.getText());
            sqlStatement.setString(3,institutionId.getText() );
            sqlStatement.setString(4,this.group);
            sqlStatement.setString(5,this.birthDateId.getText());

            sqlStatement.execute();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void showFocusPoint(){
        try{

            FXMLLoader focusPointLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)focusPointLoader.load(getClass().getResource("/Instructions/FocusPoint.fxml").openStream());

            AdminController adminController = (AdminController)focusPointLoader.getController();
            Scene scene = new Scene(focusPointRoot);
            focusPointStage.setScene(scene);
            focusPointStage.setTitle("Focus Point");
            focusPointStage.setResizable(false);
            focusPointStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void clearFields(){
        this.nameId.setText("");
        this.lastnameId.setText("");
        this.birthDateId.setText("");
        this.institutionId.setText("");
    }

    public void setTimer(){
        Timeline contador = new Timeline(new KeyFrame(
                Duration.seconds(4),
                accion -> showFamiliarityInstruction()));
        contador.play();
    }

    public void showFamiliarityInstruction(){
        try{
            Stage familiarityInstructionStage = new Stage();
            FXMLLoader familiarityInstructionLoader = new FXMLLoader();
            Pane familiarityInstructionRoot = (Pane)familiarityInstructionLoader.load(getClass().getResource("/Instructions/FamiliarityInstruction.fxml").openStream());

            AdminController familiarityInstructionController = (AdminController)familiarityInstructionLoader.getController();
            Scene scene = new Scene(familiarityInstructionRoot);
            familiarityInstructionStage.setScene(scene);
            familiarityInstructionStage.setTitle("FamiliarityInstruction Point");
            familiarityInstructionStage.setResizable(false);
            familiarityInstructionStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}
