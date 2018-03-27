package gui.mainviewpanels;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.ElementModel;

/**
 * Created by karl on 23.11.2017.
 */
public interface IMainViewLeftPanel {

    ScrollPane addLeftPaneVBox();

    void addNewElementItem(ElementModel model);
}
