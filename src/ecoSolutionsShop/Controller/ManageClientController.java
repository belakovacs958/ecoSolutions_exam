package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Main;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class ManageClientController implements Initializable, windows {


    private String clientID;
    private String clientName;
    private String clientEmail;
    private String clientPhone;

    Controller myController;

    @FXML
    private TextField clientID_textfield_mw, clientName_textfield, clientEmail_textfield, clientPhone_textfield;

    @FXML
    private Label client_name, client_email, client_phone, client_ID;

    @FXML
    private Button registerClient_btn;

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

        client_name.setText(clientName);
        client_email.setText(clientEmail);
        client_phone.setText(clientPhone);
        client_ID.setText(clientID);

    }

    public void registerClient() {


    }

    public void go() {

        clientID = clientID_textfield_mw.getText();

        displayClientDetails();
    }
}
