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

public class FocusPointController implements Initializable
{

    public Circle circlePoint;
    public Stage focusPointStage = new Stage();
    public FamiliarityWordController familiarityWord = new FamiliarityWordController();
    public PrimingWordController primingWord = new PrimingWordController();

    public FocusPointController()
    {

    }

    public void showFocusPoint()
    {
        try
        {

            FXMLLoader focusPointLoader = new FXMLLoader();
            Pane focusPointRoot = (Pane)focusPointLoader.load(getClass().getResource("/Layouts/FocusPoint.fxml").openStream());

//            AdminController adminController = (AdminController)focusPointLoader.getController();
            Scene scene = new Scene(focusPointRoot);
            this.focusPointStage.setScene(scene);
            this.focusPointStage.setTitle("Focus Point");
            this.focusPointStage.setResizable(false);
            this.focusPointStage.show();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    //Metodo que despues de cierto tiempo, te lleva de la vista focus point
    //a la vista FamiliarityWord
    public void setTimer()
    {
        try
        {
            Thread.sleep(3000);
            familiarityWord.showFamiliarityWord();
            focusPointStage.close();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }

    public void setTimer2()
    {
        try
        {
            Thread.sleep(3000);
            primingWord.showPrimingWord();
            focusPointStage.close();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}