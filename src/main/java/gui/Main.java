package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.DependencyClass;

public class Main extends Application {

    private final String title = "ThesisProject ProtoType";
    private VBox leftPaneContent;
    private GridPane centerPaneContent;
    private HBox topPaneContent;

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (IllegalStateException e) {}
    }

    public Main() {}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(new Image("file:src/main/resources/generator.jpg"));
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        BorderPane border = new BorderPane();
        StackPane stack = new StackPane();
        populateTopPaneBorder(border, stack);
        populateBottomPaneBorder(border);
        populateLeftPaneBorder(border);
        populateCenterPaneBorder(border);
        Scene mainScene = new Scene(border, 700, 375);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void populateTopPaneBorder(BorderPane border, StackPane stackPane) {
        topPaneContent = DependencyClass.getTopPanel().populateTopPaneBorder(border, stackPane);
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
}
