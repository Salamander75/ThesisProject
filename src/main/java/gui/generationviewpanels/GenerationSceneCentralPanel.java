package gui.generationviewpanels;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GenerationSceneCentralPanel implements IGenerationSceneCentralPanel {

    private String pageObjectSourceCode;

    private Text textAreaLabel;

    private TextArea sourceCodeTextArea;

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
        textAreaLabel = new Text("Source code");
        gridPane.add(textAreaLabel,1,1);
        sourceCodeTextArea = new TextArea();
        sourceCodeTextArea.setPrefHeight(350);
        gridPane.add(sourceCodeTextArea, 1, 2);
        return gridPane;
    }

    public void setPageObjectSourceCode(String pageObjectSourceCode) {
        this.pageObjectSourceCode = pageObjectSourceCode;
        sourceCodeTextArea.setText(pageObjectSourceCode);
    }
}
