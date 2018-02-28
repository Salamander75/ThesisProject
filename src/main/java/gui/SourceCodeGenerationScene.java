package gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.DependencyClass;

public class SourceCodeGenerationScene {

    private static final double sceneWidth = 700;

    private static final double sceneHeight = 375;

    private HBox topPaneContent;
    private GridPane centerPaneContent;
    private VBox rightPaneContent;

    private static BorderPane sourceCodeGenerationSceneBorderPane = new BorderPane();

    private static Scene sourceCodeGenerationScene =
            new Scene(sourceCodeGenerationSceneBorderPane, sceneWidth, sceneHeight);

    private static SourceCodeGenerationScene sceneInstance = null;

    private SourceCodeGenerationScene() {
        populateTopPaneBorder(sourceCodeGenerationSceneBorderPane);
        populateCentralPaneBorder(sourceCodeGenerationSceneBorderPane);
        populateRightPaneBorder(sourceCodeGenerationSceneBorderPane);
    }

    private void populateTopPaneBorder(BorderPane border) {
        topPaneContent = DependencyClass.getMainViewTopPanel().populateTopPaneBorder(border, true);
    }

    private void populateCentralPaneBorder(BorderPane border) {
        centerPaneContent = DependencyClass.getGenerationSceneCentralPanel().addCenterPanel();
        border.setCenter(centerPaneContent);
    }

    private void populateRightPaneBorder(BorderPane border) {
        rightPaneContent = DependencyClass.getGenerationSceneRightPanel().addRightPanel();
        border.setRight(rightPaneContent);
    }

    public static Scene getSourceCodeGenerationScene() {
        if (sceneInstance == null) {
            sceneInstance = new SourceCodeGenerationScene();
        }
        return sourceCodeGenerationScene;
    }

}
