package gui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karl on 23.11.2017.
 */
public class MainViewLeftPanel implements IMainViewLeftPanel{

    private List<Hyperlink> leftPaneElements = new ArrayList<>();
    private ObservableList<Hyperlink> observableList;
 //   private HashMap<String, >


    @Override
    public VBox addLeftPaneVBox() {

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

        observableList = FXCollections.observableList(leftPaneElements);
        observableList.addListener(new ListChangeListener() {

            @Override
            public void onChanged(Change change) {
                System.out.println("Detected a change! ");
            }
        });

        List<Hyperlink> links = new ArrayList<>();
        links.add(new Hyperlink("Sales"));
        links.add(new Hyperlink("Marketing"));
        links.add(new Hyperlink("Distribution"));
        links.add(new Hyperlink("Costs"));

        for(Hyperlink hyperlink : links) {
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //     getHostServices().showDocument(hyperlink.getText());
                    hyperlink.setVisited(false);
                    System.out.println(hyperlink.getText());
                }
            });
        }
        for (int i=0; i<links.size(); i++) {
            VBox.setMargin(links.get(i), new Insets(0, 0, 0, 8));
            vbox.getChildren().add(links.get(i));
        }

        return vbox;
    }

    @Override
    public void addNewElementItem(Hyperlink hyperlink) {
        observableList.add(hyperlink);
    }
}
