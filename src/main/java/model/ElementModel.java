package model;

import javafx.scene.control.Hyperlink;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Karl on 1.11.2017.
 */
public class ElementModel {

    private String elementUniqueName;

    private String id;

    private String className;

    private String name;

    private String selector;

    private String xpath;

    private String elementTagName;

    private Map<String, String> selectedAttribute;

    private Hyperlink hyperlink;

    public ElementModel() {
        this.selectedAttribute = new HashMap<>();
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

    public Map<String, String> getSelectedAttribute() {
        return selectedAttribute;
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
}
