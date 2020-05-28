package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Data.DBMethods;
import ecoSolutionsShop.Main;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class ManageClientController implements Initializable, windows {

    Controller myController;
    DBMethods dbMethods = new DBMethods();


    private String clientEmail;


    @FXML
    private TextField clientID_textfield;

    @FXML
    private Label clientName_label, clientEmail_label, clientPhone_label, client_ID;

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

    public void displayClientDetails() {

        clientName_label.setText(dbMethods.getName());
        clientEmail_label.setText(clientEmail);
        clientPhone_label.setText(dbMethods.getPhoneNo());


    }

    public void registerClient() {
    }

    public void go() {

        clientEmail = clientID_textfield.getText();
        dbMethods.setEmail(clientEmail);
        dbMethods.selectClient();


        displayClientDetails();
    }
}
