package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimingWordController implements Initializable {

    Stage primingWordStage = new Stage();
    public Label word;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        word.setText("hola");
    }

    public void showPrimingWord(){
        try{

            FXMLLoader primingWordLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)primingWordLoader.load(getClass().getResource("/Layouts/FocusPoint.fxml").openStream());

//            AdminController adminController = (AdminController)primingWordLoader.getController();
            Scene scene = new Scene(focusPointRoot);
            this.primingWordStage.setScene(scene);
            this.primingWordStage.setTitle("Focus Point");
            this.primingWordStage.setResizable(false);
            this.primingWordStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
