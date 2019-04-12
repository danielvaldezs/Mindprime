package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class PrimingInstructionController
{

	public Button imageButton;
    Stage primingInstructionStage = new Stage();
    FocusPointController focusPoint = new FocusPointController();
    PrimingWordController primingWord = new PrimingWordController();
    
    public PrimingInstructionController()
    {
    	
    }


    public void showPrimingInstruction()
    {
        try
        {

            FXMLLoader primingInstructionLoader = new FXMLLoader();
            Pane primingInstructionRoot = (Pane) primingInstructionLoader.load(getClass().getResource("/Layouts/PrimingInstruction.fxml").openStream());

//            AdminController adminController = (AdminController) primingInstructionLoader.getController();
            Scene scene = new Scene(primingInstructionRoot);
            this.primingInstructionStage.setScene(scene);
            this.primingInstructionStage.setTitle("Priming Instruction Point");
            this.primingInstructionStage.setResizable(false);
            this.primingInstructionStage.show();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void showFocusPoint(ActionEvent actionEvent)
    {
        focusPoint.showFocusPoint();
        focusPoint.setTimer2();
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();
//        Stage stage = (Stage)imageButton.getScene().getWindow();


//        focusPoint.close();

//        Stage stage = (Stage)this.imageButton.getScene().getWindow();
//        stage.close();
    }

//    public void closeFamiliarityInstruction(){
//        this.primingInstructionStage.close();
//    }
}