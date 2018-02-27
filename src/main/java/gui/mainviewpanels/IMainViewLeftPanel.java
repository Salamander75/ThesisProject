package gui.mainviewpanels;

import javafx.scene.layout.VBox;
import model.ElementModel;

/**
 * Created by karl on 23.11.2017.
 */
public interface IMainViewLeftPanel {

    VBox addLeftPaneVBox();

    void addNewElementItem(ElementModel model);
}
