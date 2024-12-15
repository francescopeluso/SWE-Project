package g12swe.addressbook.models.contacts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PhoneNumberTest {
    
    public PhoneNumberTest() {
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
     * @brief Test of getCategory method, of class PhoneNumber.
     * 
     * This test checks that the method getCategory() returns exactly the
     * category of the PhoneNumber instance.
     */
    @Test
    public void testGetCategory() {
        System.out.println("T010");
        PhoneNumber instance = new PhoneNumber("1234567890", EntryCategory.WORK);
        EntryCategory expResult = EntryCategory.WORK;
        EntryCategory result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * @brief Test of getPhoneNumber method, of class PhoneNumber.
     * 
     * This test checks that the method getPhoneNumber() returns exactly the
     * PhoneNumber of the PhoneNumber instance.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("T011");
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        String expResult = "1234567890";
        String result = instance.getPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * @brief Test of setCategory method, of class PhoneNumber.
     * 
     * This test checks that the method setCategory() modifies the instance's
     * category value to the EntryCategory parameter.
     */
    @Test
    public void testSetCategory() {
        System.out.println("T012");
        EntryCategory category = EntryCategory.WORK;
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        instance.setCategory(category);
        assertEquals(category, instance.getCategory());
        instance.setCategory(EntryCategory.PERSONAL);
        assertNotEquals(category, instance.getCategory());
        assertEquals(EntryCategory.PERSONAL, instance.getCategory());
    }

    /**
     * @brief Test of setPhoneNumber method, of class PhoneNumber.
     * 
     * This test checks that the method setPhoneNumber() modifies the instance's
     * phoneNumber value to the string parameter.
     */
    @Test
    public void testSetPhoneNumber() {
        System.out.println("T013");
        String phoneNumber = "1234567890";
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        instance.setPhoneNumber(phoneNumber);
        assertEquals("1234567890", instance.getPhoneNumber());
        
        instance.setPhoneNumber("3231212c");
        assertNotEquals("1234567890", instance.getPhoneNumber());
    }

    /**
     * @brief Test of isValid method, of class PhoneNumber.
     * 
     * This test checks that the method isValid() only returns true when the
     * value of the PhoneNumber attribute follows the correct phone number
     * pattern.
     */
    @Test
    public void testIsValid() {
        System.out.println("T014");
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        boolean expResult = true;
        boolean result = instance.isValid();
        assertEquals(expResult, result);
        
        instance.setPhoneNumber("ciao");
        expResult = false;
        result = instance.isValid();
        assertEquals(expResult, result);
        
    }

    /**
     * @brief Test of equals method, of class PhoneNumber.
     * 
     * This test checks that two instances of PhoneNumber are only equal when 
     * their phoneNumber attribute is the same string.
     */
    @Test
    public void testEquals() {
        System.out.println("T015");
        
        //equals to null
        Object obj = null;
        PhoneNumber instance = new PhoneNumber("1234567890", null);  
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        
        //equals to a different phoneNumber
        System.out.println("T016");
        obj = new PhoneNumber("2133445689", null);
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        
        //equals to the same phoneNumber
        System.out.println("T017");
        obj = new PhoneNumber("1234567890", null);
        result = instance.equals(obj);
        expResult = true;
        assertEquals(expResult, result);
        
        
        //equals to the same instance
        System.out.println("T018");
        obj = instance;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * @brief Test of hashCode method, of class PhoneNumber.
     * 
     * This test checks that two different instances of PhoneNumber generate 
     * different hashCodes.
     */
    @Test
    public void testHashCode() {
        System.out.println("T019");
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        int expResult = new PhoneNumber("1234567890", null).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
