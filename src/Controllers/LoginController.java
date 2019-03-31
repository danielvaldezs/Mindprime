package Controllers;

import DatabaseConnection.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Stage adminStage = new Stage();

    @FXML private Label dbStatusId;
    @FXML private TextField usernameId;
    @FXML private PasswordField passwordId;
    @FXML private Button signInButtonId;
    @FXML private Label loginStatusId;

    Connection connection = dbConnection.getConnection();
    
    public LoginController() throws SQLException {
    	
    }
    // Useless stuff
//    public LoginController(){
//        try{
//            this.connection = dbConnection.getConnection();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        if(this.connection == null){
//            System.exit(1);
//        }
//    }
//
//    public boolean isDatabaseConnected(){
//        return this.connection != null;
//    }

    public boolean isLogin(String adminame, String pass) throws Exception{

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sqlSelect = "SELECT * FROM ADMIN where adminName = ? and password =?"; //firstName and password are the name of the attributes in sqlite
        
        try{
            preparedStatement = this.connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1,adminame);
            preparedStatement.setString(2,pass);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            return false;
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

    @FXML
    public void Login(ActionEvent event){
        try{
            if(isLogin(this.usernameId.getText(),this.passwordId.getText())){
                Stage stage = (Stage)this.signInButtonId.getScene().getWindow();
                stage.close();
                adminLogin();
                this.loginStatusId.setText(usernameId.getText() + " ha iniciado sesion");
            }
            else{
                this.loginStatusId.setText("Usuario o contraseña no válidos");
            }
        }catch (Exception localException){
            localException.getStackTrace();
        }
    }

    public void adminLogin(){
        try{

            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminRoot = (Pane)adminLoader.load(getClass().getResource("/Layouts/Admin.fxml").openStream());

            AdminController adminController = (AdminController)adminLoader.getController();
            Scene scene = new Scene(adminRoot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
