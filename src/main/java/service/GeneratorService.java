package service;

import javafx.collections.ObservableMap;
import model.ElementModel;
import service.javabuilder.JavaBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by Karl on 15.11.2017.
 */
public class GeneratorService implements IGeneratorService{

    private LinkedHashMap<String, ElementModel> linkedHashMap;

    public GeneratorService () {
    }

    public String removeDoubleQuotes(String s) {
        return s.replaceAll("\"", "");
    }

    public void generateJavaPageObjectFile(LinkedHashMap linkedHashMap) {

        JavaBuilder javaBuilder = new JavaBuilder("Test");
        this.linkedHashMap = linkedHashMap;

        Set<String> keys = this.linkedHashMap.keySet();
        for (String key : keys) {
            ElementModel model = this.linkedHashMap.get(key);
            String elementName = model.getElementUniqueName();

            String tagName = removeDoubleQuotes(model.getElementTagName());
            String tagType = removeDoubleQuotes(model.getElementTagType());
            String selectedAttributeValue = model.getSelectedLocatorValue().get(model.getSelectedLocatorTag());
            String elementLocatorTag = model.getSelectedLocatorTag();
            javaBuilder.addPublicMethod(elementName, tagName, tagType, selectedAttributeValue, elementLocatorTag);
        }
        System.out.println(javaBuilder.buildJavaSourceCode());
    }
}
