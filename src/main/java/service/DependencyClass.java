package service;

import gui.MainViewCenterPanel;
import gui.MainViewLeftPanel;
import gui.MainViewTopPanel;

/**
 * Created by Karl on 14.11.2017.
 */
public class DependencyClass {

    private static MainViewCenterPanel centralPanel = new MainViewCenterPanel();

    private static MainViewLeftPanel leftPanel = new MainViewLeftPanel();

    private static MainViewTopPanel topPanel = new MainViewTopPanel();



    public static MainViewCenterPanel getCentralPanel() {
        return centralPanel;
    }

    public static MainViewLeftPanel getLeftPanel() {
        return leftPanel;
    }

    public static MainViewTopPanel getTopPanel() {
        return topPanel;
    }
}
