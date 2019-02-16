package controllers.fiorella;

import Controllers.AdminController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FamiliarityWordController {
    
    Stage familiarityWordStage = new Stage();

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

}
