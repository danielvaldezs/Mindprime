package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimingActivityController {

    Stage primingActivityStage = new Stage();


    public void showPrimingActivity(){
        try{

            FXMLLoader primingActivityLoader = new FXMLLoader();
            Pane familiarityWord = (Pane)primingActivityLoader.load(getClass().getResource("../Layouts/PrimingActivity.fxml").openStream());

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
