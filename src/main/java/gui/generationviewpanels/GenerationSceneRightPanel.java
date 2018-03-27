package gui.generationviewpanels;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import service.DependencyClass;
import service.GeneratorService;

public class GenerationSceneRightPanel implements IGenerationSceneRightPanel {

    private VBox vbox = new VBox();

    private String[] items = {"Java"};

    @Override
    public VBox addRightPanel() {
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 15;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n";
        vbox.setStyle(cssLayout);
        Text title = new Text("Select language");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                items)
        );

        Button generateButton = new Button("Generate");
        generateButton.setOnAction( event -> {
            GeneratorService generatorService = new GeneratorService();
            String pageObjectSourceCode = generatorService.
                    generateJavaPageObjectFile(DependencyClass.getMainViewCentralPanel().getPageObjectsList());
            DependencyClass.getGenerationSceneCentralPanel().setPageObjectSourceCode(pageObjectSourceCode);
        });
        vbox.getChildren().addAll(title, cb, generateButton);
        return vbox;
    }
}
