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

    private String topPanelComponentCssClass = "top-panel-item";

    public HBox getCommonTopPanelGui(BorderPane border) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 2, 0, 2));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #5f495f;");
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

        WebViewWindow webViewWindow = new WebViewWindow(webViewWindowStage);
        Hyperlink webView = new Hyperlink("Webview");
        webView.getStyleClass().add(topPanelComponentCssClass);
        webView.setVisited(false);
        webView.setOnAction(event -> {
            if(!webViewWindowStage.isShowing()) {
                webViewWindow.display();
            }
        });

        Button helpButton = new Button("Help");
        helpButton.setOnAction(e -> HelpModal.display());

        buttonBar.getButtons().addAll(helpButton);

        hbox.getChildren().addAll(menuBar, buttonBar, webView);
        return hbox;
    }
}
