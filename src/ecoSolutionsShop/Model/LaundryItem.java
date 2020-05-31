package ecoSolutionsShop.Model;

public class LaundryItem {

    private String description, itemStatus, clothingTypeName;
    private int laundryItemID;

    public LaundryItem(String description, int laundryItemID, String itemStatus, String clothingTypeName) {
        this.description = description;
        this.laundryItemID = laundryItemID;
        this.itemStatus = itemStatus;
        this.clothingTypeName = clothingTypeName;
    }
}
