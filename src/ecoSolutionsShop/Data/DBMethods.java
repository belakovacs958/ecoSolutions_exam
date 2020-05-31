package ecoSolutionsShop.Data;


import ecoSolutionsShop.Model.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {

    ////////////////////////////////////////fields////////////////////////////////////
    private String name;
    private String phoneNo;
    private String description = "";
    private String itemStatus = "";
    private String clothingTypeName = "";
    private int laundryItemID = 0;

    ////////////////////////////////////////objects///////////////////////////////////


    //////////////////////////////Methods////////////////////////////////////////

    public void selectShop() {

    }


    //creates an order for a given email and attaches the shopID where the order ID was created
    public void insertOrder(String email, String shopID){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("INSERT INTO tblOrder (fldEmail, fdlShopID, fldOrderStatus) " +    // !!!fdlShopID has a typo
                    "VALUES (?,?,?);");
            query.setString(1, email);
            query.setString(2, shopID);
            query.setString(3, Status.dirtyInShop);
            query.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //selects a client with a given email address
    public void selectClient(String email) {

        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblClient WHERE fldEmail = ?");
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


    //inserts a laundry item with a given description , order id and a clothing type
    public void insertLaundryItem(String description, int orderID, String clothingTypeName) {
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("INSERT INTO tblLaundryItem (fldDescription, fldOrderID, fldClothingTypeName,fldItemStatus) " +
                    "VALUES (?,?,?,?);");
            query.setString(1, description);
            query.setInt(2, orderID);
            query.setString(3,clothingTypeName);
            query.setString(4,Status.dirtyInShop);
            query.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void selectLaundryItem(int orderID) {
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblLaundryItem WHERE fldOrderID = ?");
            query.setInt(1, orderID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                description = resultSet.getString(2);
                itemStatus = resultSet.getString(5);
                clothingTypeName = resultSet.getString(4);
                laundryItemID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    //select the latest order for a given email address
    public int selectMostRecentOrderIDForGivenEmail(String email) {
        int recentOrderID = 0;
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select top 1 fldOrderID from tblOrder where fldEmail =? order by fldOrderID desc");
            query.setString(1, email);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                recentOrderID = resultSet.getInt(1);
                return recentOrderID;

            }



        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return recentOrderID;

    }


    //checks if the given email is registered for a client in the database
    public boolean isEmailRegistered(String email){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblClient WHERE fldEmail = ? ");
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


    //This method inserts a client with with the given info in the ManageClient controller
    public void insertClient(String fullName, String email, String phoneNo) {
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("INSERT INTO tblClient (fldEmail, fldFullName, fldPhoneNo) " +
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
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * FROM tblShop WHERE fldShopID = ? and fldPassword = ?"); //!!!!be careful there is a typo in the field name
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public String getClothingTypeName() {
        return clothingTypeName;
    }

    public int getLaundryItemID() {
        return laundryItemID;
    }
}
