import com.mongodb.assertions.Assertions;
import java.rmi.RemoteException;

import models.user.Account;
import models.user.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class JUnitTest_Account {
    
    Account A;
    User u;
    
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
        A = new Account();
        System.out.println("This object needed before each test");
    }
    
    @After
    public void tearDown() {
        System.out.println("This is running after each test");
        
    }
    
    @Test
    public void Login(){
     
        String email = "sherifAhmed@hotmail.com";
        String password = "sherif2001";
        A.login(email, password);
        assertNotNull(email,"Email should not be null");
    }
    
    @Test
    public void createAccount() throws RemoteException{
        System.out.println("Test cancel airplane method");
        String passportNo = "A24145254";
        String Fname = "Ahmed";
        String Lname = "Mohamed";
        String DOB = "2010";
        String PhoneNo = "01025232114";
        char gender = 'M';
        String username = "Ahmedmohamed12";
        String email = "Ahmed12@yahoo.com";
        String password = "ahmed12345";
        String accType = "Passenger";
        
        u.createAccount(passportNo, Fname, Lname, DOB, PhoneNo, gender, username, email, password, accType);
        assertNotNull(u.getAcc().getUsername(), "Should Not Create Account When User name is Null");
           
        
    }
    
    
}
