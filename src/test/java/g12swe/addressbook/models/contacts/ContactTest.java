/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g12swe.addressbook.models.contacts;

import g12swe.addressbook.exceptions.InvalidEmailAddressException;
import g12swe.addressbook.exceptions.InvalidPhoneNumberException;
import java.util.ArrayList;
import java.util.List;
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
     * @brief Test of getName method, of class Contact.
     * 
     * This test makes sure the return value of the testGetName method 
     * corresponds to the instance's name attribute.
     * 
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Contact instance = new Contact("Stefani", "Germanotta");
        String expResult = "Stefani";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * @brief Test of getSurname method, of class Contact.
     * 
     * This test makes sure the return value of the testGetSurname corresponds 
     * to the instance's surname attribute. 
     * 
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        Contact instance = new Contact("Stefani", "Germanotta");
        String expResult = "Germanotta";
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * @brief Test of getPhoneNumbers method, of class Contact.
     * 
     * This test checks that the return of the class getPhoneNumbers() is a
     * list containing the contact's phoneNumbers.
     * 
     */
    @Test
    public void testGetPhoneNumbers() throws InvalidPhoneNumberException {
        System.out.println("getPhoneNumbers");
        Contact instance = new Contact("Stefani", "Germanotta");
        instance.addPhoneNumber("3311410705", EntryCategory.WORK);
        
        List<PhoneNumber> expResult = new ArrayList<>();
        expResult.add(new PhoneNumber("3311410705", EntryCategory.WORK));
        
        List<PhoneNumber> result = instance.getPhoneNumbers();
        assertEquals(expResult, result);
        
        expResult.add(new PhoneNumber("3334445556", EntryCategory.WORK));
        instance.addPhoneNumber("3334445556", EntryCategory.WORK);
        result = instance.getPhoneNumbers();
        assertEquals(expResult, result);
        
        expResult.add(new PhoneNumber("1111153456", EntryCategory.WORK));
        result = instance.getPhoneNumbers();
        assertNotEquals(expResult, result);
    }

    /**
     * @brief Test of getEmailAddresses method, of class Contact.
     * 
     * This test checks that the return of the class getEmailAddresses() is a
     * list containing the contact's email addresses.
     * 
     */
    @Test
    public void testGetEmailAddresses() throws InvalidEmailAddressException {
        System.out.println("getEmailAddresses");
        Contact instance = new Contact("Stefani", "Germanotta");
        instance.addEmailAddress("uwu@gmail.com", EntryCategory.WORK);
        
        List<EmailAddress> expResult = new ArrayList<>();
        expResult.add(new EmailAddress("uwu@gmail.com", EntryCategory.WORK));
        
        List<EmailAddress> result = instance.getEmailAddresses();
        assertEquals(expResult, result);
        
        result = instance.getEmailAddresses();
        expResult.add(new EmailAddress("uwu2@gmail.com", EntryCategory.WORK));
        assertNotEquals(expResult, result);
    }

    /**
     * @brief Test of setName method, of class Contact.
     * 
     * This test checks that the class SetName() correctly modifies the
     * contact instance's name to the string it has as parameter.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Stefani";
        Contact instance = new Contact("STEFANIA", "Germanotta");
        instance.setName(name);
        assertEquals(name, instance.getName());
        
        assertNotEquals("STEFANIA", instance.getName());
    }

    /**
     * @brief Test of setSurname method, of class Contact.
     * 
     * This test checks that the class SetName() correctly modifies the
     * contact instance's name to the string it has as parameter.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String surname = "Germanotta";
        Contact instance = new Contact("Stefani", "GERMANIA");
        instance.setSurname(surname);
        assertEquals(surname, instance.getSurname());
        
        assertNotEquals("GERMANIA", instance.getSurname());
    }

    /**
     * @brief Test of addEmailAddress method, of class Contact.
     * 
     * This test checks the correct addition of the input inserted in the 
     * addEmailAddress()'s method to the contact's email addresses.
     */
    @Test
    public void testAddEmailAddress() throws Exception {
        System.out.println("addEmailAddress");
        String emailAddress = "mariorossi@gmail.com";
        Contact instance = new Contact("Mario", "Rossi");
        instance.addEmailAddress(emailAddress, EntryCategory.WORK);
        
        assertTrue(instance.getEmailAddresses().contains(new EmailAddress(emailAddress, EntryCategory.WORK)));
    }

    /**
     * @brief Test of addPhoneNumber method, of class Contact.
     * 
     * This test checks the correct addition of the input inserted in the 
     * addTelephoneNumber()'s method to the contact's telephone numbers.
     */
    @Test
    public void testAddPhoneNumber() throws Exception {
        System.out.println("addPhoneNumber");
        String phoneNumber = "1234567890";
        Contact instance = new Contact("Mario", "Rossi");
        instance.addPhoneNumber(phoneNumber, EntryCategory.WORK);
        
        assertTrue(instance.getPhoneNumbers().contains(new PhoneNumber(phoneNumber, EntryCategory.WORK)));
    }

    /**
     * @brief Test of removeEmailAddress method, of class Contact.
     * 
     * This test checks the correct removal of the inserted email address in the
     * contact's email addresses.
     */
    @Test
    public void testRemoveEmailAddress() throws InvalidEmailAddressException {
        System.out.println("removeEmailAddress");
        String emailAddress = "mariorossi@gmail.com";
        Contact instance = new Contact("Mario", "Rossi");
        instance.addEmailAddress(emailAddress, EntryCategory.WORK);
        assertTrue(instance.getEmailAddresses().contains(new EmailAddress(emailAddress, EntryCategory.WORK)));
        
        instance.removeEmailAddress(emailAddress);
        assertFalse(instance.getEmailAddresses().contains(new EmailAddress(emailAddress, EntryCategory.WORK)));
    }

    /**
     * @brief Test of removePhoneNumber method, of class Contact.
     * 
     * This test checks the correct removal of the inserted telephone number in 
     * the contact's telephone numbers.
     */
    @Test
    public void testRemovePhoneNumber() throws InvalidPhoneNumberException {
        System.out.println("removePhoneNumber");
        String phoneNumber = "1234567890";
        Contact instance = new Contact("Mario", "Rossi");
        instance.addPhoneNumber(phoneNumber, EntryCategory.WORK);
        assertTrue(instance.getPhoneNumbers().contains(new PhoneNumber(phoneNumber, EntryCategory.WORK)));
        
        
        instance.removePhoneNumber(phoneNumber);
        assertFalse(instance.getPhoneNumbers().contains(new PhoneNumber(phoneNumber, EntryCategory.WORK)));
    }

    /**
     * @brief Test of hashCode method, of class Contact.
     * 
     * This test checks that two different contacts generate different
     * hashCodes 
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Contact instance = new Contact("Mario", "Rossi");
        int expResult = new Contact("Mario", "Rossi").hashCode();
        int result = instance.hashCode();
        assertNotEquals(expResult, result);
    }

    /**
     * @brief Test of equals method, of class Contact.
     * 
     * This test checks that two different contacts are only equal to one 
     * another if they're the exact same instance.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Contact instance = new Contact("Mario", "Rossi");
        boolean expResult = false;
        boolean result = instance.equals(obj);   //confronto un contatto con un 
                                                 // null
        assertEquals(expResult, result);
        
        obj = new Contact("Stefani", "Germanotta");
        result = instance.equals(obj);
        assertEquals(expResult, result);   //confronto due contatti con nomi
                                           // diversi
        
                                           
        obj = new Contact("Mario", "Rossi");
        result = instance.equals(obj);
        //expResult = true;
        assertEquals(expResult, result);   //confronto due contatti con nomi
                                           //uguali
        
        obj = instance;
        expResult = true;
        result = instance.equals(obj);    //confronto il contatto con se stesso
        assertEquals(expResult, result);
                                           
    }

    /**
     * @brief Test of compareTo method, of class Contact.
     * 
     * This test checks that the correct order of magnitude 
     * between two instances of contact only relies on their uniqueID.
     */
    @Test
    public void testCompareTo() {
        
        System.out.println("compareTo");
        
        //confronto due contatti uguali
        Contact o = new Contact("Stefani", "Germanotta"); 
        Contact instance = new Contact("Stefani", "GermanoTTa"); 
        int expResult = 0;
        int result = instance.compareTo(o);
        assertNotEquals(expResult, result);
        
        //confronto due contatti con cognomi diversi
        o.setSurname("!");                 
        result = instance.compareTo(o); 
        assertNotEquals(expResult, result);
        
        //confronto due contatti con nomi diversi
        o.setName("C");      
        o.setSurname("Germanotta");
        result = instance.compareTo(o); 
        assertNotEquals(expResult, result);
        
        //confronto un contatto con se stesso
        result = instance.compareTo(instance);
        assertEquals(expResult, result);
    }
    
}