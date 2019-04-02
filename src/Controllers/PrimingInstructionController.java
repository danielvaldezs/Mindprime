package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class PrimingInstructionController {

	public Button imageButton;
    Stage primingInstructionStage = new Stage();
    FocusPointController focusPoint = new FocusPointController();
    PrimingWordController primingWord = new PrimingWordController();
    
    public PrimingInstructionController() throws SQLException {
    	
    }


    public void showPrimingInstruction() {
        try {

            FXMLLoader primingInstructionLoader = new FXMLLoader();
            Pane primingInstructionRoot = (Pane) primingInstructionLoader.load(getClass().getResource("/Layouts/PrimingInstruction.fxml").openStream());

//            AdminController adminController = (AdminController) primingInstructionLoader.getController();
            Scene scene = new Scene(primingInstructionRoot);
            this.primingInstructionStage.setScene(scene);
            this.primingInstructionStage.setTitle("Priming Instruction Point");
            this.primingInstructionStage.setResizable(false);
            this.primingInstructionStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void showFocusPoint(ActionEvent actionEvent) throws InterruptedException {
        focusPoint.showFocusPoint();
        focusPoint.setTimer2();
//        focusPoint.close();

        Stage stage = (Stage)this.imageButton.getScene().getWindow();
        stage.close();
    }

//    public void closeFamiliarityInstruction(){
//        this.primingInstructionStage.close();
//    }
}