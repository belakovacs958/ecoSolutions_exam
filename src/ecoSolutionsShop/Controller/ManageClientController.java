package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Data.DBMethods;
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


    ////////////////////////////////////////objects///////////////////////////////////
    Controller myController;
    DBMethods dbMethods = new DBMethods();

    ///////////////////////////////////////FXML///////////////////////////////////////

    @FXML
    private TextField clientID_textfield, clientName_textfield, clientEmail_textfield, clientPhone_textfield;

    @FXML
    private Label clientName_label, clientEmail_label, clientPhone_label, successMessage_label, errorMessage_label;

    //////////////////////////////////////////override///////////////////////////////////////////

    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    ///////////////////////////////////////methods////////////////////////////////////


    // set the Check Order window
    public void goToCheckOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId4);
    }

    // set the Create New Order window
    public void goToCreateNewOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId2);
    }

    //displays information about the client with the given email address
    public void displayClientDetails() {
        System.out.println("displayClientDetails() is called");
        clientName_label.setText("Client name: " + dbMethods.getName());
        clientEmail_label.setText("Client email: " + clientID_textfield.getText());
        clientPhone_label.setText("Client phone number: " + dbMethods.getPhoneNo());

    }
    //registers a client if it is not already registered
    public void registerClient() {
        System.out.println("registerClient() is called");
        //checks if the given email is already registered or not , if yes it displays an error message
        if(dbMethods.isEmailRegistered(clientEmail_textfield.getText())==true){
            displayErrorMessage();
        }
        //if the given email is not already registered then it inserts the client info into the database and displays a success message
        else {
           displaySuccessMessage();
        }
    }

    public void go() {
        System.out.println("go() is called");
        dbMethods.selectClient(clientID_textfield.getText());
        displayClientDetails();
    }
    public void displaySuccessMessage(){
        System.out.println("displaySuccessMessage() is called");
        errorMessage_label.setText("");
        dbMethods.insertClient(clientName_textfield.getText(), clientEmail_textfield.getText(), clientPhone_textfield.getText());
        successMessage_label.setText(clientName_textfield.getText() + " is registered successfully!");
        clientName_textfield.setText("");
        clientEmail_textfield.setText("");
        clientPhone_textfield.setText("");
    }
    public void displayErrorMessage(){
        System.out.println("displayErrorMessage() is called");
        successMessage_label.setText("");
        errorMessage_label.setText("Client registration failed! A client with: "+clientEmail_textfield.getText()+" email already exists");
        clientName_textfield.setText("");
        clientEmail_textfield.setText("");
        clientPhone_textfield.setText("");
    }
}
