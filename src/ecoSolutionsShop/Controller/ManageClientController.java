package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Main;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.event.ActionEvent;
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

    // set the Check Order window
    public void goToCheckOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId4);
    }

    // set the Create New Order window
    public void goToCreateNewOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId2);
    }
}
