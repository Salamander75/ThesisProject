package service;

import gui.Main;

/**
 * Created by Karl on 15.11.2017.
 */
public class GeneratorService implements IGeneratorService{

    private Main main;

    public GeneratorService () {
    }

    public String removeDoubleQuotes(String s) {
        return s.replaceAll("\"", "");
    }

    public void validateElementSave() {

    }
}
