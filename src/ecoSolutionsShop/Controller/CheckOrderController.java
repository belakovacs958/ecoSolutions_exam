package ecoSolutionsShop.Controller;

import ecoSolutionsShop.Data.DBMethods;
import ecoSolutionsShop.Main;
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

public class CheckOrderController implements Initializable, windows {

    Controller myController;

    private DBMethods dbMethods = new DBMethods();


    public static ObservableList<LaundryItem> laundryItems = FXCollections.observableArrayList();
    private String itemID = "";

    private String description = dbMethods.getDescription();
    private String itemStatus = dbMethods.getItemStatus();
    private String clothingTypeName = dbMethods.getClothingTypeName();
    private int laundryItemID = dbMethods.getLaundryItemID();


    @FXML
    private TextField itemID_textfield;
    @FXML
    private Label description_label,clothingType_label,itemID_label,itemStatus_label,orderStatus_label;
    @FXML
    private TableView<LaundryItem> order_tableview;
    @FXML
    private TableColumn<LaundryItem, String> description_column;
    @FXML
    private TableColumn<LaundryItem, String> itemStatus_column;
    @FXML
    private TableColumn<LaundryItem, String> clothingType_column;
    @FXML
    private TableColumn<LaundryItem, Integer> itemID_column;
    @FXML
    private ChoiceBox<String> itemStatus_choiceBox, orderStatus_choiceBox;

    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillChoiceBoxes();
        createTableView();


    }

    // sets the Manage Client window
    public void goToManageClient(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId3);
    }

    // sets the Create the New Order window
    public void goToCreateNewOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId2);
    }



    public void go() {
        System.out.println("go() is called");
        order_tableview.getItems().clear();
        itemID = itemID_textfield.getText();
        dbMethods.selectLaundryItems(Integer.parseInt(itemID));
        dbMethods.selectLaundryItemDetails(Integer.parseInt(itemID));
        dbMethods.selectOrderStatus(Integer.parseInt(itemID));
        displayLaundryItems();
        displayItemDetails();
        displayOrderStatus();
    }

    /**
     * part of UC25
     */
    public void setStatuses(){
        System.out.println("setStatuses() is called");
        itemID = itemID_textfield.getText();
        dbMethods.updateOrderStatus(Integer.parseInt(itemID), orderStatus_choiceBox.getValue());
        dbMethods.updateItemStatus(Integer.parseInt(itemID), itemStatus_choiceBox.getValue());

    }
    public void displayItemDetails() {
        System.out.println("displayItemDetails() is called");
        description_label.setText("Description: " + dbMethods.getDescription());
        itemID_label.setText("Item ID: " + itemID_textfield.getText());
        clothingType_label.setText("Clothing type: " + dbMethods.getClothingTypeName());
        itemStatus_label.setText(dbMethods.getItemStatus());

    }

    public void displayLaundryItems() {
        System.out.println("displayLaundryItems() is called");
        order_tableview.setItems(laundryItems);
    }

    public void displayOrderStatus() {
        System.out.println("displayOrderStatus() is called");
        orderStatus_label.setText(dbMethods.getOrderStatus());
    }
    public void createTableView(){
        //these are the columns in the table view
        description_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("description"));
        itemStatus_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("itemStatus"));
        clothingType_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("clothingTypeName"));
        itemID_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, Integer>("laundryItemID"));
    }

    public void fillChoiceBoxes(){
        //this fills the choice boxes
        itemStatus_choiceBox.getItems().addAll(Status.dirtyInShop,Status.inTransportToCenter,Status.cleanInShop,Status.completed);
        itemStatus_choiceBox.setValue(Status.dirtyInShop);
        orderStatus_choiceBox.getItems().addAll(Status.dirtyInShop,Status.inTransportToCenter,Status.cleanInShop,Status.completed);
        orderStatus_choiceBox.setValue(Status.dirtyInShop);
    }
}
