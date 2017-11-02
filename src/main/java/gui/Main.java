package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ElementModel;

public class Main extends Application {

    private Button openWebView;
    private TextField elementNameInput;
    private TextField elementIdInput;
    private TextField elementClassInput;
    private TextField elementNameAttrInput;
    private TextField elementSelectorInput;
    private TextField elementXPathInput;
    private final String title = "ThesisProject ProtoType";
    private ElementModel elementModel;


    public static void main(String[] args) {
        try {
            launch(args);
        } catch (IllegalStateException e) {}
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(new Image("file:src/main/resources/generator.jpg"));
        openWebView = new Button("WebView");
        Stage webViewWindowStage = new Stage();
        WebViewWindow webViewWindow = new WebViewWindow(webViewWindowStage, this);

        openWebView.setOnAction(e -> {
            if(!webViewWindowStage.isShowing()) {
                webViewWindow.display();
            }
        });

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
        HBox hbox = addHBox(2, 2, 2, 2);
        border.setTop(hbox);
   //     openAlertBox.setPrefSize(100, 20);

        // Menus
        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Platform.exit());

        fileMenu.getItems().addAll(newFile, exit);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        ButtonBar buttonBar = new ButtonBar();
        Button helpButton = new Button("Help");
        helpButton.setOnAction(e -> HelpModal.display());
        buttonBar.getButtons().addAll(openWebView, helpButton);


        hbox.getChildren().addAll(menuBar, buttonBar);
        hbox.getChildren().add(stackPane);
    }

    private void populateBottomPaneBorder(BorderPane border) {
        HBox hbox = addHBox(12, 15, 12, 15);
        border.setBottom(hbox);
    }

    private void populateLeftPaneBorder(BorderPane border) {
        VBox vBox = addVBox();
        border.setLeft(vBox);
    }

    private void populateCenterPaneBorder(BorderPane border) {
        border.setCenter(addGridPane());
    }

    private HBox addHBox(double top, double right, double bottom, double left) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(top, right, bottom, left));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #336699;");
        return hbox;
    }

    private VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n";
        vbox.setStyle(cssLayout);

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);
        Hyperlink options[] = new Hyperlink[] {
                new Hyperlink("Sales"),
                new Hyperlink("Marketing"),
                new Hyperlink("Distribution"),
                new Hyperlink("Costs")};

        for (int i=0; i<4; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        return vbox;
    }

    private GridPane addGridPane() {
        GridPane gridPane = new GridPane();
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n" +
                "-fx-background-color: #FFFFFF;\n";
        gridPane.setStyle(cssLayout);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(false);

        Text elementNameLabel = new Text("Element Name : ");
        elementNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        gridPane.add(elementNameLabel, 1, 1);

        Text elementIdLabel = new Text("ID : ");
        elementIdLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        gridPane.add(elementIdLabel, 1, 2);

        Text elementClassLabel = new Text("Class : ");
        elementClassLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        gridPane.add(elementClassLabel, 1, 3);

        Text elementNameAttributeLabel = new Text("Name attr. : ");
        elementNameAttributeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        gridPane.add(elementNameAttributeLabel, 1, 4);

        Text elementSelectorLabel = new Text("Selector : ");
        elementSelectorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        gridPane.add(elementSelectorLabel, 1, 5);

        Text elementXPathLabel = new Text("XPath : ");
        elementXPathLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        gridPane.add(elementXPathLabel, 1, 6);

        elementNameInput = new TextField();
        gridPane.add(elementNameInput, 2, 1);

        elementIdInput = new TextField();
        gridPane.add(elementIdInput, 2, 2);

        elementClassInput = new TextField();
        gridPane.add(elementClassInput, 2, 3);

        elementNameAttrInput = new TextField();
        gridPane.add(elementNameAttrInput, 2, 4);

        elementSelectorInput = new TextField();
        gridPane.add(elementSelectorInput, 2, 5);

        elementXPathInput = new TextField();
        gridPane.add(elementXPathInput, 2, 6);

        return gridPane;
    }

    public void receiveElementObject(ElementModel model) {
        this.elementModel = model;
        elementIdInput.setText(model.getId());
        elementClassInput.setText(model.getClassName());
        elementNameAttrInput.setText(model.getName());
        elementSelectorInput.setText(model.getSelector());
        elementXPathInput.setText(model.getXpath());
    }
}
