import java.rmi.RemoteException;
import models.airline.Airplane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitTest_Airplane {
    
    Airplane airplane;
    
    @BeforeClass
    public static void setUpClass() {
       System.out.println("Testing is Running for the class");
    }
    
    @AfterClass
    public static void tearDownClass() { 
       System.out.println("Tesing Done for the class");
    }
    
        @Before
    public void setUp() throws RemoteException {
        airplane = new Airplane();
        System.out.println("This object needed before each test");
    }
    
    @After
    public void tearDown() {
        System.out.println("This is running after each test");
        
    }
    
    @Test
    public void TestAirplaneMaxTravelDistance() throws RemoteException{
     
     double maxDistance = airplane.getMaxTravelDistance();
     
     assertTrue(maxDistance < 6000);
    }
    
}
