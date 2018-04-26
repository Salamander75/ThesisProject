package gui;

import controller.SceneController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private final String title = "ThesisProject ProtoType";

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (IllegalStateException e) {}
    }

    public Main() {}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(title);
        URL url = getClass().getResource("/generator.jpg");
        System.out.println(url.toString());
        primaryStage.getIcons().add(new Image(url.toExternalForm()));
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        SceneController.setPrimaryStage(primaryStage);
        SceneController.setCurrentScene(MainViewScene.getMainViewScene());
    }
}
