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

    /**
     *
     * @param orderID
     * @return shopName
     */
    public String selectShop(int orderID) {
        String shopName ="";
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT fldName\n" +
                    "FROM tblOrder\n" +
                    "INNER JOIN tblShop ON tblShop.fldShopID = tblOrder.fdlShopID\n" +
                    "where fldOrderID = ? ");
            query.setInt(1, orderID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                shopName = resultSet.getString(1);
                return shopName;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return shopName;
    }

    /**
     *
     * @param email
     * @param shopID
     * inserts into database: email, shopID, order status
     */
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

    /**
     *
     * @param email
     * returns clientName and client phone
     */
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

    /**
     *
     * @param description
     * @param orderID
     * @param clothingTypeName
     * inserts into database : description , orderID, clothing type name and item status
     */
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

    /**
     *
     * @param orderID
     * returns laundry item details : description , item status, clothing type name, laundry item id
     */
    public void selectLaundryItem(int orderID) {
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select top 1 * from tblLaundryItem where fldOrderID = ? order by fldLaundryItemID desc ");
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

    /**
     *
     * @param email
     * @return
     * returns the latest order ID for a given client
     */
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

    /**
     *
     * @param email
     * @return
     * returns boolean if a given email is already registered or not
     */
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

    /**
     *
     * @param fullName
     * @param email
     * @param phoneNo
     *inserts client into database with the given parameters
     */

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

    /**
     *
     * @param shopID
     * @param password
     * @return
     * does the password verification by checking if the given parameters are existing together in the database
     */
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

    /**
     *
     * @param orderID
     * @return
     * returns the total for a given order
     */
    public int selectTotal(int orderID){
        int sum = 0;
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT sum(fldClothingTypePrice) \n" +
                    "FROM tblLaundryItem\n" +
                    "INNER JOIN tblClothingType ON tblLaundryItem.fldClothingTypeName = tblClothingType.fldClothingTypeName\n" +
                    "where fldOrderID = ? ");
            query.setInt(1, orderID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                sum = resultSet.getInt(1);
                return sum;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
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
