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
            Pane primingActivity = (Pane) primingActivityLoader.load(getClass().getResource("../Layouts/PrimingActivity.fxml").openStream());

//            AdminController adminController = (AdminController)primingALoader.getController();
            Scene scene = new Scene(primingActivity);
            this.primingActivityStage.setScene(scene);
            this.primingActivityStage.setTitle("PrimingActivity");
            this.primingActivityStage.setResizable(false);
            this.primingActivityStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void closePrimingActivity(){
        this.primingActivityStage.close();
    }

}