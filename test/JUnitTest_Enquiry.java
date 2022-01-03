
import models.ticket.Enquiry;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author karim
 */
public class JUnitTest_Enquiry {
    Enquiry e;
    
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
        e = new Enquiry();
        System.out.println("This object needed before each test");
    }
    
    @After
    public void tearDown() {
        System.out.println("This is running after each test");
        
    }
    
    @Test
    public void addEnquiryTest(){
        System.out.println("Test Add Enquiry method");
        String title = "Trip 516";
        String description ="I lost my bag in this trip";
        e.createEnquiry(title, description);
        
        assertNotNull(description, "Should Not Add enquiry if the description is Null");
        
    }
}
