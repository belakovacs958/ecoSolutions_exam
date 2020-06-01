package ecoSolutionsShop.Services;

import java.util.*;


public class Invoice {

    ////////////////////////////////////////fields////////////////////////////////////

    private Formatter file;

    ///////////////////////////////////////methods////////////////////////////////////

    //this method creates a file gives it a name, write in some data about the client and the shop and the order total
    public void writeFile(int sum, int orderID, String name, String shopName, String email ){
        try{
            file = new Formatter("C:/Users/KendeSzabo/IdeaProjects/ecoSolutions_exam/src/ecoSolutionsShop/Services/invoices/" + orderID + ".txt");
            System.out.println(orderID + "invoice");
            file.format(String.valueOf(sum));
            file.format(name);
            file.format(shopName);
            file.format(email);
            file.close();
        }
        catch (Exception e){
            System.out.println("Something went wrong during file creation");
        }
    }

}
