package ecoSolutionsShop.Data;


import ecoSolutionsShop.Model.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBMethods {

    ////////////////////////////////////////fields////////////////////////////////////
    private String email;
    private String name;
    private String phoneNo;


    ////////////////////////////////////////objects///////////////////////////////////



    ///////////////////////////////////////methods////////////////////////////////////
    public void selectShop() {

    }

    public void insertOrder(String email, String shopID){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("INSERT INTO tblOrder (fldEmail, fdlDeliveryPointID, fldOrderStatus) " +    // !!!fdlDeliveryPoint has a typo
                    "VALUES (?,?,?);");
            query.setString(1, email);
            query.setString(2, shopID);
            query.setString(3, "dirtyInShop");
            query.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }















    public void selectClient(String email) {

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

    public void insertLaundryItem(String description, int orderID, String clothingTypeID) {
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("INSERT INTO tblLaundryItem (fldDescription, fldOrderID, fldClothingTypeID) " +
                    "VALUES (?,?,?);");
            query.setString(1, description);
            query.setInt(2, orderID);
            query.setString(3, clothingTypeID);
            query.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void selectLaundryItem() {

    }

    public int selectMostRecentOrderIDForGivenEmail(String email) {
        int recentOrderID = 0;
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select top 1 fldOrderID from tblOrder where fldEmail =? order by fldOrderID desc");
            query.setString(1, email);
            query.executeQuery();
            return recentOrderID;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    //checks if the given email is registered for a client in the database
    public boolean isEmailRegistered(String email){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblCustomer WHERE fldEmail = ? ");
            query.setString(1, email);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertClient(String fullName, String email, String phoneNo) {
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("INSERT INTO tblCustomer (fldEmail, fldFullName, fldPhoneNo) " +
                    "VALUES (?,?,?);");
                    query.setString(1, email);
                    query.setString(2, fullName);
                    query.setString(3, phoneNo);
                    query.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //selects password from tblShop for a shopIS which is entered into the login form
    public boolean selectCredentials(String shopID, String password) {
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblShop WHERE fldDeliveryPonintID = ? and fldPassword = ?"); //!!!!be careful there is a typo in the field name
            query.setString(1, shopID);
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




//////////////////////////////////////getters and setters/////////////////////////
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getName() {
        return name;
    }

}
