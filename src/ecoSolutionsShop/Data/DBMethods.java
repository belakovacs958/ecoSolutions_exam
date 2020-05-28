package ecoSolutionsShop.Data;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


    /////////////////////////////Fields////////////////////////////////////////



public class DBMethods {

    ////////////////////////////////////////fields////////////////////////////////////

    
    private String email;
    private String name;
    private String phoneNo;


    ////////////////////////////////////////objects///////////////////////////////////



    ///////////////////////////////////////methods////////////////////////////////////

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

    public void selectLaundryItem() {

    }

    public void selectOrder() {

    }

    public void insertClient() {

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
