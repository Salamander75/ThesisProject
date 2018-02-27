package gui.common;

import gui.HelpModal;
import gui.WebViewWindow;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CommonTopPanelComponents {

    private static Stage webViewWindowStage = new Stage();
    public HBox getCommonTopPanelGui(BorderPane border) {
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
        return hbox;
    }
}
