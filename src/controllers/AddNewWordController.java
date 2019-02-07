package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Metodo utilizado para blablablabla
 */
public class AddNewWordController {

    public void showAddNewWord() {
        try {

            FXMLLoader addNewWordLoader = new FXMLLoader();
            Pane primingInstructionRoot = (Pane) addNewWordLoader.load(getClass().getResource("/Layouts/AddNewWord.fxml").openStream());

//            AdminController adminController = (AdminController) addNewWordLoader.getController();
            Stage addNewWordStage = new Stage();
            Scene scene = new Scene(primingInstructionRoot);
            addNewWordStage.setScene(scene);
            addNewWordStage.setTitle("familiarityInstruction Point");
            addNewWordStage.setResizable(false);
            addNewWordStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
