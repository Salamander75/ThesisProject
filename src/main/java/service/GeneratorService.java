package service;

import gui.Main;
import javafx.collections.ObservableMap;

/**
 * Created by Karl on 15.11.2017.
 */
public class GeneratorService implements IGeneratorService{

    public GeneratorService () {
    }

    public String removeDoubleQuotes(String s) {
        return s.replaceAll("\"", "");
    }

    public void generateJavaPageObjectFile(ObservableMap observableMap) {

    }
}
