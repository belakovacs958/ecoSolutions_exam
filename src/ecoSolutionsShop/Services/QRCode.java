package ecoSolutionsShop.Services;

import java.util.Formatter;

public class QRCode {

    private Formatter file;


    public void generateQRCode(int laundryItemID){
        try{
            file = new Formatter("C:/Users/KendeSzabo/IdeaProjects/ecoSolutions_exam/src/ecoSolutionsShop/Services/QRCodes/" + laundryItemID + ".txt");
            file.format(String.valueOf(laundryItemID));
            file.close();

        }
        catch (Exception e){
            System.out.println("Something went wrong during file creation");
        }
    }
}
