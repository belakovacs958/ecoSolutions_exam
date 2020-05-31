package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Account.ClientAccount;
import ecoSolutionsShop.Account.ShopAccount;
import ecoSolutionsShop.Data.DBMethods;
import ecoSolutionsShop.Main;
import ecoSolutionsShop.Model.ClothingType;
import ecoSolutionsShop.Model.LaundryItem;
import ecoSolutionsShop.Model.Status;
import ecoSolutionsShop.View.UIControl.Controller;
import ecoSolutionsShop.View.UIControl.windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class NewOrderController implements Initializable, windows {

    Controller myController;
    ClientAccount clientAccount = new ClientAccount();
    DBMethods dbMethods = new DBMethods();
    ShopAccount shopAccount = new ShopAccount();
    ObservableList<LaundryItem> laundryItems = FXCollections.observableArrayList();

    int recentOrderID = 0;


    @FXML
    private TextField clientEmail_textfield, itemDescription_textfield, orderID_textfield;

    @FXML
    private Label clientName_label;

    @FXML
    private ChoiceBox<String> clothingType_choiceBox;

    @FXML
    private TableView <LaundryItem> tableView;

    @FXML
    private TableColumn<LaundryItem, String> description_column, itemStatus_column, clothingType_column;

    @FXML
    private TableColumn<LaundryItem, Integer> itemID_column;








    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
        clothingType_choiceBox.getItems().addAll(ClothingType.pants,ClothingType.dress,ClothingType.t_shirt,ClothingType.shirt,ClothingType.skirt,ClothingType.chef_suit,ClothingType.police_uniform,ClothingType.suit,ClothingType.jumpsuit,ClothingType.jacket,ClothingType.vest,ClothingType.blazer,ClothingType.coat);
        //clothingType_choiceBox.setValue(ClothingType.pants);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        description_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("description"));
        itemStatus_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("itemStatus"));
        clothingType_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("clothingTypeName"));
        itemID_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, Integer>("laundryItemID"));

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


    public LaundryItem getLaundryItem(){
        return new LaundryItem(dbMethods.getDescription(),dbMethods.getLaundryItemID(),dbMethods.getItemStatus(),dbMethods.getClothingTypeName());
    }

    //Adds a laundry item for a given email's latest order, if the order id textfield is filled then it adds the laundry item to that order
    public void addItem() {
        if (!orderID_textfield.getText().equals("")){
            dbMethods.insertLaundryItem(itemDescription_textfield.getText(),Integer.parseInt(orderID_textfield.getText()),clothingType_choiceBox.getValue());
        }
        else{
            recentOrderID = dbMethods.selectMostRecentOrderIDForGivenEmail(clientAccount.getEmail());
            dbMethods.insertLaundryItem(itemDescription_textfield.getText(),recentOrderID,clothingType_choiceBox.getValue());
        }
        dbMethods.selectLaundryItem(recentOrderID);
        laundryItems.add(getLaundryItem());
        tableView.setItems(laundryItems);

    }

    public void go() {
        createOrder();



    }
}
