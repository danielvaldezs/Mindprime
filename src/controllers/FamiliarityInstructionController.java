package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class FamiliarityInstructionController {

    Stage familiarityInstructionStage = new Stage();
    FocusPointController focusPoint = new FocusPointController();
    FamiliarityWordController familiarityWord = new FamiliarityWordController();


    public void showFamiliarityInstruction() {
        try {

            FXMLLoader familiarityInstructionLoader = new FXMLLoader();
            Pane familiarityInstructionRoot = (Pane) familiarityInstructionLoader.load(getClass().getResource("/Layouts/FamiliarityInstruction.fxml").openStream());

//            FamiliarityInstructionController adminController = (FamiliarityInstructionController) familiarityInstructionLoader.getController();

            Scene scene = new Scene(familiarityInstructionRoot);
            this.familiarityInstructionStage.setScene(scene);
            this.familiarityInstructionStage.setTitle("familiarityInstruction Point");
            this.familiarityInstructionStage.setResizable(false);
            this.familiarityInstructionStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeFamiliarityInstruction(){
        this.familiarityInstructionStage.close();
    }

    public void showFocusPoint(ActionEvent actionEvent) {
        focusPoint.showFocusPoint();
        focusPoint.setTimer();
    }


}