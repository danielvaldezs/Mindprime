package Controllers;

import Controllers.FamiliarityWordController;
import Controllers.FocusPointController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FamiliarityInstructionController
{


    public Button imageButton;

    Stage familiarityInstructionStage = new Stage();
    FocusPointController focusPoint = new FocusPointController();
    FamiliarityWordController familiarityWord = new FamiliarityWordController();

    public FamiliarityInstructionController()
    {
    }


    public void showFamiliarityInstruction()
    {
        try
        {

            FXMLLoader familiarityInstructionLoader = new FXMLLoader();
            Pane familiarityInstructionRoot = (Pane) familiarityInstructionLoader.load(getClass().getResource("/Layouts/FamiliarityInstruction.fxml").openStream());

//            FamiliarityInstructionController adminController = (FamiliarityInstructionController) familiarityInstructionLoader.getController();

            Scene scene = new Scene(familiarityInstructionRoot);
            this.familiarityInstructionStage.setScene(scene);
            this.familiarityInstructionStage.setTitle("familiarityInstruction Point");
            this.familiarityInstructionStage.setResizable(false);
            this.familiarityInstructionStage.show();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void showFocusPoint(ActionEvent actionEvent)
    {
        focusPoint.showFocusPoint();
        focusPoint.setTimer();
//        focusPoint.close();

        Stage stage = (Stage)this.imageButton.getScene().getWindow();
        stage.close();
    }

}