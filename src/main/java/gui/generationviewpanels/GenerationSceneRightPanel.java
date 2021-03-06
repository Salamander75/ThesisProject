package gui.generationviewpanels;

import controller.SceneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import service.DependencyClass;
import service.GeneratorService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerationSceneRightPanel implements IGenerationSceneRightPanel {

    private VBox vbox = new VBox();

    private String pageObjectSourceCode;

    private String pageObjectName;

    @Override
    public VBox addRightPanel() {
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 15;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n";
        vbox.setStyle(cssLayout);

        Button generateButton = new Button("Generate");
        generateButton.setOnAction( event -> {
            pageObjectName = DependencyClass.getMainViewLeftPanel().getPageObjectName();
            GeneratorService generatorService = new GeneratorService();
            pageObjectSourceCode = generatorService.
                    generateJavaPageObjectFile(pageObjectName, DependencyClass.getMainViewCentralPanel().getPageObjectsList());
            DependencyClass.getGenerationSceneCentralPanel().setPageObjectSourceCode(pageObjectSourceCode);
        });

        Button saveFileButton = new Button("    Save   ");
        saveFileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName(pageObjectName);
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Java files (*.java)", "*.java");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showSaveDialog(SceneController.getCurrentStage());
                if(file != null){
                    SaveFile(pageObjectSourceCode, file);
                }

        }});

        vbox.getChildren().addAll(generateButton, saveFileButton);
        return vbox;
    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }

    }
}
