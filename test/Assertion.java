/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class Assertion {

    @Test
    public void test() {
        String obj1 = "junit";
        String obj2 = "junit";
        String obj3 = "test";
        String obj4 = "test";
        String obj5 = null;
        int var1 = 1;
        int var2 = 2;

        
        
        /*
    The assertEquals() method will return normally if the two compared objects are equal, 
        otherwise a failure will be displayed in the JUnit window and the test will abort.
        */
        assertEquals(obj1, obj2);
        
        /*
    The assertSame() and assertNotSame() methods 
        tests if two object references point to exactly the same object.

        */
        assertSame(obj3, obj4);
        assertNotSame(obj2, obj4);
        
        /*
        The assertNull() and assertNotNull() methods 
        test whether a variable is null or not null.
        */
        assertNotNull(obj1);
        assertNull(obj5);
        
        /*
        The assertTrue() and assertFalse() methods 
        tests if a condition or a variable is true or false
        */
         double result= var1 + var2;
         assertTrue(result == 3);

}
    
    
    
    }

    
   

