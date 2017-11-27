package gui;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.ElementModel;
import netscape.javascript.JSObject;
import service.DependencyClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Karl on 30.10.2017.
 */
public class WebViewWindow {

    private Stage window;
    private Scene scene;
    MyBrowser myBrowser;
    private final String viewTitle = "ThesisProject - WebView";
    private IMainViewCenterPanel iMainViewCenterPanel;

    public WebViewWindow(Stage stage) {
        this.window = stage;
        //     this.iMainViewCenterPanel = new MainViewCenterPanel();
    }

    public void display() {
        myBrowser = new MyBrowser();
        window.setTitle(viewTitle);
        scene = new Scene(myBrowser, 640, 480);
        window.setScene(scene);
        window.show();
    }

    class MyBrowser extends Region {

        HBox toolbar;

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        public MyBrowser(){

            String javascript = "";
            try {
                Scanner sc = new Scanner(new FileInputStream(new File("src/main/resources/elementController.js")));
                while (sc.hasNext()) {
                    javascript += sc.nextLine() + "\n";
                }
                sc.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //       System.out.println(javascript);
            String script = "var getHeadTag = document.getElementsByTagName('head')[0];" +
                    "var newScriptTag = document.createElement('script');" +
                    "newScriptTag.type='text/javascript';" +
                    "newScriptTag.src="+javascript+";" +
                    "getHeadTag.appendChild(newScriptTag);" +
                    "var css = '.highlight { background-color: yellow; }';" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    "style.appendChild(document.createTextNode(css));" +
                    "getHeadTag.appendChild(style);";


            webEngine.setJavaScriptEnabled(true);
            webEngine.load("http://www.urbandead.com/");
            String finalScript = script;
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                    if (t1 == Worker.State.SUCCEEDED) {
                        // this will be run as soon as WebView is initialized.
                        webEngine.executeScript(finalScript);
                        System.out.println("OUTPOST");
                        JSObject jsobj = (JSObject) webEngine.executeScript("window");
                        jsobj.setMember("gateway", new GateWay());
                    }
                }
            });

            webEngine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {
                @Override
                public void changed(ObservableValue<? extends Throwable> ov, Throwable t, Throwable t1) {
                    System.out.println("Received exception: "+t1.getMessage());
                }
            });

            webEngine.setOnAlert(e -> showAlert(e.getData()));
            webEngine.setConfirmHandler(message -> showConfirm(message));

            final TextField textField = new TextField ();
            textField.setPromptText("Hello! Who are?");

            Button buttonEnter = new Button("Enter");

            buttonEnter.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    webEngine.executeScript("updateHello();");
                }
            });

            Button buttonClear = new Button("Clear");
            buttonClear.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    webEngine.executeScript( "clearHello()" );
                }
            });


            toolbar = new HBox();
            toolbar.setPadding(new Insets(10, 10, 10, 10));
            toolbar.setSpacing(10);
            toolbar.setStyle("-fx-background-color: #336699");
            toolbar.getChildren().addAll(textField, buttonEnter, buttonClear);

            getChildren().add(toolbar);
            getChildren().add(webView);
        }

        @Override
        protected void layoutChildren(){
            double w = getWidth();
            double h = getHeight();
            double toolbarHeight = toolbar.prefHeight(w);
            layoutInArea(webView, 0, 0, w, h-toolbarHeight, 0, HPos.CENTER, VPos.CENTER);
            layoutInArea(toolbar, 0, h-toolbarHeight, w, toolbarHeight, 0, HPos.CENTER, VPos.CENTER);
        }

        private void showAlert(String message) {
            Dialog<Void> alert = new Dialog<>();
            alert.getDialogPane().setContentText(message);
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.showAndWait();
        }

        private boolean showConfirm(String message) {
            Dialog<ButtonType> confirm = new Dialog<>();
            confirm.getDialogPane().setContentText(message);
            confirm.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
            boolean result = confirm.showAndWait().filter(ButtonType.YES::equals).isPresent();

            // for debugging:
            System.out.println(result);

            return result ;
        }
    }

    public class GateWay {
        public void receiveUserSelection(String jsObject) {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(jsObject).getAsJsonObject();
            ElementModel elementModel = new ElementModel();
            elementModel.setId(obj.get("id").toString());
            elementModel.setClassName(obj.get("className").toString());
            elementModel.setName(obj.get("name").toString());
            elementModel.setSelector(obj.get("selector").toString());
            elementModel.setXpath(obj.get("xpath").toString());
            elementModel.setElementTagName(obj.get("tagName").toString().toLowerCase());
            System.out.println(obj.get("xpath").toString());
            DependencyClass.getCentralPanel().receiveElementObject(elementModel);
            //   iMainViewCenterPanel.receiveElementObject(elementModel);
            //   main.receiveElementObject(elementModel);
        }
    }
}
