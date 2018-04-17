package gui.mainviewpanels;

import controller.SceneController;
import gui.MainViewScene;
import gui.SourceCodeGenerationScene;
import gui.common.CommonTopPanelComponents;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by karl on 23.11.2017.
 */
public class MainViewTopPanel implements IMainViewTopPanel{

    private String topPanelComponentCssClass = "top-panel-item";

    private HBox hBox;

    private CommonTopPanelComponents commonGuiComponents = new CommonTopPanelComponents();

    private Hyperlink mainViewLink = new Hyperlink("Main view");

    private Hyperlink sourceCodeGenerationLink = new Hyperlink("Generation");

    @Override
    public HBox populateTopPaneBorder(BorderPane border, boolean isSourceCodeGenerationScene) {
        hBox = commonGuiComponents.getCommonTopPanelGui(border);
        mainViewLink.setVisited(false);
        mainViewLink.getStyleClass().add(topPanelComponentCssClass);
        sourceCodeGenerationLink.setVisited(false);
        sourceCodeGenerationLink.getStyleClass().add(topPanelComponentCssClass);
        if (isSourceCodeGenerationScene) {
            mainViewLink.setOnAction(e -> SceneController.setCurrentScene(MainViewScene.getMainViewScene()));
            hBox.getChildren().add(mainViewLink);
        } else {
            sourceCodeGenerationLink.setOnAction(e -> {
                SceneController.setCurrentScene(SourceCodeGenerationScene.getSourceCodeGenerationScene()); });
            hBox.getChildren().add(sourceCodeGenerationLink);
        }
        return hBox;
    }
}
