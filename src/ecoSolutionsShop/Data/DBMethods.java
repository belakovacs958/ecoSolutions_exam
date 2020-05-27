package ecoSolutionsShop.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {
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
}
