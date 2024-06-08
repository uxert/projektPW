package projekt_PW;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayDeque;

public class HelloApplication extends Application {

    private static HelloApplication appInstance; // there should always be only one instance of the application
    private Scene scene;
    public static HelloApplication getAppInstance()
    {
        return appInstance;
    }
    public Scene getScene()
    {
        return scene;
    }
    public void start(Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 320, 240);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void init()
    {
        appInstance = this;
    }

    public static void main(String[] args) {
        launch();
    }
}