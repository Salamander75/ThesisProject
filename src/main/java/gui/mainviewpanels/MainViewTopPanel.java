package gui.mainviewpanels;

import controller.SceneController;
import gui.MainViewScene;
import gui.SourceCodeGenerationScene;
import gui.common.CommonTopPanelComponents;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import service.DependencyClass;
import service.GeneratorService;

/**
 * Created by karl on 23.11.2017.
 */
public class MainViewTopPanel implements IMainViewTopPanel{

    private HBox hBox;

    private CommonTopPanelComponents commonGuiComponents = new CommonTopPanelComponents();

    private Button mainViewButton = new Button("Main view");

    private Button sourceCodeGenerationButton = new Button("Generation");

    @Override
    public HBox populateTopPaneBorder(BorderPane border, boolean isSourceCodeGenerationScene) {
        hBox = commonGuiComponents.getCommonTopPanelGui(border);
        if (isSourceCodeGenerationScene) {
            mainViewButton.setOnAction(e -> SceneController.setCurrentScene(MainViewScene.getMainViewScene()));
            hBox.getChildren().add(mainViewButton);
        } else {
            sourceCodeGenerationButton.setOnAction(e -> {
                SceneController.setCurrentScene(SourceCodeGenerationScene.getSourceCodeGenerationScene());
                    GeneratorService generatorService = new GeneratorService();
            generatorService.
                    generateJavaPageObjectFile(DependencyClass.getMainViewLeftPanel()
                            .getElementsHashMap()); });
            hBox.getChildren().add(sourceCodeGenerationButton);
        }
        return hBox;
    }
}
