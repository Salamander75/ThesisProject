package gui.mainviewpanels;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.ElementModel;
import service.DependencyClass;
import service.GeneratorService;
import service.IGeneratorService;

import java.util.LinkedHashMap;

/**
 * Created by karl on 23.11.2017.
 */
public class MainViewLeftPanel implements IMainViewLeftPanel{

    private VBox vbox = new VBox();
    private IGeneratorService iGeneratorService;

    public MainViewLeftPanel() {
        iGeneratorService = new GeneratorService();
    }

    @Override
    public VBox addLeftPaneVBox() {

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

        return vbox;
    }

    @Override
    public void addNewElementItem(ElementModel model) {
        vbox.getChildren().add(model.getHyperlink());
    }

    public void removeElement(ElementModel model) {
        vbox.getChildren().remove(model.getHyperlink());
    }
}
