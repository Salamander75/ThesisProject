package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by karl on 23.11.2017.
 */
public class MainViewTopPanel implements  IMainViewTopPanel{

    @Override
    public HBox populateTopPaneBorder(BorderPane border, StackPane stackPane) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(2, 2, 2, 2));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #336699;");
        border.setTop(hbox);

        // Menus
        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Platform.exit());

        fileMenu.getItems().addAll(newFile, exit);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        ButtonBar buttonBar = new ButtonBar();
        Button openWebView = new Button("WebView");
        Stage webViewWindowStage = new Stage();
        WebViewWindow webViewWindow = new WebViewWindow(webViewWindowStage);
        openWebView.setOnAction(e -> {
            if(!webViewWindowStage.isShowing()) {
                webViewWindow.display();
            }
        });

        Button helpButton = new Button("Help");
        helpButton.setOnAction(e -> HelpModal.display());

        buttonBar.getButtons().addAll(openWebView, helpButton);

        hbox.getChildren().addAll(menuBar, buttonBar);
        hbox.getChildren().add(stackPane);
        return hbox;
    }
}
