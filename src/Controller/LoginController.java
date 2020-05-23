package Controller;

import UI.UIControl.Controller;
import UI.UIControl.windows;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, windows {


    Controller myController;



    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {

    }
}
