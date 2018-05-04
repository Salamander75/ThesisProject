package gui.common;

import gui.HelpModal;
import gui.WebViewWindow;
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


        WebViewWindow webViewWindow = new WebViewWindow(webViewWindowStage);
        Hyperlink webView = new Hyperlink("Webview");
        webView.getStyleClass().add(topPanelComponentCssClass);
        webView.setVisited(false);
        webView.setOnAction(event -> {
            if(!webViewWindowStage.isShowing()) {
                webViewWindow.display();
            }
        });

        Hyperlink helpLink = new Hyperlink("Help");
        helpLink.getStyleClass().add(topPanelComponentCssClass);
        helpLink.setVisited(false);
        helpLink.setOnAction(e -> HelpModal.display());

        hbox.getChildren().addAll(helpLink, webView);
        return hbox;
    }
}
