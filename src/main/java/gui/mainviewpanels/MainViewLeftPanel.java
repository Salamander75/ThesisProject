package gui.mainviewpanels;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.ElementModel;
import service.GeneratorService;
import service.IGeneratorService;

/**
 * Created by karl on 23.11.2017.
 */
public class MainViewLeftPanel implements IMainViewLeftPanel{

    private VBox vbox = new VBox();
    private IGeneratorService iGeneratorService;
    private ScrollPane scrollPane;
    private TextField pageObjectClassNameField;

    public MainViewLeftPanel() {
        iGeneratorService = new GeneratorService();
    }

    @Override
    public ScrollPane addLeftPaneVBox() {

        pageObjectClassNameField = new TextField();
        pageObjectClassNameField.setPromptText("Page Object name here...");


        scrollPane = new ScrollPane();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n";
        scrollPane.setStyle(cssLayout);

        vbox.getChildren().addAll(pageObjectClassNameField,scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setVmax(375);
        scrollPane.setPrefSize(200, 150);
        scrollPane.setContent(vbox);
        scrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
              //  fileName.setText(imageNames[(new_val.intValue() - 1)/100]);
            }
        });

        return scrollPane;
    }

    @Override
    public void addNewElementItem(ElementModel model) {
        vbox.getChildren().add(model.getHyperlink());
    }

    public void removeElement(ElementModel model) {
        vbox.getChildren().remove(model.getHyperlink());
    }

    public String getPageObjectName() {
        return pageObjectClassNameField.getText();
    }
}
