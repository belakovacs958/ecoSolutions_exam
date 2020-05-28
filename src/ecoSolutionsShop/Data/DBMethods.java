package ecoSolutionsShop.Data;

import ecoSolutionsShop.Account.ShopAccount;
import ecoSolutionsShop.Controller.LoginController;
import ecoSolutionsShop.Model.ShopID;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {

    /////////////////////////////Fields////////////////////////////////////////


    public String shopID = "";
    private String password = "";
    private String email;
    private String name;
    private String phoneNo;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getName() {
        return name;
    }



    //////////////////////////////Objects///////////////////////////////////////

    ShopID shopIDObject = new ShopID();



    //////////////////////////////Methods////////////////////////////////////////
    public void selectShop() {

    }

    public void selectClient() {

        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblCustomer WHERE fldEmail = ?");
            query.setString(1, email);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString(2);
                phoneNo = resultSet.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


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

    ////////////////////////////////Getters and setters/////////////////////////////


    public String getShopID() {
        return shopID;
    }

    public String getPassword() {
        return password;
    }


}
