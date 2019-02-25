package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.management.remote.rmi.RMIConnectionImpl;
import java.io.IOException;

public class adminController {
   Stage focusPoint = new Stage();
    private ActionEvent event;
    private adminController adminController;


    public void startFamilirialityInstruction(ActionEvent actionEvent) {
        try {
            FXMLLoader adminLoader = new FXMLLoader();
            Pane FocusPoint = adminLoader.load(getClass().getResource("../Layouts/FamilirialityInstruction.fxml").openStream());

          //  adminController adminController = (adminController) adminLoader.getController();
            Scene scene = new Scene(FocusPoint);
            focusPoint.setScene(scene);
            focusPoint.setTitle("Instrucciones");
            focusPoint.setResizable(false);
            focusPoint.show();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
