package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Main;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckOrderController implements Initializable, windows {

    Controller myController;
    private String itemID;
    private String description;
    private String clothingType;
    private String itemStatus;
    private String orderStatus;

    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // set the Manage Client window
    public void goToManageClient(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId3);
    }

    // set the Create the New Order window
    public void goToCreateNewOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId2);
    }

    public void displayItemDetails() {
    }

    public void displayLaundryItems() {
    }

    public void displayOrderStatus() {
    }

    public void go() {
    }
}
