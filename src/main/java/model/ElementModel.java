package model;

import javafx.scene.control.Hyperlink;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Karl on 1.11.2017.
 */
@Data
public class ElementModel {

    private String elementUniqueName;

    private String id;

    private String className;

    private String name;

    private String selector;

    private String xpath;

    private String elementTagName;

    private Map<String, String> selectedLocatorValue;

    private Hyperlink hyperlink;

    private String selectedLocatorTag;

    private String elementTagType;

    private boolean elementExistsInElementList;

    public ElementModel() {
        this.selectedLocatorValue = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getElementTagName() {
        return elementTagName;
    }

    public void setElementTagName(String elementTagName) {
        this.elementTagName = elementTagName;
    }

    public Map<String, String> getSelectedLocatorValue() {
        return selectedLocatorValue;
    }

    public String getElementUniqueName() {
        return elementUniqueName;
    }

    public void setElementUniqueName(String elementUniqueName) {
        this.elementUniqueName = elementUniqueName;
    }

    public Hyperlink getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(Hyperlink hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getSelectedLocatorTag() {
        return selectedLocatorTag;
    }

    public void setSelectedLocatorTag(String selectedLocatorTag) {
        this.selectedLocatorTag = selectedLocatorTag;
    }

    public String getElementTagType() {
        return elementTagType;
    }

    public void setElementTagType(String elementTagType) {
        this.elementTagType = elementTagType;
    }

    public boolean isElementExistsInElementList() {
        return elementExistsInElementList;
    }

    public void setElementExistsInElementList(boolean elementExistsInElementList) {
        this.elementExistsInElementList = elementExistsInElementList;
    }
}
