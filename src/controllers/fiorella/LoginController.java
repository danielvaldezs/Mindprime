package controllers.fiorella;
import Controllers.AdminController;
import LoginAdminApp.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController  {

    LoginModel loginModel = new LoginModel();
    Stage adminStage = new Stage();

    @FXML private Label dbStatusId;
    @FXML private TextField usernameId;
    @FXML private PasswordField passwordId;
    @FXML private Button signInButtonId;
    @FXML private Label loginStatusId;
//    @Override

    public void initialize(URL url, ResourceBundle rb) {
        if(this.loginModel.isDatabaseConnected()){
            this.dbStatusId.setText("Database connected");
        }else{
            this.dbStatusId.setText("Database NOT connected");
        }
    }


    @FXML
    public void Login(ActionEvent event){
        try{
            if(this.loginModel.isLogin(this.usernameId.getText(),this.passwordId.getText())){
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
            Pane adminRoot = (Pane)adminLoader.load(getClass().getResource("/Layouts/AdminFXML.fxml").openStream());

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
    public void adminLoginClose(){
        this.adminStage.close();
    }


}
