package ecoSolutionsShop.Test;

import ecoSolutionsShop.Data.DBMethods;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBMethodsTest {
    DBMethods test = new DBMethods();

    @Test
    public void shouldSelectCredentials() throws Exception{


        test.selectCredentials("0001","12345");


    }

    @Test
    public void testSelectTotal() throws Exception{

        test.selectTotal(4);




    }
}