package Controllers;

import DatabaseConnection.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinishedTestController {

    Stage finishedTestViewStage = new Stage();
    int lastTest = 0;
    HSSFWorkbook workbook;


    public void showFinishedTestView() {
        try {

            FXMLLoader finishedTestView = new FXMLLoader();
            Pane finishedTestViewRoot = (Pane) finishedTestView.load(getClass().getResource("/Layouts/FinishedTest.fxml").openStream());

            Scene scene = new Scene(finishedTestViewRoot);
            this.finishedTestViewStage.setScene(scene);
            this.finishedTestViewStage.setTitle("FinishedTest");
            this.finishedTestViewStage.setResizable(false);
            this.finishedTestViewStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void generateStatisticsButton(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setInitialFileName(getLastUserInformation());

        //Show save file dialog
        File file = fileChooser.showSaveDialog(finishedTestViewStage);
        String fileAsString = file.toString();
        System.out.println(fileAsString);

        generateStatistics(fileAsString);
    }

    public void generateStatistics(String fileName) throws IOException {
        getLastUserInformation();
        generateFirstSheet();
        generateSecondSheet();
        generateThirdSheet();
        String urlFile = fileName;
        try{
            FileOutputStream fileOut = new FileOutputStream(urlFile);
            workbook.write(fileOut);
            fileOut.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Verifique que el archivo " + urlFile + " no está siendo usado");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public String getLastUserInformation(){
        Connection connect = dbConnection.getConnection();
        String sqlSelect = "select idTest from Test order by idTest DESC limit 1;";
        String userFirstName = "";
        String userLastName = "";
        String testDateDone = "";

        try{
            PreparedStatement ps = connect.prepareStatement(sqlSelect);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               lastTest = rs.getInt("idTest");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        String sqlSelectUser = "select u.idUser ,u.firstName, u.lastName, t.dateDone from user u join test t on u.idUser = t.idUser" ;
        try{
            PreparedStatement ps = connect.prepareStatement(sqlSelectUser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                userFirstName= rs.getString("firstname");
                userLastName = rs.getString("lastname");
                testDateDone= rs.getString("dateDone");

            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        String userInformation = userFirstName + " " + userLastName + " " + testDateDone;
        return userInformation;
    }

    public void generateFirstSheet(){
        String sqlQuery = "Select * from user u where u.idUser = (select idUser from Test order by idUser DESC limit 1);";
        try{
            Connection connect = dbConnection.getConnection();
            PreparedStatement ps = connect.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();

            workbook = new HSSFWorkbook();
            HSSFSheet sheet1 = workbook.createSheet("Información Usuario");
            HSSFRow rowhead = sheet1.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("Usuario Id");
            rowhead.createCell((short) 1).setCellValue("Nombre");
            rowhead.createCell((short) 2).setCellValue("Apellido");
            rowhead.createCell((short) 3).setCellValue("Institución");
            rowhead.createCell((short) 4).setCellValue("Grupo");
            int i = 1;
            while (rs.next()){
                HSSFRow row = sheet1.createRow((short) i);
                row.createCell((short) 0).setCellValue(rs.getString("idUser"));
                row.createCell((short) 1).setCellValue(rs.getString("firstName"));
                row.createCell((short) 2).setCellValue(rs.getString("lastName"));
                row.createCell((short) 3).setCellValue(rs.getString("institution"));
                row.createCell((short) 4).setCellValue(rs.getString("userGroup"));
                i++;
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void generateSecondSheet(){
        String sqlQuery = "select w.idWord, w.word, w.category ,f.score from Familiarity f\n" +
                "join Word w on w.idWord = f.idWord\n" +
                "where f.idTest = (select Test.idTest from Test order by idUser DESC limit 1);";
        try{
            Connection connect = dbConnection.getConnection();
            PreparedStatement ps = connect.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();

            HSSFSheet sheet2 = workbook.createSheet("Familiaridad");
            HSSFRow rowhead2 = sheet2.createRow((short) 0);
            rowhead2.createCell((short) 0).setCellValue("Palabra Id");
            rowhead2.createCell((short) 1).setCellValue("Palabra");
            rowhead2.createCell((short) 2).setCellValue("Categoria");
            rowhead2.createCell((short) 3).setCellValue("Nivel Familiaridad");
            int j = 1;
            while (rs.next()){
                HSSFRow row2 = sheet2.createRow((short) j);
                row2.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("idWord")));
                row2.createCell((short) 1).setCellValue(rs.getString("word"));
                row2.createCell((short) 2).setCellValue(rs.getString("category"));
                row2.createCell((short) 3).setCellValue(rs.getString("score"));
                j++;
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void generateThirdSheet(){
//        String sqlQuery = "select W.idWord, W.word, W.category, ap.answer, ap.movementTime, ap.answerTime from ActivityPriming ap\n" +
//                "join Word W on ap.idWord = W.idWord\n" +
//                "where ap.idTest =(select t.idTest from Test t order by idUser DESC limit 1);";
//
        String sqlQuery = "select W.idWord, W.word, W.category, ap.answer, ap.answerTime from ActivityPriming ap\n" +
                "join Word W on ap.idWord = W.idWord\n" +
                "where ap.idTest =(select t.idTest from Test t order by idUser DESC limit 1);";

        try{
            Connection connect = dbConnection.getConnection();
            PreparedStatement ps = connect.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();

            HSSFSheet sheet3 = workbook.createSheet("Actividad Priming");
            HSSFRow rowhead3 = sheet3.createRow((short) 0);
            rowhead3.createCell((short) 0).setCellValue("Palabra Id");
            rowhead3.createCell((short) 1).setCellValue("Palabra");
            rowhead3.createCell((short) 2).setCellValue("Categoria");
            rowhead3.createCell((short) 3).setCellValue("Respuesta");
//            rowhead3.createCell((short) 4).setCellValue("Tiempo de Movimiento");
            rowhead3.createCell((short) 4).setCellValue("Tiempo de Respuesta");
            int j = 1;
            while (rs.next()){
                HSSFRow row3 = sheet3.createRow((short) j);
                row3.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("idWord")));
                row3.createCell((short) 1).setCellValue(rs.getString("word"));
                row3.createCell((short) 2).setCellValue(rs.getString("category"));
                row3.createCell((short) 3).setCellValue(rs.getBoolean("answer"));
//                row3.createCell((short) 4).setCellValue(rs.getDouble("movementTime"));
                row3.createCell((short) 4).setCellValue(rs.getDouble("answerTime"));
                j++;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
