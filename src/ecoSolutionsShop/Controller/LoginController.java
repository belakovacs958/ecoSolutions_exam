package ecoSolutionsShop.Controller;

import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import ecoSolutionsShop.Main;


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

    // set the New Order window
    public void login(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId2);
    }
}
