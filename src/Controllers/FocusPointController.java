package Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class FocusPointController {
    FamiliarityActivityController familiarityWord = new FamiliarityActivityController();
    Stage focusPointStage = new Stage();


    public void showFocusPoint(){
        try{

            FXMLLoader focusPointLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)focusPointLoader.load(getClass().getResource("/Layouts/FocusPoint.fxml").openStream());

//            AdminController adminController = (AdminController)focusPointLoader.getController();
            Scene scene = new Scene(focusPointRoot);
            this.focusPointStage.setScene(scene);
            this.focusPointStage.setTitle("Focus Point");
            this.focusPointStage.setResizable(false);
            this.focusPointStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //Metodo que despues de cierto tiempo, te lleva de la vista focus point
    //a la vista FamiliarityWord
    public void setTimer(){
        Timeline contador = new Timeline(new KeyFrame(
                Duration.seconds(1),
                accion -> familiarityWord.showFamiliarityWord()));
        contador.play();
    }
}
