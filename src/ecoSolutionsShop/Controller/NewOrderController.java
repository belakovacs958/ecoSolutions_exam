package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Account.ClientAccount;
import ecoSolutionsShop.Account.ShopAccount;
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

import static java.lang.Integer.parseInt;

public class NewOrderController implements Initializable, windows {

    Controller myController;
    ClientAccount clientAccount = new ClientAccount();
    DBMethods dbMethods = new DBMethods();
    ShopAccount shopAccount = new ShopAccount();


    @FXML
    private TextField clientEmail_textfield, itemDescription_textfield;

    @FXML
    private Label clientName_label;


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

    // set the Check Order
    public void goToCheckOrder(ActionEvent actionEvent) {

        myController.setWindow(Main.windowId4);
    }
//This method displays the client's name with the given email , if an account exists for that email.
// Sets email variable for clientAccount object for the given value
    public void createOrder() {

        dbMethods.selectClient(clientEmail_textfield.getText());

        if (dbMethods.isEmailRegistered(clientEmail_textfield.getText())==true){
            clientName_label.setText("The order is created to " + dbMethods.getName()+ "'s account! Put in laundry items");
            dbMethods.insertOrder(clientEmail_textfield.getText(),shopAccount.getShopID());
            clientAccount.setEmail(clientEmail_textfield.getText());
            clientEmail_textfield.setText("");

        }
        else{
            clientName_label.setText("There is no account registered for the email: " + clientEmail_textfield.getText());
            clientEmail_textfield.setText("");

        }
    }

    public void displayLaundryItems() {
    }

    public void finishOrder() {
    }

    public void addItem() {
        int recentOrderID = dbMethods.selectMostRecentOrderIDForGivenEmail(clientAccount.getEmail());


        dbMethods.insertLaundryItem(itemDescription_textfield.getText(),recentOrderID,"defaultClothingID");


    }

    public void go() {
        createOrder();



    }
}
