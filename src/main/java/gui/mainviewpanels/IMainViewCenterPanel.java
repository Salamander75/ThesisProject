package gui.mainviewpanels;

import javafx.scene.layout.GridPane;
import model.ElementModel;

/**
 * Created by karl on 23.11.2017.
 */
public interface IMainViewCenterPanel {

    GridPane addCenterPanel();

    void receiveElementObject(ElementModel model);
}
