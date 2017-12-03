package service;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javafx.collections.ObservableMap;
import model.ElementModel;

import javax.lang.model.element.Modifier;
import java.util.Map;

/**
 * Created by Karl on 30.11.2017.
 */
public class FileWriter {

    TypeSpec pageObjectClassName;

    ObservableMap observableMap;


    public FileWriter(String className) {
        pageObjectClassName = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .build();
    }

    public void generatePageObjectFile(ObservableMap observableMap) {

        for (Object entry : observableMap.entrySet()) {

        }

        MethodSpec method = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC)
                .build();

    }
}
