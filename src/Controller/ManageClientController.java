package Controller;

import UI.UIControl.Controller;
import UI.UIControl.windows;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageClientController implements Initializable, windows {

    Controller myController;

    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
