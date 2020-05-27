package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Main;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class NewOrderController implements Initializable, windows {

    Controller myController;
    private String clientID;
    private String orderID;
    private String itemDescription;


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

    // set teh Check Order
    public void goToCheckOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId4);
    }

    public void displayClientName() {
    }

    public void displayLaundryItems() {
    }

    public void finishOrder() {
    }

    public void addItem() {
    }

    public void go() {
    }
}
