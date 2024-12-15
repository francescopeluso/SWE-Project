package g12swe.addressbook.models;

import g12swe.addressbook.exceptions.LimitReachedException;
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
 * @file AddressBookTest.java
 * @brief Unit tests for the AddressBook class
 * 
 * This test class verifies the core functionality of the AddressBook class,
 * including adding contacts, removing contacts, and retrieving the contact list.
 * 
 * The tests ensure that the AddressBook methods correctly manipulate the internal
 * ObservableSet of Contact objects.
 * 
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
     * @brief Test the addContact method of AddressBook
     * 
     * Verifies that a contact can be successfully added to the address book
     * and is present in the contact list after addition.
     */
    @Test
    public void testAddContact() throws LimitReachedException {
        System.out.println("T070");
        Contact c = new Contact("Stefani", "Germanotta");
        AddressBook instance = new AddressBook();
        instance.addContact(c);
        assertTrue(instance.getContactList().contains(c));
    }

    /**
     * @brief Test the removeContact method of AddressBook
     * 
     * Checks that a contact can be successfully removed from the address book 
     * and that the removal operation returns true.
     */
    @Test
    public void testRemoveContact() throws LimitReachedException {
        System.out.println("T071");
        Contact c = new Contact("Stefani", "Germanotta");
        AddressBook instance = new AddressBook();
        instance.addContact(c);
        boolean expResult = true;
        boolean result = instance.removeContact(c);
        assertEquals(expResult, result);
    }

    /**
     * @brief Test the getContactList method of AddressBook
     * 
     * Verifies that the contact list returned by getContactList() contains all
     * addedd contacts and matches te expected set.
     */
    @Test
    public void testGetContactList() throws LimitReachedException {
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

    /**
     * @brief Test contact limit in AddressBook
     * 
     * Verifies that attempting to add more than 5000 contacts
     * throws a LimitReachedException
     */
    @Test
    public void testContactLimit() {
        System.out.println("T073");
        AddressBook instance = new AddressBook();
        
        // Add 5000 contacts
        for (int i = 0; i < 5000; i++) {
            try {
                instance.addContact(new Contact("First" + i, "Last" + i));
            } catch (LimitReachedException e) {
                fail("Should not throw exception before limit");
            }
        }
        
        // Verify adding one more throws exception
        assertThrows(LimitReachedException.class, () -> {
            instance.addContact(new Contact("One", "TooMany"));
        });
    }
    
}