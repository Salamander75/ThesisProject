package gui.mainviewpanels;

import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.SelectorType;
import model.ElementModel;
import service.DependencyClass;
import service.GeneratorService;
import service.IGeneratorService;

import java.util.LinkedHashMap;

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
    private LinkedHashMap<String, ElementModel> linkedElementsHashMap = new LinkedHashMap<>();

    private final String elementNameNotDefined = "* Save failed. \n Element name must be defined";

    private final String elementSelectorNotSelected = "* Save failed. \n No selector chosen";

    private final String emptySelector = "* Save failed. \n Selector value can't be empty";

    private final String duplicateElement = "* Save failed. \n Element with same already exists";


    public MainViewCenterPanel () {
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
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(0);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(45);
        gridPane.getColumnConstraints().addAll(col1,col2,col3,col4);
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

        addNewElement = new Button("Add new");
        gridPane.add(addNewElement, 1, 7);

        saveElement = new Button("Save");
        saveElement.setVisible(false); // Invisible at the beginning
        gridPane.add(saveElement, 2, 7);


        addNewElement.setOnAction(e -> {
       //     addNewElementItem();
            clearForm();
            removeElement.setVisible(false);
            elementModel = new ElementModel();
            System.out.println(elementModel.hashCode());
            saveElement.setVisible(true);
            saveElement.setDisable(false);
        });

        saveElement.setOnAction(e -> {
            if (validateInput()) {
                if (!linkedElementsHashMap.containsKey(elementNameInput.getText()) &&
                        !elementModel.isElementExistsInElementList()) { // We are adding a new element
                    elementModel.setElementUniqueName(elementNameInput.getText());
                    elementModel.setElementExistsInElementList(true);
                    setElementSelectorForm();
                    addHyperlinkToElementModel();
                    linkedElementsHashMap.put(elementModel.getElementUniqueName(), elementModel);
                    saveElement.setDisable(true);
                    DependencyClass.getMainViewLeftPanel().addNewElementItem(elementModel);
                } else if (!linkedElementsHashMap.containsKey(elementNameInput.getText()) &&
                        elementModel.isElementExistsInElementList()) { // Element exists in list but name has been changed
                    linkedElementsHashMap.remove(elementModel.getElementUniqueName());
                    elementModel.setElementUniqueName(elementNameInput.getText());
                    elementModel.getHyperlink().setText(elementNameInput.getText());
                    setElementSelectorForm();
                    linkedElementsHashMap.put(elementModel.getElementUniqueName(), elementModel);
                    saveElement.setDisable(true);
                } else { // Element exists and no change in name
                    setElementSelectorForm();
                    saveElement.setDisable(true);
                }


                // TODO: 1) Element name must be defined, else throw error.DONE
                // TODO: 3) If no element is selected, throw error 4) If no elementSelector is selected, throw error
                // TODO: 5) If empty selector is selected, throw error

                clearForm();
            }
        });

        removeElement = new Button("Remove");
        removeElement.setVisible(false);
        gridPane.add(removeElement, 3, 7);
        removeElement.setOnAction(e -> {
            linkedElementsHashMap.remove(getElementNameInput().getText());
            DependencyClass.getMainViewLeftPanel().removeElement(elementModel);
            clearForm();
            removeElement.setVisible(false);
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
    ///    elementModel = model;
        setElementIdInput(iGeneratorService.removeDoubleQuotes(model.getId()));
        setElementClassInput(iGeneratorService.removeDoubleQuotes(model.getClassName()));
        setElementnameAttrInput(iGeneratorService.removeDoubleQuotes(model.getName()));
        setElementSelectorInput(iGeneratorService.removeDoubleQuotes(model.getSelector()));
        setElementXPathInput(iGeneratorService.removeDoubleQuotes(model.getXpath()));

    }

    public TextField getElementNameInput() {
        return elementNameInput;
    }

    public void setElementNameInput(String text) {
        elementNameInput.setText(text);
    }

    public void setElementIdInput(String text) {
        elementIdInput.setText(text);
    }

    public void setElementClassInput(String text) {
        elementClassInput.setText(text);
    }

    public void setElementnameAttrInput(String text) {
        elementNameAttrInput.setText(text);
    }

    public void setElementSelectorInput(String text) {
        elementSelectorInput.setText(text);
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
        if (getElementNameInput().getText().length() == 0) {
            errorMessage.setText(elementNameNotDefined);
            return false;
        } else if (linkedElementsHashMap.containsKey(elementNameInput.getText()) &&
                !elementModel.isElementExistsInElementList()) {
            errorMessage.setText(duplicateElement);
            return false;
        } else if (!idRadioButton.isSelected() && !classNameRadioButton.isSelected() && !nameRadioButton.isSelected()
                && !selectorRadioButton.isSelected() && !xpathRadioButton.isSelected()) {
            errorMessage.setText(elementSelectorNotSelected);
            return false;
        } else if (elementIdInput.getText().length() == 0 && idRadioButton.isSelected()) {
            errorMessage.setText(emptySelector);
            return false;
        } else if (elementClassInput.getText().length() == 0 && classNameRadioButton.isSelected()) {
            errorMessage.setText(emptySelector);
            return false;
        } else if (elementNameAttrInput.getText().length() == 0 && nameRadioButton.isSelected()) {
            errorMessage.setText(emptySelector);
            return false;
        } else if (elementSelectorInput.getText().length() == 0 && selectorRadioButton.isSelected()) {
            errorMessage.setText(emptySelector);
            return false;
        } else if (elementXPathInput.getText().length() == 0 && xpathRadioButton.isSelected()) {
            errorMessage.setText(emptySelector);
            return false;
        }
        return true;
    }

    private void clearForm() {
        setElementNameInput("");
        setElementIdInput("");
        setElementClassInput("");
        setElementnameAttrInput("");
        setElementSelectorInput("");
        setElementXPathInput("");
        idRadioButton.setSelected(false);
        classNameRadioButton.setSelected(false);
        nameRadioButton.setSelected(false);
        selectorRadioButton.setSelected(false);
        xpathRadioButton.setSelected(false);
        errorMessage.setText("");
    }

    private void addHyperlinkToElementModel() {
        Hyperlink hyperlink = new Hyperlink(elementModel.getElementUniqueName());
        elementModel.setHyperlink(hyperlink);
        hyperlink.setOnMouseClicked( event -> {
            saveElement.setDisable(false);
            removeElement.setVisible(true);
            elementModel = linkedElementsHashMap.get(hyperlink.getText());
            setElementNameInput(elementModel.getElementUniqueName());
            setElementIdInput(iGeneratorService.removeDoubleQuotes(elementModel.getId()));
            setElementClassInput(iGeneratorService.removeDoubleQuotes(elementModel.getClassName()));
            setElementnameAttrInput(iGeneratorService.removeDoubleQuotes(elementModel.getName()));
            setElementSelectorInput(iGeneratorService.removeDoubleQuotes(elementModel.getSelector()));
            setElementXPathInput(iGeneratorService.removeDoubleQuotes(elementModel.getXpath()));
            setSelectedElementRadioButton(elementModel.getSelectedLocatorValue()
                    .entrySet().iterator().next().getKey());
        });
    }

    private void setElementSelectorForm() {
        if (idRadioButton.isSelected()) {
            elementModel.getSelectedLocatorValue().clear();
            elementModel.getSelectedLocatorValue().put(SelectorType.ID, elementIdInput.getText());
            elementModel.setSelectedLocatorTag(SelectorType.ID);
        } else if (classNameRadioButton.isSelected()) {
            elementModel.getSelectedLocatorValue().clear();
            elementModel.getSelectedLocatorValue().put(SelectorType.CLASS_NAME, elementClassInput.getText());
            elementModel.setSelectedLocatorTag(SelectorType.CLASS_NAME);
        } else if (nameRadioButton.isSelected()) {
            elementModel.getSelectedLocatorValue().clear();
            elementModel.getSelectedLocatorValue().put(SelectorType.NAME, elementNameAttrInput.getText());
            elementModel.setSelectedLocatorTag(SelectorType.NAME);
        } else if (selectorRadioButton.isSelected()) {
            elementModel.getSelectedLocatorValue().clear();
            elementModel.getSelectedLocatorValue().put(SelectorType.SELECTOR, elementSelectorInput.getText());
            elementModel.setSelectedLocatorTag(SelectorType.SELECTOR);
        } else if (xpathRadioButton.isSelected()) {
            elementModel.getSelectedLocatorValue().clear();
            elementModel.getSelectedLocatorValue().put(SelectorType.XPATH, elementXPathInput.getText());
            elementModel.setSelectedLocatorTag(SelectorType.XPATH);
        }
        updateElementAllSelectors();
    }

    private void updateElementAllSelectors() {
        elementModel.setId(elementIdInput.getText());
        elementModel.setClassName(elementClassInput.getText());
        elementModel.setName(elementNameAttrInput.getText());
        elementModel.setSelector(elementSelectorInput.getText());
        elementModel.setXpath(elementXPathInput.getText());
    }

    public ElementModel getCurrentElementModel() {
        return elementModel;
    }

    public LinkedHashMap<String, ElementModel> getPageObjectsList() {
        return linkedElementsHashMap;
    }

}
