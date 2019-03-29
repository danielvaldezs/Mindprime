package Controllers;

import javafx.animation.KeyFrame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;

public class PrimingActivityController {
    PrimingActivityController primingActivity = new PrimingActivityController();
    Stage primingActivityStage = new Stage();


    public void showPrimingActivity(){
        try{

            FXMLLoader primingActivityLoader = new FXMLLoader();
            Pane primingActivityRoot = (Pane)primingActivityLoader.load(getClass().getResource("/Layouts/PrimingActivity.fxml").openStream());

//            AdminController adminController = (AdminController)focusPointLoader.getController();
            Scene scene = new Scene(primingActivityRoot);
            this.primingActivityStage.setScene(scene);
            this.primingActivityStage.setTitle("Priming Activity ");
            this.primingActivityStage.setResizable(false);
            this.primingActivityStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }