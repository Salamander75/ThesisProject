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

    private LinkedHashMap<String, ElementModel> linkedHashMap = new LinkedHashMap<>();
    private ObservableMap<String, ElementModel> observableMap;
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

        observableMap = FXCollections.observableMap(linkedHashMap);
        observableMap.addListener(new MapChangeListener<String, ElementModel>() {
            @Override
            public void onChanged(Change<? extends String, ? extends ElementModel> change) {
                System.out.println("HashMap has changed");
            }
        });

        return vbox;
    }

    @Override
    public void addNewElementItem(ElementModel model) {

        if (!observableMap.containsKey(model.getElementUniqueName())) {
            System.out.println("ITEM ADDER: " + model.getXpath());
            observableMap.put(model.getElementUniqueName(), model);
            Hyperlink hyperlink = new Hyperlink(model.getElementUniqueName());
            model.setHyperlink(hyperlink);
           // links.add(hyperlink);
            VBox.setMargin(hyperlink, new Insets(0, 0, 0, 8));
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    hyperlink.setVisited(false);
                    System.out.println(hyperlink.getText());
                    ElementModel model = observableMap.get(hyperlink.getText());
                    DependencyClass.getMainViewCentralPanel().setElementNameInput(model.getElementUniqueName());
                    DependencyClass.getMainViewCentralPanel().setElementIdInput(iGeneratorService.removeDoubleQuotes(model.getId()));
                    DependencyClass.getMainViewCentralPanel().setElementClassInput(iGeneratorService.removeDoubleQuotes(model.getClassName()));
                    DependencyClass.getMainViewCentralPanel().setElementnameAttrInput(iGeneratorService.removeDoubleQuotes(model.getName()));
                    DependencyClass.getMainViewCentralPanel().setElementSelectorInput(iGeneratorService.removeDoubleQuotes(model.getSelector()));
                    DependencyClass.getMainViewCentralPanel().setElementXPathInput(iGeneratorService.removeDoubleQuotes(model.getXpath()));
                    DependencyClass.getMainViewCentralPanel().setSelectedElementRadioButton(model.getSelectedLocatorValue()
                            .entrySet().iterator().next().getKey());
                }
            });
            vbox.getChildren().add(hyperlink);
        }
        System.out.println(observableMap.size());
    }

    public void removeElement(String key) {
        vbox.getChildren().remove(observableMap.get(key).getHyperlink());
        System.out.println(key);
        observableMap.remove(key);

        System.out.println(observableMap.size());

    }

    public ObservableMap<String, ElementModel> getObservableList() {
        return observableMap;
    }

    public LinkedHashMap<String, ElementModel> getElementsHashMap() {
        System.out.println("LINKED HASHMAP GIVER 1: " + linkedHashMap.get("ad").getElementUniqueName());
        System.out.println("LINKED HASHMAP GIVER 2: " + linkedHashMap.get("ad").getElementTagName());
        System.out.println("LINKED HASHMAP GIVER 3: " + linkedHashMap.get("ad").getXpath());
        return linkedHashMap;
    }
}
