package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Model.ShopAccount;
import ecoSolutionsShop.Data.DBMethods;
import ecoSolutionsShop.Main;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, windows {

    ////////////////////////////////////////fields//////////////////////////////////

    private String shopID = "";
    private String password = "";

    ////////////////////////////////////////objects///////////////////////////////////

    ShopAccount shopAccount = new ShopAccount();
    DBMethods dbMethods = new DBMethods();
    Controller myController;

    ///////////////////////////////////////FXML///////////////////////////////////////

    @FXML
    TextField shopIDTextfield,passwordTextfield;
    @FXML
    Label errorLabel;


    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    ///////////////////////////////////////methods////////////////////////////////////

    // set the New Order window if login credentials are correct
    public void login() {
        System.out.println("public void login() is called");
        //sending the entered data for other classes for processing
        shopID = shopIDTextfield.getText();
        password = passwordTextfield.getText();
        shopAccount.setShopID(shopID);

        if (dbMethods.selectCredentials(shopID,password)==true){
            myController.setWindow(Main.windowId2);
        }
        else{
            passwordTextfield.setText("");
            errorLabel.setText("Incorrect shop ID or Password!");
        }

    }
}
