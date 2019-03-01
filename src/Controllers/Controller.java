package Controllers;

import javafx.event.ActionEvent;
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

public class Controller {

    Stage adminStage = new Stage();
    private ActionEvent event;

    public void AdminLaunch(ActionEvent actionEvent) {
        try {
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminRoot = adminLoader.load(getClass().getResource("../Layouts/Admin.fxml").openStream());

            // adminController adminController = (adminController) adminLoader.getController();
            Scene scene = new Scene(adminRoot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}






