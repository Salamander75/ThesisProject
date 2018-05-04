package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Karl on 3.11.2017.
 */
public class HelpModal {

    private static final String title = "ThesisProject ProtoType";

    public static void display () {
        Stage window = new Stage();
        window.initModality(Modality.NONE);
        window.setTitle(title);
        window.setMinWidth(250);
        Text text1 = new Text("Instructions to use the tool: ");
        Text text2 = new Text("1) Press 'Add new' button: ");
        Text text3 = new Text("2) Open the Web View");
        Text text4 = new Text("3) Hover over the element, you wish to select (selected element is yellow)");
        Text text5 = new Text("4) Modal opens about element selectors. Press 'Select'");
        Text text6 = new Text("5) Main view form is filled with data. Here you can modify them \n and add element name. Also choose one selector type and press 'Save'");
        Text text7 = new Text("6) Repeat steps 1 - 5 until you no longer need any more elements");
        Text text8 = new Text("7) Enter Page Object name (at top of created web elements)");
        Text text9 = new Text("8) Go to generation view and press Generate. Press 'Save' to save your file to local disk");
        VBox layout = new VBox(10);
        layout.getChildren().addAll(text1, text2, text3, text4, text5, text6, text7, text8, text9);
        layout.setAlignment(Pos.TOP_LEFT);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }


}
