/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g12swe.addressbook.models.contacts;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author giaro
 */
public class ContactTest {
    
    public ContactTest() {
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
     * Test of getName method, of class Contact.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Contact instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSurname method, of class Contact.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        Contact instance = null;
        String expResult = "";
        String result = instance.getSurname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhoneNumbers method, of class Contact.
     */
    @Test
    public void testGetPhoneNumbers() {
        System.out.println("getPhoneNumbers");
        Contact instance = null;
        List<PhoneNumber> expResult = null;
        List<PhoneNumber> result = instance.getPhoneNumbers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmailAddresses method, of class Contact.
     */
    @Test
    public void testGetEmailAddresses() {
        System.out.println("getEmailAddresses");
        Contact instance = null;
        List<EmailAddress> expResult = null;
        List<EmailAddress> result = instance.getEmailAddresses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Contact.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Contact instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSurname method, of class Contact.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String surname = "";
        Contact instance = null;
        instance.setSurname(surname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEmailAddress method, of class Contact.
     */
    @Test
    public void testAddEmailAddress() throws Exception {
        System.out.println("addEmailAddress");
        String emailAddress = "";
        EntryCategory category = null;
        Contact instance = null;
        instance.addEmailAddress(emailAddress, category);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPhoneNumber method, of class Contact.
     */
    @Test
    public void testAddPhoneNumber() throws Exception {
        System.out.println("addPhoneNumber");
        String phoneNumber = "";
        EntryCategory category = null;
        Contact instance = null;
        instance.addPhoneNumber(phoneNumber, category);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeEmailAddress method, of class Contact.
     */
    @Test
    public void testRemoveEmailAddress() {
        System.out.println("removeEmailAddress");
        String emailAddress = "";
        Contact instance = null;
        instance.removeEmailAddress(emailAddress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePhoneNumber method, of class Contact.
     */
    @Test
    public void testRemovePhoneNumber() {
        System.out.println("removePhoneNumber");
        String phoneNumber = "";
        Contact instance = null;
        instance.removePhoneNumber(phoneNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Contact.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Contact instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Contact.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Contact instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Contact.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Contact o = null;
        Contact instance = null;
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
