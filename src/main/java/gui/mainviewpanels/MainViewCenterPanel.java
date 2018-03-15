package gui.mainviewpanels;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.SelectorType;
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
    private Button addNewElement;
    private Button saveElement;
    private Button removeElement;
    private Label errorMessage;
    private IGeneratorService iGeneratorService;
    private RadioButton idRadioButton;
    private RadioButton classNameRadioButton;
    private RadioButton nameRadioButton;
    private RadioButton selectorRadioButton;
    private RadioButton xpathRadioButton;
    private ElementModel elementModel;
    private boolean isEditMode = false;

    private final String elementNameNotDefined = "* Save failed. \n Element name must be defined";

    private final String elementSelectorNotSelected = "* Save failed. \n No selector chosen";

    private final String emptySelector = "* Save failed. \n Selector can't be empty";


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
        addNewElement = new Button("Add new");
        gridPane.add(saveElement, 1, 7);


        addNewElement.setOnAction(e -> {
            addNewElementItem();
        });

        saveElement.setOnAction(e -> {
      //      setUpNewElementModel();
            // TODO: 1) Element name must be defined, else throw error.DONE 2) If element with same name already exists, throw error
            // TODO: 3) If no element is selected, throw error 4) If no elementSelector is selected, throw error
            // TODO: 5) If empty selector is selected, throw error
            if (idRadioButton.isSelected()) {
                this.elementModel.getSelectedLocatorValue().put(SelectorType.ID, this.elementModel.getId());
                this.elementModel.setSelectedLocatorTag(SelectorType.ID);
            } else if (classNameRadioButton.isSelected()) {
                this.elementModel.getSelectedLocatorValue().put(SelectorType.CLASS_NAME, this.elementModel.getClassName());
                this.elementModel.setSelectedLocatorTag(SelectorType.CLASS_NAME);
            } else if (nameRadioButton.isSelected()) {
                this.elementModel.getSelectedLocatorValue().put(SelectorType.NAME, this.elementModel.getName());
                this.elementModel.setSelectedLocatorTag(SelectorType.NAME);
            } else if (selectorRadioButton.isSelected()) {
                this.elementModel.getSelectedLocatorValue().put(SelectorType.SELECTOR, this.elementModel.getSelector());
                this.elementModel.setSelectedLocatorTag(SelectorType.SELECTOR);
            } else if (xpathRadioButton.isSelected()) {
                this.elementModel.getSelectedLocatorValue().put(SelectorType.XPATH, this.elementModel.getXpath());
                this.elementModel.setSelectedLocatorTag(SelectorType.XPATH);
            }
            this.elementModel.setElementUniqueName(elementNameInput.getText());
            if (validateInput()) {
                DependencyClass.getMainViewLeftPanel().addNewElementItem(this.elementModel);
            }
        });

        removeElement = new Button("Remove");
        gridPane.add(removeElement, 2, 7);
        removeElement.setOnAction(e -> {
            DependencyClass.getMainViewLeftPanel().removeElement(getElementNameInput().getText());
            setElementNameInput(null);
            setElementIdInput(null);
            setElementClassInput(null);
            setElementnameAttrInput(null);
            setElementSelectorInput(null);
            setElementXPathInput(null);
            idRadioButton.setSelected(false);
            classNameRadioButton.setSelected(false);
            nameRadioButton.setSelected(false);
            selectorRadioButton.setSelected(false);
            xpathRadioButton.setSelected(false);
            errorMessage.setText("");
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

        errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: red;");
        gridPane.add(errorMessage, 1, 8);

        return gridPane;
    }

    @Override
    public void receiveElementObject(ElementModel model) {
        System.out.println("Rodriguez");
        this.elementModel = model;
        System.out.println("receiveElementObject tagType: " + this.elementModel.getElementTagType());
        setElementIdInput(iGeneratorService.removeDoubleQuotes(this.elementModel.getId()));
        setElementClassInput(iGeneratorService.removeDoubleQuotes(this.elementModel.getClassName()));
        setElementnameAttrInput(iGeneratorService.removeDoubleQuotes(this.elementModel.getName()));
        setElementSelectorInput(iGeneratorService.removeDoubleQuotes(this.elementModel.getSelector()));
        setElementXPathInput(iGeneratorService.removeDoubleQuotes(this.elementModel.getXpath()));
        System.out.println("receiveElementObject tagName: " + iGeneratorService.removeDoubleQuotes(this.elementModel.getElementTagName()));
    }

    public TextField getElementNameInput() {
        return elementNameInput;
    }

    public void setElementNameInput(String text) {
        elementNameInput.setText(text);
    }

    public TextField getElementIdInput() {
        return elementIdInput;
    }

    public void setElementIdInput(String text) {
        elementIdInput.setText(text);
    }

    public TextField getElementClassInput() {
        return elementClassInput;
    }

    public void setElementClassInput(String text) {
        elementClassInput.setText(text);
    }

    public TextField getElementnameAttrInput() {
        return elementNameAttrInput;
    }

    public void setElementnameAttrInput(String text) {
        elementNameAttrInput.setText(text);
    }

    public TextField getElementSelectorInput() {
        return elementSelectorInput;
    }

    public void setElementSelectorInput(String text) {
        elementSelectorInput.setText(text);
    }

    public TextField getElementXPathInput() {
        return elementXPathInput;
    }

    public void setElementXPathInput(String text) {
        elementXPathInput.setText(text);
    }

    public void setSelectedElementRadioButton(String keyword) {
        if (keyword.equals("id")) {
            idRadioButton.setSelected(true);
        }
        else if (keyword.equals("className")) {
            classNameRadioButton.setSelected(true);
        }
        else if (keyword.equals("name")) {
            nameRadioButton.setSelected(true);
        }
        else if (keyword.equals("selector")) {
            selectorRadioButton.setSelected(true);
        }
        else if (keyword.equals("xPath")) {
            xpathRadioButton.setSelected(true);
        }
    }

    private boolean validateInput() {
        System.out.println("tekst: " + getElementNameInput().getText());
        if (getElementNameInput().getText().length() == 0) {
            System.out.println("outpost 1");
            errorMessage.setText(elementNameNotDefined);
            return false;
        } else if (this.elementModel.getSelectedLocatorValue().size() == 0) {
            errorMessage.setText(elementSelectorNotSelected);
            return false;
        }
        System.out.println("key: " + this.elementModel.getSelectedLocatorValue().entrySet().iterator().next().getKey());
        System.out.println("outpost 2");
        return true;
    }

    private void addNewElementItem() {
        clearForm();
        this.elementModel = new ElementModel();
    }

    private void clearForm() {
        setElementNameInput(null);
        setElementIdInput(null);
        setElementClassInput(null);
        setElementnameAttrInput(null);
        setElementSelectorInput(null);
        setElementXPathInput(null);
        idRadioButton.setSelected(false);
        classNameRadioButton.setSelected(false);
        nameRadioButton.setSelected(false);
        selectorRadioButton.setSelected(false);
        xpathRadioButton.setSelected(false);
        errorMessage.setText("");
    }

}
