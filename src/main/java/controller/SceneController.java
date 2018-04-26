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
        scene.getStylesheets().add("/generalStyle.css");
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public static Stage getCurrentStage() {
        return primaryStage;
    }







}
