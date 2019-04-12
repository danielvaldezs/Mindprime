package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FinishedTestController {

    Stage finishedTestViewStage = new Stage();

    public void showFinishedTestView() {
        try {

            FXMLLoader finishedTestView = new FXMLLoader();
            Pane finishedTestViewRoot = (Pane) finishedTestView.load(getClass().getResource("/Layouts/FinishedTest.fxml").openStream());

//            FamiliarityInstructionController adminController = (FamiliarityInstructionController) familiarityInstructionLoader.getController();

            Scene scene = new Scene(finishedTestViewRoot);
            this.finishedTestViewStage.setScene(scene);
            this.finishedTestViewStage.setTitle("FinishedTest");
            this.finishedTestViewStage.setResizable(false);
            this.finishedTestViewStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
