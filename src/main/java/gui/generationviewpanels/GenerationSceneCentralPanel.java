package gui.generationviewpanels;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GenerationSceneCentralPanel implements IGenerationSceneCentralPanel {

    @Override
    public GridPane addCenterPanel() {
        GridPane gridPane = new GridPane();
        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 15;\n" +
                "-fx-border-width: 0;\n" +
                "-fx-border-style: solid;\n" +
                "-fx-background-color: #FFFFFF;\n";
        gridPane.setStyle(cssLayout);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(false);
        Text textAreaLabel = new Text("Source code");
        gridPane.add(textAreaLabel,1,1);
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(350);
        gridPane.add(textArea, 1, 2);
        return gridPane;
    }
}
