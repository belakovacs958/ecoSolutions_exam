package ecoSolutionsShop.Model;

public class ShopID {
    public String shopID;

    private static ShopID instance = null;

    public ShopID(){
    }
    public static ShopID getInstance(){
        if (instance == null){
            instance = new ShopID();
        }
        return instance;

    }
}
