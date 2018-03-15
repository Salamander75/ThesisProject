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
        System.out.println("SUURUS: " + this.linkedHashMap.size());

        Set<String> keys = this.linkedHashMap.keySet();
        for (String key : keys) {
            ElementModel model = this.linkedHashMap.get(key);
            String elementName = model.getElementUniqueName();
            System.out.println("elementName: " + elementName);

            String tagName = model.getElementTagName();
            String tagType = model.getElementTagType();
            System.out.println("TAG NAME 7: " + tagName);
            System.out.println("TAG TYPE 7: " + tagType);
            String selectedAttributeValue = model.getSelectedLocatorValue().get(model.getSelectedLocatorTag());
            javaBuilder.addPublicMethod(elementName, tagName, tagType, selectedAttributeValue);
        }
        System.out.println(javaBuilder.buildJavaSourceCode());
    }
}
