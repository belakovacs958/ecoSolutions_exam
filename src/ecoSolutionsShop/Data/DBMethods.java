package ecoSolutionsShop.Data;

import ecoSolutionsShop.Account.ShopAccount;
import ecoSolutionsShop.Controller.LoginController;
import ecoSolutionsShop.Model.ShopID;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {


    ShopID shopIDObject = new ShopID();
    public String shopID = "";
    private String password = "";

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }
    public String getShopID() {
        return shopID;
    }

    public String getPassword() {
        return password;
    }
    /*
    String name = "";
    int sellPrice = 0;
    public void testMethod(){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblClothingType WHERE fldClothingTypeID = ?");
            query.setString(1, "a");
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString(2);
                sellPrice = resultSet.getInt(3);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(name + sellPrice);
    }
    */

    public void selectShop() {

    }

    public void selectClient() {

    }

    public void insertLaundryItem() {

    }

    public void selectLaundryItem(){

    }

    public void selectOrder(){

    }

    public void insertClient(){

    }
    //selects password from tblShop for a shopIS which is entered into the login form
    public void selectCredentials(){
        System.out.println(shopID);
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblShop WHERE fldDeliveryPonintID = ?"); //!!!!be careful there is a typo in the field name
            query.setString(1, shopIDObject.getInstance().shopID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                shopID = resultSet.getString(1);
                password = resultSet.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
