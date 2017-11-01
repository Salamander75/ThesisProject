package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ElementModel;

import java.io.IOException;
import static com.codeborne.selenide.Selenide.open;

public class Main extends Application {

    private Button openAlertBox;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ThesisProject ProtoType");
        openAlertBox = new Button("Open Alert");
        Stage webViewWindowStage = new Stage();
        WebViewWindow webViewWindow = new WebViewWindow(webViewWindowStage, this);
        openAlertBox.setOnAction(e -> {
            if(!webViewWindowStage.isShowing()) {
                webViewWindow.display();
            }
        });
        VBox vboxLayout = new VBox(20);
        vboxLayout.getChildren().add(openAlertBox);
        Scene mainScene = new Scene(vboxLayout, 300, 275);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public void receiveElementObject(ElementModel model) {
        System.out.println(model.getId());
        System.out.println(model.getClassName());
        System.out.println(model.getName());
        System.out.println(model.getSelector());
        System.out.println(model.getXpath());
    }
}
