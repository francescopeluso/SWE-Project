/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g12swe.addressbook.models;

import g12swe.addressbook.models.contacts.Contact;
import java.util.Set;
<<<<<<< HEAD
=======
import java.util.TreeSet;
import javafx.collections.FXCollections;
>>>>>>> 95a0983e8182ef3957ddd12e2a89b40d0495e734
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
<<<<<<< HEAD
 * @author giaro
=======
 * @author Valerio
>>>>>>> 95a0983e8182ef3957ddd12e2a89b40d0495e734
 */
public class AddressBookTest {
    
    public AddressBookTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
<<<<<<< HEAD
        
        
=======
>>>>>>> 95a0983e8182ef3957ddd12e2a89b40d0495e734
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
<<<<<<< HEAD
        Contact c = new Contact("Lady", "Gaga");
        AddressBook instance = new AddressBook();
        
        instance.addContact(c);
        
        assertTrue(instance.getContactList().contains(new Contact("Lady", "Gaga")));
        
        // TODO review the generated test code and remove the default call to fail.
        
=======
        Contact c = new Contact("Stefani", "Germanotta");
        AddressBook instance = new AddressBook();
        instance.addContact(c);
        assertTrue(instance.getContactList().contains(c));
>>>>>>> 95a0983e8182ef3957ddd12e2a89b40d0495e734
    }

    /**
     * Test of removeContact method, of class AddressBook.
     */
    @Test
    public void testRemoveContact() {
        System.out.println("removeContact");
<<<<<<< HEAD
        Contact c = new Contact("Jay", "Z");
        AddressBook instance = new AddressBook();
        
=======
        Contact c = new Contact("Stefani", "Germanotta");
        AddressBook instance = new AddressBook();
>>>>>>> 95a0983e8182ef3957ddd12e2a89b40d0495e734
        instance.addContact(c);
        boolean expResult = true;
        boolean result = instance.removeContact(c);
        assertEquals(expResult, result);
<<<<<<< HEAD
        // TODO review the generated test code and remove the default call to fail.
       
=======
>>>>>>> 95a0983e8182ef3957ddd12e2a89b40d0495e734
    }

    /**
     * Test of getContactList method, of class AddressBook.
     */
    @Test
    public void testGetContactList() {
        System.out.println("getContactList");
        AddressBook instance = new AddressBook();
<<<<<<< HEAD
        Set<Contact> expResult = null;
        Set<Contact> result = instance.getContactList();
        
        instance.addContact(new Contact("Gianni", "Sperti"));
        
        assertTrue(instance.getContactList().contains(new Contact("Gianni", "Sperti")));
        expResult = instance.getContactList();
        assertEquals(expResult, result);
        
        
        
        // TODO review the generated test code and remove the default call to fail.
=======
        Contact c = new Contact("Stefani", "Germanotta");
        Contact c2 = new Contact("Beyoncé", "Knowles");
        instance.addContact(c);
        instance.addContact(c2);
        Set<Contact> expResult = new TreeSet<>();
        expResult.add(c);
        expResult.add(c2);
        Set<Contact> result = instance.getContactList();
        
        assertEquals(FXCollections.observableSet(expResult), result);
>>>>>>> 95a0983e8182ef3957ddd12e2a89b40d0495e734
    }
    
}
