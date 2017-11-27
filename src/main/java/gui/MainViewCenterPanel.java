package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.ElementModel;
import service.DependencyClass;
import service.GeneratorService;
import service.IGeneratorService;

/**
 * Created by karl on 23.11.2017.
 */
public class MainViewCenterPanel implements IMainViewCenterPanel {

    private TextField elementNameInput;
    private TextField elementIdInput;
    private TextField elementClassInput;
    private TextField elementNameAttrInput;
    private TextField elementSelectorInput;
    private TextField elementXPathInput;
    private Button saveElement;
    private IMainViewLeftPanel iMainViewLeftPanel;
    private IGeneratorService iGeneratorService;
    private RadioButton idRadioButton;
    private RadioButton classNameRadioButton;
    private RadioButton nameRadioButton;
    private RadioButton selectorRadioButton;
    private RadioButton xpathRadioButton;
    private ElementModel elementModel;

    public MainViewCenterPanel () {
        System.out.println("Kood: " + this.hashCode());
        this.iGeneratorService = new GeneratorService();
    }

    @Override
    public GridPane addCenterPanel() {
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

        saveElement = new Button("Save");
        gridPane.add(saveElement, 1, 7);

        saveElement.setOnAction(e -> {
            Hyperlink hyperlink = new Hyperlink("as");
            DependencyClass.getLeftPanel().addNewElementItem(hyperlink);
            // TODO: 1) Element name must be defined, else throw error 2) If element with same name already exists, throw error
            // TODO: 3) If no element is selected, throw error 4) If no elementSelector is selected, throw error
            // TODO: 5) If empty selector is selected, throw error
            if (idRadioButton.isSelected()) {
                this.elementModel.getSelectedAttribute().put("id", this.elementModel.getId());
            } else if (classNameRadioButton.isSelected()) {
                this.elementModel.getSelectedAttribute().put("className", this.elementModel.getClassName());
            } else if (nameRadioButton.isSelected()) {
                this.elementModel.getSelectedAttribute().put("name", this.elementModel.getName());
            } else if (selectorRadioButton.isSelected()) {
                this.elementModel.getSelectedAttribute().put("selector", this.elementModel.getSelector());
            } else {
                this.elementModel.getSelectedAttribute().put("xPath", this.elementModel.getXpath());
            }
            this.elementModel.setElementUniqueName(elementNameInput.getText());
            //       leftPaneContent.getChildren().addAll(new );
        });

        final ToggleGroup group = new ToggleGroup();

        idRadioButton = new RadioButton();
        idRadioButton.setToggleGroup(group);
        gridPane.add(idRadioButton, 3, 2);

        classNameRadioButton = new RadioButton();
        gridPane.add(classNameRadioButton, 3, 3);
        classNameRadioButton.setToggleGroup(group);

        nameRadioButton = new RadioButton();
        gridPane.add(nameRadioButton, 3, 4);
        nameRadioButton.setToggleGroup(group);

        selectorRadioButton = new RadioButton();
        gridPane.add(selectorRadioButton, 3, 5);
        selectorRadioButton.setToggleGroup(group);

        xpathRadioButton = new RadioButton();
        gridPane.add(xpathRadioButton, 3, 6);
        xpathRadioButton.setToggleGroup(group);

        return gridPane;
    }

    @Override
    public void receiveElementObject(ElementModel model) {
        System.out.println("Kood: " + this.hashCode());
        System.out.println("H>HA>HH>H>H>AA: " + model.getSelector());
        this.elementModel = model;
        elementIdInput.setText(iGeneratorService.removeDoubleQuotes(model.getId()));
        elementClassInput.setText(iGeneratorService.removeDoubleQuotes(model.getClassName()));
        elementNameAttrInput.setText(iGeneratorService.removeDoubleQuotes(model.getName()));
        elementSelectorInput.setText(iGeneratorService.removeDoubleQuotes(model.getSelector()));
        elementXPathInput.setText(iGeneratorService.removeDoubleQuotes(model.getXpath()));
        System.out.println(iGeneratorService.removeDoubleQuotes(model.getElementTagName()));
    }
}
