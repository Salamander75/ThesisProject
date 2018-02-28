package service;

import gui.generationviewpanels.GenerationSceneCentralPanel;
import gui.generationviewpanels.GenerationSceneRightPanel;
import gui.mainviewpanels.MainViewCenterPanel;
import gui.mainviewpanels.MainViewLeftPanel;
import gui.mainviewpanels.MainViewTopPanel;
import model.ElementModel;

/**
 * Created by Karl on 14.11.2017.
 */
public class DependencyClass {

    private static MainViewCenterPanel centralPanel = new MainViewCenterPanel();

    private static MainViewLeftPanel leftPanel = new MainViewLeftPanel();

    private static MainViewTopPanel topPanel = new MainViewTopPanel();

    private static GenerationSceneCentralPanel generationSceneCentralPanel = new GenerationSceneCentralPanel();

    private static GenerationSceneRightPanel generationSceneRightPanel = new GenerationSceneRightPanel();

    private static ElementModel selectedElement = new ElementModel();

    public static MainViewCenterPanel getMainViewCentralPanel() {
        return centralPanel;
    }

    public static MainViewLeftPanel getMainViewLeftPanel() {
        return leftPanel;
    }

    public static MainViewTopPanel getMainViewTopPanel() {
        return topPanel;
    }

    public static GenerationSceneCentralPanel getGenerationSceneCentralPanel() {
        return generationSceneCentralPanel;
    }

    public static GenerationSceneRightPanel getGenerationSceneRightPanel() {
        return generationSceneRightPanel;
    }

    public static ElementModel getSelectedElement() {
        return selectedElement;
    }

    public static void setSelectedElement(ElementModel selectedElement) {
        DependencyClass.selectedElement = selectedElement;
    }
}
