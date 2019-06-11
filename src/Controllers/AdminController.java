package Controllers;

import DatabaseConnection.dbConnection;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML private TextField nameId;
    @FXML private TextField lastnameId;
    @FXML private TextField institutionId;
    @FXML private RadioButton rbTeptAsiId;
    @FXML private RadioButton rbCHId;
    @FXML private RadioButton rbCFId;
    @FXML private ListView listId;
    @FXML DatePicker datePickerId;
    @FXML ToggleGroup categoryUserGroup;

    Connection connect;

    ObservableList<User> userObservableList;
    ArrayList<User> userList = new ArrayList<User>();
    int selectedUserId = 100000;
    int currentUserId=0;
    FamiliarityInstructionController familiarityInstruction = new FamiliarityInstructionController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchUser();
    }

    public AdminController() throws SQLException {
    }

//    Stage focusPointStage = new Stage();

    public void startActivity(ActionEvent actionEvent) {
        addUser();
    }

    private String getUserGroup(){
        RadioButton selectedRadioButton = (RadioButton) categoryUserGroup.getSelectedToggle();
        String stringUserGroupSelected = selectedRadioButton.getText();
        return stringUserGroupSelected;
    }
    //Método para agregar usuario a la base de datos
    public void addUser(){
        String first_name = nameId.getText();
        String last_name = lastnameId.getText();
        String institution = institutionId.getText();
        String group = getUserGroup();
        String birthdate = datePickerId.getValue().toString();

        try{
            if(!first_name.equals("") && !last_name.equals("") && !institution.equals("") && !birthdate.equals("") && !group.equals("")){
                connect = dbConnection.getConnection();

                if(!userExists(first_name, last_name)){
                    String sqlInsert = "INSERT INTO User (firstName, lastName, institution,userGroup,birthdate) VALUES (?,?,?,?,?)";

                    PreparedStatement sqlStatement = connect.prepareStatement(sqlInsert);
                    sqlStatement.setString(1,first_name);
                    sqlStatement.setString(2,last_name);
                    sqlStatement.setString(3,institution );
                    sqlStatement.setString(4,group);
                    sqlStatement.setString(5,datePickerId.getValue().toString());

                    sqlStatement.execute();
                }else{
                    System.out.println("EL USUARIO YA EXISTE");
                }
                createTest();
                showInstruction();
                connect.close();
            }else{
                System.out.println("Todos los campos deben llenarse");
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
    }
    public void createTest(){
        String sqlSelectId = "select idUser from user where (firstName = ?) and (lastName = ?) and (institution = ?)";

        try{
            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelectId);
            preparedStatement.setString(1, nameId.getText());
            preparedStatement.setString(2, lastnameId.getText());
            preparedStatement.setString(3, institutionId.getText());

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                currentUserId = rs.getInt("idUser");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        String sqlInsertTest = "Insert into test(correctAnswers,dateDone, idUser) values (0,date('now'), ?)";
        try{
            PreparedStatement prepareStatement = connect.prepareStatement(sqlInsertTest);
            prepareStatement.setInt(1, currentUserId);

            prepareStatement.executeUpdate();

//            connect.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Metodo para limpiar los campos (textfield) de informacion del usuario
    public void clearFields(){
        this.nameId.setText("");
        this.lastnameId.setText("");
        this.datePickerId.getEditor().clear();
        this.institutionId.setText("");

        rbTeptAsiId.setSelected(false);
        rbCHId.setSelected(false);
        rbCFId.setSelected(false);

        searchUser();

    }

    public void searchUserEventK(KeyEvent keyEvent) {
        searchUser();
    }

    public void searchUserEventA(ActionEvent actionEvent) {
        searchUser();
    }

    //Metodo para verificar si ya existe un usuario en la base de datos
    public boolean userExists(String first_name, String last_name){
        boolean user_exists = false;
        String sqlDuplicityUser = "SELECT * from user where firstName = '" + first_name + "'"
                + " and lastName = '" + last_name + "'";
        try{
            ResultSet rs = connect.createStatement().executeQuery(sqlDuplicityUser);
            if(rs.next()){
                user_exists = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user_exists;
    }

    //Metodo para verificar que en los campos nombre, apellido e institucion solo se permitan letras y espacios
    public void onlyLettersAndSpaces(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        TextField tx = (TextField) keyEvent.getSource();
        if(!Character.isLetter(c) && !Character.isWhitespace(c)){
            keyEvent.consume();
        }
    }

    public void searchUser(){
        userList.clear();
        String grupo;
        if(rbCFId.isSelected() || rbCHId.isSelected() || rbTeptAsiId.isSelected()){
            grupo = getUserGroup();
        } else{
            grupo="";
        }
        try{
            Connection connect = dbConnection.getConnection();

            String sqlSelect = "select idUser, firstName, lastName, institution, userGroup, birthdate\n" +
                    "from user\n" +
                    "where (firstName like ?) and (lastName like ? ) and\n" +
                    "      (institution like ?) and (userGroup like ?) ";

            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            preparedStatement.setString(1,"%" + nameId.getText() + "%");
            preparedStatement.setString(2,"%" + lastnameId.getText() + "%");
            preparedStatement.setString(3,"%" + institutionId.getText() + "%");
            preparedStatement.setString(4, "%" + grupo + "%");

            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()){
                userList.add(new User(rs.getInt("idUser"),rs.getString("firstname"),rs.getString("lastname"),
                        rs.getString("institution"),rs.getString("usergroup"),
                        rs.getString("birthdate")));
            }

            connect.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        createUserList(userList);
    }

    public void createUserList(ArrayList<User> userList){
        listId.getItems().clear();
        userObservableList= FXCollections.observableArrayList();
        for (int i = 0; i < userList.size(); i++) {
            userObservableList.add(userList.get(i));
        }
        listId.getItems().addAll(userObservableList);
    }

    public void clickUserList(){
        listId.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Obtener index del item seleccionado en el listview
                ObservableList index= listId.getSelectionModel().getSelectedIndices();
                //Convertirlo de observablelist a un string
                String indexString = index.toString();
                //Remplazar corchetes del index
                String indexNumber = indexString.replace("[" ,"").replace("]","");
                //Convertir a entero el index
                int indexNumeric = Integer.parseInt(indexNumber);

                //Llenar los datos
                nameId.setText(userList.get(indexNumeric).getFirstName());
                lastnameId.setText(userList.get(indexNumeric).getLastName());
                institutionId.setText(userList.get(indexNumeric).getInstitution());

                if(userList.get(indexNumeric).getGroup().equals(rbTeptAsiId.getText())){
                    rbTeptAsiId.setSelected(true);
                }else if(userList.get(indexNumeric).getGroup().equals(rbCFId.getText())){
                    rbCFId.setSelected(true);
                }else if(userList.get(indexNumeric).getGroup().equals(rbCHId.getText())){
                    rbCHId.setSelected(true);
                }
                datePickerId.setValue(LOCAL_DATE(userList.get(indexNumeric).getBirthDate()));

                selectedUserId = userList.get(indexNumeric).getId();
            }
        });
    }

    public static final LocalDate LOCAL_DATE(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public void editUser(ActionEvent actionEvent) {
        try{
            Connection connect = dbConnection.getConnection();

            String sqlSelect = "UPDATE user set firstName=?, lastName=?, institution=?, userGroup=?, birthdate=? WHERE idUser=?";

            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);

            preparedStatement.setString(1, nameId.getText());
            preparedStatement.setString(2, lastnameId.getText());
            preparedStatement.setString(3, institutionId.getText());
            preparedStatement.setString(4, getUserGroup());
            preparedStatement.setString(5, datePickerId.getValue().toString());
            preparedStatement.setInt(6, selectedUserId);

            preparedStatement.executeUpdate();

            connect.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sujeto Editado");
        alert.setContentText("Sujeto ha sido editado exitosamente");
        alert.setHeaderText(null);
        alert.showAndWait();
        searchUser();
    }

    public void deleteUser(ActionEvent actionEvent) {
        try{
            Connection connect = dbConnection.getConnection();
            String sqlSelect = "DELETE FROM user WHERE idUser = ?";

            PreparedStatement preparedStatement = connect.prepareStatement(sqlSelect);
            preparedStatement.setInt(1, selectedUserId);
            preparedStatement.executeUpdate();

          connect.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        clearFields();
        searchUser();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sujeto eliminado");
        alert.setContentText("Sujeto ha sido eliminado exitosamente");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    //Metodo para mostrar la vista de la instruccion de familiaridad
    public void showInstruction(){
        familiarityInstruction.showFamiliarityInstruction();
    }

    //Metodo para mostrar la vista de agregar una nueva palabra
    public void addNewWord(ActionEvent actionEvent) {
        AddNewWordController addNewWord = new AddNewWordController();
        addNewWord.showAddNewWord();
    }
}
