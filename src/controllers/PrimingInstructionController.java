package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimingInstructionController {

        Stage primingInstructionStage = new Stage();

        public void showPrimingInstruction() {
            try {

                FXMLLoader primingInstructionLoader = new FXMLLoader();
                Pane primingInstructionRoot = (Pane) primingInstructionLoader.load(getClass().getResource("/Layouts/PrimingInstruction.fxml").openStream());

                AdminController adminController = (AdminController) primingInstructionLoader.getController();
                Scene scene = new Scene(primingInstructionRoot);
                this.primingInstructionStage.setScene(scene);
                this.primingInstructionStage.setTitle("familiarityInstruction Point");
                this.primingInstructionStage.setResizable(false);
                this.primingInstructionStage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void closeFamiliarityInstruction(){
            this.primingInstructionStage.close();
        }
}
