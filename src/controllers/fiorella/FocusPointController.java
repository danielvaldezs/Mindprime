package controllers.fiorella;

import Controllers.AdminController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class FocusPointController {
    Stage focusPointStage = new Stage();
    FamiliarityWordController familiarityWord = new FamiliarityWordController();

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

    public void setTimer(){
        Timeline contador = new Timeline(new KeyFrame(
                Duration.seconds(4),
                accion -> familiarityWord.showFamiliarityWord()));
        contador.play();
    }

    public void closeFocusPoint(){
        this.focusPointStage.close();
    }


}
