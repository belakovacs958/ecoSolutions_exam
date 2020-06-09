package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Account.ClientAccount;
import ecoSolutionsShop.Account.ShopAccount;
import ecoSolutionsShop.Data.DBMethods;
import ecoSolutionsShop.Main;
import ecoSolutionsShop.Model.ClothingType;
import ecoSolutionsShop.Model.LaundryItem;
import ecoSolutionsShop.Services.Invoice;
import ecoSolutionsShop.Services.QRCode;
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



public class NewOrderController implements Initializable, windows {

    ////////////////////////////////////////objects///////////////////////////////////

    Controller myController;
    ClientAccount clientAccount = new ClientAccount();
    DBMethods dbMethods = new DBMethods();
    ShopAccount shopAccount = new ShopAccount();
    ObservableList<LaundryItem> laundryItems = FXCollections.observableArrayList();
    Invoice invoice = new Invoice();
    QRCode qrCode = new QRCode();


    ////////////////////////////////////////fields////////////////////////////////////

    int recentOrderID = 0;
    private String description = dbMethods.getDescription();
    private String itemStatus = dbMethods.getItemStatus();
    private String clothingTypeName = dbMethods.getClothingTypeName();
    private int laundryItemID = dbMethods.getLaundryItemID();

    ///////////////////////////////////////FXML///////////////////////////////////////

    @FXML
    private TextField clientEmail_textfield, itemDescription_textfield, orderID_textfield;
    @FXML
    private Label clientName_label;
    @FXML
    private ChoiceBox<String> clothingType_choiceBox;
    @FXML
    private TableView <LaundryItem> tableView;
    @FXML
    private TableColumn<LaundryItem, String> description_column;
    @FXML
    private TableColumn<LaundryItem, String> itemStatus_column;
    @FXML
    private TableColumn<LaundryItem, String> clothingType_column;
    @FXML
    private TableColumn<LaundryItem, Integer> itemID_column;


    //////////////////////////////////////////override///////////////////////////////////////////
    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //these are the values in the dropdown menu
        clothingType_choiceBox.getItems().addAll(ClothingType.pants,ClothingType.dress,ClothingType.t_shirt,ClothingType.shirt,
                ClothingType.skirt,ClothingType.chef_suit,ClothingType.police_uniform,ClothingType.suit,ClothingType.jumpsuit,
                ClothingType.jacket,ClothingType.vest,ClothingType.blazer,ClothingType.coat);
        clothingType_choiceBox.setValue(ClothingType.pants);

        //these are the columns in the table view
        description_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("description"));
        itemStatus_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("itemStatus"));
        clothingType_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("clothingTypeName"));
        itemID_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, Integer>("laundryItemID"));
    }

    ///////////////////////////////////////methods////////////////////////////////////

    // set the Manage Client window
    public void goToManageClient(ActionEvent actionEvent) {
        System.out.println("goToManageClient() is called");
        myController.setWindow(Main.windowId3);
    }

    // set the Check Order
    public void goToCheckOrder(ActionEvent actionEvent) {
        System.out.println("goToCheckOrder() is called");
        myController.setWindow(Main.windowId4);
    }

    //creates an object which is a new row in the table view
    public LaundryItem getLaundryItem(){
        System.out.println("getLaundryItem() is called");
        return new LaundryItem(dbMethods.getDescription(),dbMethods.getLaundryItemID(),dbMethods.getItemStatus(),dbMethods.getClothingTypeName());
    }


    //This method displays the client's name with the given email , if an account exists for that email.
    // Sets email variable for clientAccount object for the given value
    public void createOrder() {
        System.out.println("createOrder() is called");
        dbMethods.selectClient(clientEmail_textfield.getText());


        if (dbMethods.isEmailRegistered(clientEmail_textfield.getText())==true){
            clientName_label.setText("The order is created to " + dbMethods.getName()+ "'s account! Put in laundry items");
            dbMethods.insertOrder(clientEmail_textfield.getText(),shopAccount.getShopID());
            clientAccount.setEmail(clientEmail_textfield.getText());
            clientEmail_textfield.setText("");

        }
        //displays an error message if there is no account registered with the given email
        else{
            clientName_label.setText("There is no account registered for the email: " + clientEmail_textfield.getText());
            clientEmail_textfield.setText("");

        }
    }


    //Adds a laundry item for a given email's latest order, if the order id textfield is filled then it adds the laundry item to that order
    public void addItem() {
        System.out.println("addItem() is called");
        recentOrderID = dbMethods.selectMostRecentOrderIDForGivenEmail(clientAccount.getEmail());
        dbMethods.insertLaundryItem(itemDescription_textfield.getText(),recentOrderID,clothingType_choiceBox.getValue());

        dbMethods.selectLaundryItem(recentOrderID);
        laundryItems.add(getLaundryItem());
        tableView.setItems(laundryItems);
        qrCode.generateQRCode(dbMethods.getLaundryItemID());
    }

    //this is the button which initiates the createOrder() method
    public void go() {
        System.out.println("go() is called");
        createOrder();
        tableView.getItems().clear();

    }

    //this method creates an invoice.txt file with the client details and the total and the shop which it was created in.
    //the file name is the orderID
    public void finishOrder() {
        System.out.println("finishOrder() is called");
        invoice.writeFile(dbMethods.selectTotal(recentOrderID),recentOrderID,dbMethods.getName(),
                dbMethods.selectShop(recentOrderID),ClientAccount.email);
        tableView.getItems().clear();
        itemDescription_textfield.setText("");
        clothingType_choiceBox.setValue(ClothingType.pants);
        clientName_label.setText("");
    }
}
