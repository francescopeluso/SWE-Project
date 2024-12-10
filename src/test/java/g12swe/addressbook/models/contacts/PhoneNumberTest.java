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
     * Test of getCategory method, of class PhoneNumber.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        PhoneNumber instance = new PhoneNumber("1234567890", EntryCategory.WORK);
        EntryCategory expResult = EntryCategory.WORK;
        EntryCategory result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhoneNumber method, of class PhoneNumber.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        String expResult = "1234567890";
        String result = instance.getPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCategory method, of class PhoneNumber.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        EntryCategory category = EntryCategory.WORK;
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        instance.setCategory(category);
        assertEquals(category, instance.getCategory());
        instance.setCategory(EntryCategory.PERSONAL);
        assertNotEquals(category, instance.getCategory());
        assertEquals(EntryCategory.PERSONAL, instance.getCategory());
    }

    /**
     * Test of setPhoneNumber method, of class PhoneNumber.
     */
    @Test
    public void testSetPhoneNumber() {
        System.out.println("setPhoneNumber");
        String phoneNumber = "1234567890";
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        instance.setPhoneNumber(phoneNumber);
        assertEquals("1234567890", instance.getPhoneNumber());
        
        instance.setPhoneNumber("3231212c");
        assertNotEquals("1234567890", instance.getPhoneNumber());
    }

    /**
     * Test of isValid method, of class PhoneNumber.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
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
     * Test of equals method, of class PhoneNumber.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        //equals to null
        Object obj = null;
        PhoneNumber instance = new PhoneNumber("1234567890", null);  
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        
        //equals to a different phoneNumber
        obj = new PhoneNumber("2133445689", null);
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        
        //equals to the same phoneNumber
        obj = new PhoneNumber("1234567890", null);
        result = instance.equals(obj);
        expResult = true;
        assertEquals(expResult, result);
        
        
        //equals to the same instance
        obj = instance;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class PhoneNumber.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        PhoneNumber instance = new PhoneNumber("1234567890", null);
        int expResult = new PhoneNumber("1234567890", null).hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }
    
}
