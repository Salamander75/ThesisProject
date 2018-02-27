package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {


    private static Stage primaryStage;

    private static Scene currentScene;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void setCurrentScene(Scene scene) {
        currentScene = scene;
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }







}
