/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g12swe.addressbook.models.contacts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Valerio
 */
public class EmailAddressTest {
    
    public EmailAddressTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getCategory method, of class EmailAddress.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        EntryCategory expResult = EntryCategory.WORK;
        EntryCategory result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmailAddress method, of class EmailAddress.
     */
    @Test
    public void testGetEmailAddress() {
        System.out.println("getEmailAddress");
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        String expResult = "mariorossi@gmail.com";
        String result = instance.getEmailAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCategory method, of class EmailAddress.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        EntryCategory category = EntryCategory.PERSONAL;
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", category);
        
        assertNotEquals(instance.getCategory(), EntryCategory.WORK);
        instance.setCategory(EntryCategory.WORK);
        assertEquals(instance.getCategory(), EntryCategory.WORK);
    }

    /**
     * Test of setEmailAddress method, of class EmailAddress.
     */
    @Test
    public void testSetEmailAddress() {
        System.out.println("setEmailAddress");
        String emailAddress = "mariorossi@gmail.com";
        EmailAddress instance = new EmailAddress("stefanigermanotta@gmail.com", EntryCategory.WORK);
        instance.setEmailAddress(emailAddress);
        assertEquals(emailAddress, instance.getEmailAddress());
    }

    /**
     * Test of isValid method, of class EmailAddress.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        EmailAddress instance = new EmailAddress();
        boolean expResult = false;
        instance.setEmailAddress("ciao");
        boolean result = instance.isValid();
        assertEquals(expResult, result);
        
        instance.setEmailAddress("mariorossi@gmail.com");
        expResult = true;
        result = instance.isValid();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class EmailAddress.
     */
    @Test
    public void testEquals() {
        
       //confronto con null
        System.out.println("equals");
        Object obj = null;
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        //due email diverse
        obj = new EmailAddress("mi@gmail.com", EntryCategory.WORK);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        //due email uguali
        obj = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK); 
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hashCode method, of class EmailAddress.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        int expResult = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
