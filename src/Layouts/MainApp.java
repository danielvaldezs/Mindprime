package Layouts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal del proyecto, inicia con la ejecución de la aplicación
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = (Parent) FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MindPrime Login");
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
