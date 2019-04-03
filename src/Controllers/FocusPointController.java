package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FocusPointController implements Initializable {

    public Circle circlePoint;
    Stage focusPointStage = new Stage();
    FamiliarityWordController familiarityWord = new FamiliarityWordController();
    PrimingWordController primingWord = new PrimingWordController();

    public FocusPointController() throws SQLException {
    }

    public void showFocusPoint(){
        try{

            FXMLLoader focusPointLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)focusPointLoader.load(getClass().getResource("/Layouts/FocusPoint.fxml").openStream());

//            AdminController adminController = (AdminController)focusPointLoader.getController();
            Scene scene = new Scene(focusPointRoot);
            this.focusPointStage.setScene(scene);
            this.focusPointStage.setTitle("Focus Point");
            this.focusPointStage.setResizable(false);
            this.focusPointStage.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //Metodo que despues de cierto tiempo, te lleva de la vista focus point
    //a la vista FamiliarityWord
    public void setTimer() throws InterruptedException {
//        Timeline contador = new Timeline(new KeyFrame(
//                Duration.seconds(3),
//                accion -> familiarityWord.showFamiliarityWord()));
//        contador.play();
        Thread.sleep(3000);
        familiarityWord.showFamiliarityWord();
        focusPointStage.close();
    }

    public void setTimer2() throws InterruptedException {
        Thread.sleep(3000);
        primingWord.showPrimingWord();
        focusPointStage.close();
    }

    public void close(){
        this.focusPointStage.close();
        primingWord.showPrimingWord();
        focusPointStage.close();
    }

//    @Override
//    public void setScreenParent(ScreensController screenParent) {
//        myController = screenParent;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Metodo que debe cerrar la vista de focus point
    //aun no funciona, falta implementar bien la navegacion entre vistas
    //    public void closeFocusPoint(){
    //        this.focusPointStage.close();
    //    }


}