import java.rmi.RemoteException;
import java.util.ArrayList;

import models.airline.Host;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitTest_Host {
    
    Host host;
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Testing is Running for the class");
    }
    
    @AfterClass
    public static void tearDownClass() {
       
        System.out.println("Tesing Done for the class");
    }
    
        @Before
    public void setUp() {
                
        System.out.println("This object needed before each test");
    }
    
    @After
    public void tearDown() {
        System.out.println("This is running after each test");
        
    }
    
    @Test
    public void addHostTest() throws RemoteException{
     
        String passportNo= "1";
        String name = "ahmed";
        ArrayList<String> languages = new  ArrayList<String>();
        languages.add("arabic english");
        host.addHost(passportNo, name, languages);
        assertNotNull(languages);
    }
}
