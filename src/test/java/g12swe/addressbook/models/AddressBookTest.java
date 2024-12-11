/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g12swe.addressbook.models;

import g12swe.addressbook.models.contacts.Contact;
import java.util.Set;
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
public class AddressBookTest {
    
    public AddressBookTest() {
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
     * Test of addContact method, of class AddressBook.
     */
    @Test
    public void testAddContact() {
        System.out.println("addContact");
        Contact c = new Contact("Lady", "Gaga");
        AddressBook instance = new AddressBook();
        
        instance.addContact(c);
        
        assertTrue(instance.getContactList().contains(new Contact("Lady", "Gaga")));
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of removeContact method, of class AddressBook.
     */
    @Test
    public void testRemoveContact() {
        System.out.println("removeContact");
        Contact c = new Contact("Jay", "Z");
        AddressBook instance = new AddressBook();
        
        instance.addContact(c);
        boolean expResult = true;
        boolean result = instance.removeContact(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getContactList method, of class AddressBook.
     */
    @Test
    public void testGetContactList() {
        System.out.println("getContactList");
        AddressBook instance = new AddressBook();
        Set<Contact> expResult = null;
        Set<Contact> result = instance.getContactList();
        
        instance.addContact(new Contact("Gianni", "Sperti"));
        
        assertTrue(instance.getContactList().contains(new Contact("Gianni", "Sperti")));
        expResult = instance.getContactList();
        assertEquals(expResult, result);
        
        
        
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
