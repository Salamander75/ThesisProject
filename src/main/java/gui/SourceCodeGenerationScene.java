package gui;

import controller.SceneController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import service.DependencyClass;

public class SourceCodeGenerationScene {

    private static final double sceneWidth = 700;

    private static final double sceneHeight = 375;

    private HBox topPaneContent;

    private static BorderPane sourceCodeGenerationSceneBorderPane = new BorderPane();

    private static Scene sourceCodeGenerationScene =
            new Scene(sourceCodeGenerationSceneBorderPane, sceneWidth, sceneHeight);

    private static SourceCodeGenerationScene sceneInstance = null;

    private SourceCodeGenerationScene() {
        populateTopPaneBorder(sourceCodeGenerationSceneBorderPane);
    }

    private void populateTopPaneBorder(BorderPane border) {
        topPaneContent = DependencyClass.getTopPanel().populateTopPaneBorder(border, true);
    }

    public static Scene getSourceCodeGenerationScene() {
        if (sceneInstance == null) {
            sceneInstance = new SourceCodeGenerationScene();
        }
        return sourceCodeGenerationScene;
    }

}
