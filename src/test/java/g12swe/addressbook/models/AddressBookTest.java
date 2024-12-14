/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g12swe.addressbook.models;

import g12swe.addressbook.models.contacts.Contact;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
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
        System.out.println("T070");
        Contact c = new Contact("Stefani", "Germanotta");
        AddressBook instance = new AddressBook();
        instance.addContact(c);
        assertTrue(instance.getContactList().contains(c));
    }

    /**
     * Test of removeContact method, of class AddressBook.
     */
    @Test
    public void testRemoveContact() {
        System.out.println("T071");
        Contact c = new Contact("Stefani", "Germanotta");
        AddressBook instance = new AddressBook();
        instance.addContact(c);
        boolean expResult = true;
        boolean result = instance.removeContact(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of getContactList method, of class AddressBook.
     */
    @Test
    public void testGetContactList() {
        System.out.println("T072");
        AddressBook instance = new AddressBook();
        Contact c = new Contact("Stefani", "Germanotta");
        Contact c2 = new Contact("Beyoncé", "Knowles");
        instance.addContact(c);
        instance.addContact(c2);
        Set<Contact> expResult = new TreeSet<>();
        expResult.add(c);
        expResult.add(c2);
        Set<Contact> result = instance.getContactList();
        
        assertEquals(FXCollections.observableSet(expResult), result);
    }
    
}