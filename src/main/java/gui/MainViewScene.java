package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import service.DependencyClass;

public class MainViewScene {

    private VBox leftPaneContent;
    private GridPane centerPaneContent;
    private HBox topPaneContent;

    private static final double sceneWidth = 700;

    private static final double sceneHeight = 375;

    private static BorderPane mainSceneBorderPane = new BorderPane();

    private static Scene mainViewScene = new Scene(mainSceneBorderPane, sceneWidth, sceneHeight);

    private static MainViewScene sceneInstance = null;

    private MainViewScene() {
        populateTopPaneBorder(mainSceneBorderPane);
        populateBottomPaneBorder(mainSceneBorderPane);
        populateLeftPaneBorder(mainSceneBorderPane);
        populateCenterPaneBorder(mainSceneBorderPane);
    }

    private void populateTopPaneBorder(BorderPane border) {
        topPaneContent = DependencyClass.getTopPanel().populateTopPaneBorder(border, false);
    }

    private void populateBottomPaneBorder(BorderPane border) {
        HBox hbox = addHBox(12, 15, 12, 15);
        border.setBottom(hbox);
    }

    private void populateLeftPaneBorder(BorderPane border) {
        leftPaneContent = DependencyClass.getLeftPanel().addLeftPaneVBox();
        border.setLeft(leftPaneContent);
    }

    private void populateCenterPaneBorder(BorderPane border) {
        centerPaneContent = DependencyClass.getCentralPanel().addCenterPanel();
        border.setCenter(centerPaneContent);
    }

    private HBox addHBox(double top, double right, double bottom, double left) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(top, right, bottom, left));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #336699;");
        return hbox;
    }

    public static Scene getMainViewScene() {
        if (sceneInstance == null) {
            sceneInstance = new MainViewScene();
        }
        return mainViewScene;
    }
}
