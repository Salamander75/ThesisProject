package gui;

import domLoader.DomLoader;
import domLoader.ElementFinder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
      //  launch(args);
   //     DomLoader.readUrlHtmlSourceCode();
      //  ElementFinder finder = new ElementFinder();

        open("http://www.urbandead.com/");

    }
}
