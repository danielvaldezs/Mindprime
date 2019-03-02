package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.*;






public class adminController {
   Stage focusPoint = new Stage();
    private ActionEvent event;
    private adminController adminController;


    public void startFamiliarityInstruction(ActionEvent actionEvent) {
        try {
            FXMLLoader adminLoader = new FXMLLoader();
            Pane FocusPoint = adminLoader.load(getClass().getResource("../Layouts/FamilirialityInstruction.fxml").openStream());

          //  adminController adminController = (adminController) adminLoader.getController();
            Scene scene = new Scene(FocusPoint);
            focusPoint.setScene(scene);
            focusPoint.setTitle("Instrucciones");
            focusPoint.setResizable(false);
            focusPoint.show();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /*
  Este metodo es para validar que se haya escrito algo que no sean letras, así mismo tampoco permite dejar el campo vacío
   Tambien esta el metodo de en caso que el usuario deje el espacio en blanco le marque un error
           */

    public void JustLetters (KeyEvent evt) {
        char c = evt.getCharacter().charAt(0);
        TextField txt = (TextField) evt.getSource();
        if (!Character.isLetter(c)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingrese solo letras");
        } else if (Character.equals(c) == (""))
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "No puede dejar el campo vacío")
        }
    }


    public void NoBlankSpaces(KeyEvent evt){
        char c =evt.getCharacter().charAt(0);
        TextField txt = (TextField) evt.getSource();
        if (Character.equals(c) == (""))
        {
            evt.consume();
            JOptionPane.showMessageDialog(this, "No puede dejar el campo vacío")
        }
    }

    /*
    Este es un metodo diferente para no dejar espacios en blanco
     */

    public void NoBlankSpaces(KeyEvent evt){
        char c =evt.getCharacter().charAt(0);
        TextField txt = (TextField) evt.getSource();
            if(Character == null) || (Character.isEmpty(c))) {
                evt.consume();
                JOptionPane.showMessageDialog(this, "No puede dejar el campo vacío")
            }
    }


    /* Mediante este metodo se valida que la fecha que haya ingresado el usuario este en el formato de año/mes/dia
     */
    public void ValidateDate(KeyEvent evt){
        char c = evt.getCharacter().charAt(0);
        TextField txt = (TextField) evt.getSource();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            dateFormat.parse(Character.trim(c));

        }
        catch(ParseException pe)
        {
            JOptionPane.showMessageDialog(this,"No puede dejar el campo vacío")
        }
    }

    public void{

    }

if(JRadioButton1.isSelected()==false && JRadioButton2.isSelected()==false)
    {
        JOptionPane.showMessageDialog(null,"No selecciono ninguna opción");
    }



ppublic void ValidateFields (KeyEvent evt)
{
    TextField txt = (TextField) evt.getSource();
        if(nameId.getText().equals == ("") &&  lastnameId.getText().equals == ("") && birthDateId.getText().equals == ("") && institutionId.getText().equals == ("")
    {
        JOptionPane.showMessageDialog(null,"Se tienen que completar todos los campos ");
    }
}

}
