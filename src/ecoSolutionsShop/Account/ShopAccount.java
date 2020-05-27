package ecoSolutionsShop.Account;


import ecoSolutionsShop.Data.DBMethods;

public class ShopAccount {


    DBMethods dbMethods = new DBMethods();
    private String shopID;
    private String password;


    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //verifies if the password for a given shop ID is matching with the password entered into the login form
    public boolean  verifyCredentials(){
        dbMethods.selectCredentials();

        if (shopID.equals("") || password.equals("")){
            return false;
        }
        else if (shopID.equals(dbMethods.getShopID()) && password.equals(dbMethods.getPassword())){
            return true;
        }
        else{
            System.out.println("Credentials in database and credentials given , doesn't match");
            return false;
        }

    }

}
