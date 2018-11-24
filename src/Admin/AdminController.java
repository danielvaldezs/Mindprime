package Admin;

import DatabaseConnection.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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


    public void startActivity(ActionEvent actionEvent) {
        System.out.println(
                "Nombre: " + nameId.getText() +
                "\n Apellido: " + lastnameId.getText() +
                        "\n Fecha de Nacimiento: " + birthDateId.getText() +
                        "\n institucion: " + institutionId.getText() +
                        "\n grupo: " + this.group
        );
        addUser();
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

    public void clearFields(){
        this.nameId.setText("");
        this.lastnameId.setText("");
        this.birthDateId.setText("");
        this.institutionId.setText("");
    }

}
