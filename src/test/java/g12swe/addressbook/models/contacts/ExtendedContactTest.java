/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g12swe.addressbook.models.contacts;

import java.time.LocalDateTime;
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
public class ExtendedContactTest {
    
    public ExtendedContactTest() {
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
     * Test of getAddress method, of class ExtendedContact.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        ExtendedContact instance = new ExtendedContact(null, null, "casamia", null, null, null);
        String expResult = "casamia";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        expResult = "casatua";
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class ExtendedContact.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "casamia";
        ExtendedContact instance = new ExtendedContact(null, null, null, null, null, null);
        instance.setAddress(address);
        assertEquals(address, instance.getAddress());
        
        instance.setAddress("casatua");
        assertNotEquals(address, instance.getAddress());
    }

    /**
     * Test of getBirthday method, of class ExtendedContact.
     */
    @Test
    public void testGetBirthday() {
        System.out.println("getBirthday");
        ExtendedContact instance = new ExtendedContact(null, null, null, LocalDateTime.parse("2007-12-03T10:15:30"), null, null);
        LocalDateTime expResult = LocalDateTime.parse("2007-12-03T10:15:30");
        LocalDateTime result = instance.getBirthday();
        assertEquals(expResult, result);
        
        expResult = LocalDateTime.parse("2008-12-03T10:15:30");
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setBirthday method, of class ExtendedContact.
     */
    @Test
    public void testSetBirthday() {
        System.out.println("setBirthday");
        LocalDateTime birthday = LocalDateTime.parse("2007-12-03T10:15:30");
        ExtendedContact instance = new ExtendedContact(null, null, null, null, null, null);
        instance.setBirthday(birthday);
        
        assertEquals(birthday, instance.getBirthday());
        
    }

    /**
     * Test of getNotes method, of class ExtendedContact.
     */
    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
        ExtendedContact instance = new ExtendedContact(null, null, null, null, "nota", null);
        String expResult = "nota";
        String result = instance.getNotes();
        assertEquals(expResult, result);
        
        assertNotEquals("notoo", instance.getNotes());
    }

    /**
     * Test of setNotes method, of class ExtendedContact.
     */
    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        String notes = "scemo chi legge";
        ExtendedContact instance = new ExtendedContact(null, null, null, null, null, null);
        instance.setNotes(notes);
        assertEquals(notes, instance.getNotes());
        
        assertNotEquals("s", instance.getNotes());
    }

    /**
     * Test of getPronouns method, of class ExtendedContact.
     */
    @Test
    public void testGetPronouns() {
        System.out.println("getPronouns");
        ExtendedContact instance = new ExtendedContact(null, null, null, null, null, "he/him");
        String expResult = "he/him";
        String result = instance.getPronouns();
        assertEquals(expResult, result);
        expResult = "she/her";
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setPronouns method, of class ExtendedContact.
     */
    @Test
    public void testSetPronouns() {
        System.out.println("setPronouns");
        String pronouns = "he/him";
        ExtendedContact instance = new ExtendedContact(null, null, null, null, null, null);
        instance.setPronouns(pronouns);
        assertEquals(instance.getPronouns(), "he/him");
        assertNotEquals(instance.getPronouns(), "she/her");
    }
    
}
