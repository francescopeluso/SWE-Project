package g12swe.addressbook.efficiency;

import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableSet;


/**
 * @file EfficiencyTestMain.java
 * @brief Main class for performance testing of AddressBook operations
 * 
 * This class performs two semi-automated efficiency tests:
 * 1. Measuring the time required to load 5000 contacts from a file
 * 2. Measuring the time to search for a specific contact in a 5000-contact
 *    address book
 * 
 * The tests use System.nanoTime() per precise performance measurements.
 * 
 */
public class EfficiencyTestMain {

    
    /**
     * @brief Main method executing performance tests for AddressBook 
     * 
     * Performs two performance tests:
     * - File Import Test: measures time to load 5000 contacts
     * - Contact Search Test: measures time to find a specific contact
     * 
     * @param[in] args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        // Initialize AddressBook and EfficiencyTest
        AddressBook ab = new AddressBook();
        EfficiencyTest test = new EfficiencyTest("C:\\Users\\ACER\\G12-Rubrica\\test.bin", ab.getContactList());
        
        //test.generateFile(5000); // Uncomment to generate test file
        
        // --- FIRST TEST CASE: FILE LOADING ---
        System.out.println("T068");
        ObservableSet<Contact> contacts = ab.getContactList();
        
        // Measure time to import contacts from file
        long startTime1 = System.nanoTime();
        contacts = test.importFromFile();
        long endTime1 = System.nanoTime();
        
        // Calculate and print import duration
        // Average loading time: approximately 0.15 seconds (Excellent!!!)
        long duration1 = (endTime1-startTime1);
        System.out.println("Durata caricamento file (ns): " + duration1);
        
        // --- END OF FIRST TEST CASE ---
        
        
        // --- SECOND TEST CASE: CONTACT SEARCH ---
        System.out.println("T069");
        
        // Convert to list to access specific index
        List<Contact> contacts2 = new ArrayList<>(contacts);
        Contact c = contacts2.get(2423);
        
        // Measure time to search for a specific contact
        long startTime2 = System.nanoTime();
        boolean b = contacts.contains(c);
        long endTime2 = System.nanoTime();
        
        // Calculate and print search duration
        // Average search time: approximately 0.2 milliseconds (Excellent!!!)
        long duration2 = (endTime2-startTime2);
        System.out.println("Durata ricerca di un contatto (ns): " + duration2);
        
        // --- END OF SECOND TEST CASE ---
    }
    
}
