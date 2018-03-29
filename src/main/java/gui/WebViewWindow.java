package gui;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
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
    private Button backButton;
    private Button forwardButton;

    private GateWay gateWay;

    public WebViewWindow(Stage stage) {
        this.window = stage;
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
        WebHistory history = webEngine.getHistory();


        public MyBrowser(){

            String javascript = "";
            toolbar = new HBox();
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
                    "newScriptTag.src=" + javascript + ";" +
                    "getHeadTag.appendChild(newScriptTag);" +
                    "var css = '.highlight { background-color: yellow; }';" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    "style.appendChild(document.createTextNode(css));" +
                    "getHeadTag.appendChild(style);";


            webEngine.setJavaScriptEnabled(true);
            webEngine.load("http://www.urbandead.com/");
            String finalScript = script;
            gateWay = new GateWay();
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                    if (t1 == Worker.State.SUCCEEDED) {
                        // this will be run as soon as WebView is initialized.
                        webEngine.executeScript(finalScript);
                        System.out.println("OUTPOST");
                        JSObject jsobj = (JSObject) webEngine.executeScript("window");
                        jsobj.setMember("gateway", gateWay);
                    }
                }
            });

            webEngine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {
                @Override
                public void changed(ObservableValue<? extends Throwable> ov, Throwable t, Throwable t1) {
                    showAlert("Received exception: " + t1.getMessage());
                }
            });



            webEngine.setOnAlert(e -> showAlert(e.getData()));
            webEngine.setConfirmHandler(message -> showConfirm(message));

            backButton = new Button();
            Image imageBack = new Image("file:src/main/resources/backIcon.png",10,10,true,true);
            backButton.setGraphic(new ImageView(imageBack));
            backButton.setDisable(true);

            backButton.setOnAction(e -> {
                goBackInHistory();
            });

            Image imageForward = new Image("file:src/main/resources/forwardIcon.png", 10, 10, true, true);
            forwardButton = new Button();
            forwardButton.setGraphic(new ImageView(imageForward));
            forwardButton.setDisable(true);

            forwardButton.setOnAction(e -> {
                goForwardInHistory();
            });
            final TextField urlBar = new TextField ();
            urlBar.setPrefWidth(500d);
            urlBar.setPromptText("Enter URL address... (with http:// or https:// ) and press Enter");

            urlBar.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent ke)
                {
                    if (ke.getCode().equals(KeyCode.ENTER))
                    {
                        System.out.println("history suurus: " + history.getEntries().size());
                        if (history.getEntries().size() == 1) {
                            backButton.setDisable(false);
                        }
                        webEngine.load(urlBar.getText());
                    }
                }
            });
       //     double toolBarWidth = toolbar.get
            Button buttonEnter = new Button("Search");

            buttonEnter.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    webEngine.getLocation();
                    webEngine.load(urlBar.getText());
                }
            });

            Button buttonClear = new Button("Clear");
            buttonClear.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent arg0) {
                    urlBar.clear();
                }
            });



            toolbar.setPadding(new Insets(5, 5, 5, 5));
            toolbar.setSpacing(10);
            toolbar.setStyle("-fx-background-color: #E6E6E6");
            toolbar.getChildren().addAll(backButton, forwardButton, urlBar, buttonEnter, buttonClear);

            getChildren().add(webView);
            getChildren().add(toolbar);
        }

        @Override
        protected void layoutChildren(){
            double w = getWidth();
            double h = getHeight();
            double toolbarHeight = toolbar.prefHeight(w);
            layoutInArea(toolbar, 0, 0, w, toolbarHeight, 0, HPos.CENTER, VPos.CENTER);
            layoutInArea(webView, 0, toolbarHeight, w, h-toolbarHeight, 0, HPos.CENTER, VPos.CENTER);

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
            return result ;
        }

        public void goBackInHistory()
        {
            history = webEngine.getHistory();
            ObservableList<WebHistory.Entry> entryList = history.getEntries();
            int currentIndex = history.getCurrentIndex();

            Platform.runLater(() ->
            {
                if (entryList.size() > 1 && currentIndex > 0) {
                    history.go(-1);
                    forwardButton.setDisable(false);
                } else {
                    backButton.setDisable(true);
                    history.go(0);
                }
            });
        }

        public void goForwardInHistory()
        {
            history = webEngine.getHistory();
            ObservableList<WebHistory.Entry> entryList = history.getEntries();
            int currentIndex = history.getCurrentIndex();

            Platform.runLater(() ->
            {
                if (entryList.size() > 1 && currentIndex < entryList.size() - 1) {
                    backButton.setDisable(false);
                    history.go(1);
                } else {
                    forwardButton.setDisable(true);
                    history.go(0);
                }
            });

        }
    }



    public class GateWay {
        public void receiveUserSelection(String jsObject) {
            JsonParser parser = new JsonParser();
            JsonObject obj = parser.parse(jsObject).getAsJsonObject();
            ElementModel elementModel = DependencyClass.getMainViewCentralPanel().getCurrentElementModel();
            elementModel.setId(obj.get("id").toString());
            elementModel.setClassName(obj.get("className").toString());
            elementModel.setName(obj.get("name").toString());
            elementModel.setSelector(obj.get("selector").toString());
            elementModel.setXpath(obj.get("xpath").toString());
            elementModel.setElementTagName(obj.get("tagName").toString().toLowerCase());
            elementModel.setElementTagType(obj.get("tagType").toString().toLowerCase());
            System.out.println("WEBVIEW TAG: " + elementModel.getElementTagName());
            System.out.println("WEBVIEW TYPE: " + elementModel.getElementTagType());
            DependencyClass.getMainViewCentralPanel().receiveElementObject(elementModel);
        }
    }
}
