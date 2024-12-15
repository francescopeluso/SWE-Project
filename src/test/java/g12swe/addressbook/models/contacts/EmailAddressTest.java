package g12swe.addressbook.models.contacts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @file EmailAddressTest.java
 * @brief Unit tests for the EmailAddress class
 * 
 * This test class verifies the core functionality of the EmailAddress class.
 * 
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
     * @brief Test of getCategory method, of class EmailAddress.
     * 
     * This test checks that the return value of the getCategory() method is
     * the category of the instance.
     */
    @Test
    public void testGetCategory() {
        System.out.println("T001");
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        EntryCategory expResult = EntryCategory.WORK;
        EntryCategory result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * @brief Test of getEmailAddress method, of class EmailAddress.
     * 
     * This test checks that the return value of the getEmailAddress() method is
     * the email address of the instance.
     */
    @Test
    public void testGetEmailAddress() {
        System.out.println("T002");
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        String expResult = "mariorossi@gmail.com";
        String result = instance.getEmailAddress();
        assertEquals(expResult, result);
    }

    /**
     * @brief Test of setCategory method, of class EmailAddress.
     * 
     * This test checks that the method setCategory() correctly changes the 
     * instance's category.
     */
    @Test
    public void testSetCategory() {
        System.out.println("T003");
        EntryCategory category = EntryCategory.PERSONAL;
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", category);
        
        assertNotEquals(instance.getCategory(), EntryCategory.WORK);
        instance.setCategory(EntryCategory.WORK);
        assertEquals(instance.getCategory(), EntryCategory.WORK);
    }

    /**
     * @brief Test of setEmailAddress method, of class EmailAddress.
     * 
     * This test checks that the method setEmailAddress() correctly changes the
     * instance's email address.
     */
    @Test
    public void testSetEmailAddress() {
        System.out.println("T004");
        String emailAddress = "mariorossi@gmail.com";
        EmailAddress instance = new EmailAddress("stefanigermanotta@gmail.com", EntryCategory.WORK);
        instance.setEmailAddress(emailAddress);
        assertEquals(emailAddress, instance.getEmailAddress());
    }

    /**
     * @brief Test of isValid method, of class EmailAddress.
     * 
     * This test checks that the method isValid() only accepts email addresses
     * that follow the correct email format.
     */
    @Test
    public void testIsValid() {
        System.out.println("T005");
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
     * @brief Test of equals method, of class EmailAddress.
     * 
     * This test checks that two instances of EmailAddress are only equal when 
     * their emailAddress attribute is the same.
     */
    @Test
    public void testEquals() {
        
       // compare with null
        System.out.println("T006");
        Object obj = null;
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        // two different emails
        System.out.println("T007");
        obj = new EmailAddress("mi@gmail.com", EntryCategory.WORK);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        // two equal email
        System.out.println("T008");
        obj = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK); 
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
    }

    /**
     * @brief Test of hashCode method, of class EmailAddress.
     * 
     * This test checks that two different instances of EmailAddress generate 
     * different hashCodes.
     */
    @Test
    public void testHashCode() {
        System.out.println("T009");
        EmailAddress instance = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK);
        int expResult = new EmailAddress("mariorossi@gmail.com", EntryCategory.WORK).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
