package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Account.ShopAccount;
import ecoSolutionsShop.Data.DBMethods;
import ecoSolutionsShop.Main;
import ecoSolutionsShop.Model.ShopID;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, windows {


    ShopAccount shopAccount = new ShopAccount();
    //DBMethods dbMethods = new DBMethods();
    Controller myController;

    @FXML
    TextField shopIDTextfield,passwordTextfield;
    @FXML
    Label errorLabel;

    private String shopID = "";
    private String password = "";



    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    // set the New Order window if login credentials are correct
    public void login(ActionEvent actionEvent) {
        //sending the entered data for other classes for processing
        shopID = shopIDTextfield.getText();
        shopAccount.setShopID(shopID);
        password = passwordTextfield.getText();
        shopAccount.setPassword(password);
        ShopID.getInstance().shopID = shopID;


        if (shopAccount.verifyCredentials()==true){
            myController.setWindow(Main.windowId2);
        }
        else{
            errorLabel.setText("Incorrect shop ID or Password!");
        }



    }
}
