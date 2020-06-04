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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        //this fills the choice boxes
        itemStatus_choiceBox.getItems().addAll(Status.dirtyInShop,Status.arrivedInCenter,Status.inWashing,Status.inDrying,
                Status.inIroning,Status.readyForTransport,Status.readyInShop,Status.completed);
        itemStatus_choiceBox.setValue(Status.dirtyInShop);
        orderStatus_choiceBox.getItems().addAll(Status.dirtyInShop,Status.arrivedInCenter,Status.inWashing,Status.inDrying,
                Status.inIroning,Status.readyForTransport,Status.readyInShop,Status.completed);
        orderStatus_choiceBox.setValue(Status.dirtyInShop);

        //these are the columns in the table view
        description_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("description"));
        itemStatus_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("itemStatus"));
        clothingType_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("clothingTypeName"));
        itemID_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, Integer>("laundryItemID"));

    }

    // sets the Manage Client window
    public void goToManageClient(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId3);
    }

    // sets the Create the New Order window
    public void goToCreateNewOrder(ActionEvent actionEvent) {
        myController.setWindow(Main.windowId2);
    }

    public void displayItemDetails() {
    }

    public void displayLaundryItems() {

    }

    public void displayOrderStatus() {
    }

    public void go() {
        itemID = itemID_textfield.getText();
        dbMethods.selectLaundryItems(Integer.parseInt(itemID));
        order_tableview.setItems(laundryItems);


    }

    public void setStatuses(){
        itemID = itemID_textfield.getText();
        dbMethods.updateOrderStatus(Integer.parseInt(itemID), orderStatus_choiceBox.getValue());
        dbMethods.updateItemStatus(Integer.parseInt(itemID), itemStatus_choiceBox.getValue());
    }
}
