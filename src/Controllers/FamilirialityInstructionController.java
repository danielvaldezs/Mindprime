package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FamilirialityInstructionController {

    Stage foucsPointStage = new Stage();
    FocusPointController focusPoint = new FocusPointController();
    // FamiliarityWordController familiarityWord = new FamiliarityWordController();

    public void StartFocusPoint(ActionEvent actionEvent) {
        try {
            FXMLLoader adminLoader = new FXMLLoader();
            Pane FsmilirislityRoot = adminLoader.load(getClass().getResource("../Layouts/FocusPoint.fxml").openStream());

            // adminController adminController = (adminController) adminLoader.getController();
            Scene scene = new Scene(FsmilirislityRoot);
            foucsPointStage.setScene(scene);
            foucsPointStage.setTitle("Punto de Enfoque");
            foucsPointStage.setResizable(false);
            foucsPointStage.show();
            focusPoint.setTimer();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showFocusPoint(ActionEvent actionEvent) {
        focusPoint.showFocusPoint();
        focusPoint.setTimer();
    }

    public void closeFamiliarityInstruction() {
        //  this.familiarityInstructionStage.close();
    }
if

    {
        select word
        from word
        where category = 'Positiva' and quantitySyllables = varNumSilabas
        order by random()
        limit 1;
    }
  else if

    {
        select word
        from word
        where category = 'Neutra' and quantitySyllables = varNumSilabas
        order by random()
        limit 1;
    }
    else if

    {
        select word
        from word
        where category = 'Negativa' and quantitySyllables = varNumSilabas
        order by random()
        limit 1;
    }


    public void addReport(){
        String sqlInsert = "INSERT INTO ActivityPriming (answerTime, movementTime, answer, idTest, idWord) VALUES (?,?,?,?,?)";
        try{
            Connection connect = DatabaseConnection.connect();
            PreparedStatement sqlStatement = connect.prepareStatement(sqlInsert);
            sqlStatement.setString(1,this.answerTime);
            sqlStatement.setString(2,this.movementTime);
            sqlStatement.setString(3,this.idTest);
            sqlStatement.setString(4,this.idWord);

            sqlStatement.execute();
            connect.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
