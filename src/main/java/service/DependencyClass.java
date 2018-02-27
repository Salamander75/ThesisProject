package service;

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

    private static ElementModel selectedElement = new ElementModel();

    public static MainViewCenterPanel getCentralPanel() {
        return centralPanel;
    }

    public static MainViewLeftPanel getLeftPanel() {
        return leftPanel;
    }

    public static MainViewTopPanel getTopPanel() {
        return topPanel;
    }

    public static ElementModel getSelectedElement() {
        return selectedElement;
    }

    public static void setSelectedElement(ElementModel selectedElement) {
        DependencyClass.selectedElement = selectedElement;
    }
}
