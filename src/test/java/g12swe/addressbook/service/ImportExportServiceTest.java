package g12swe.addressbook.service;

import g12swe.addressbook.models.contacts.Contact;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @file ImportExportService.java
 * @brief Unit tests for the ImportExportService class handling VCard operations
 * 
 * This test class verifies the functionality of ImportExportService methods for
 * importing and exporting contacts using the VCard (vcf) file format.
 * 
 * VCard (Virtual Contact) is a file format standard for electronic business cards,
 * typically used for exchanging contact information between different applications.
 * 
 */
public class ImportExportServiceTest {
    
    /**
     * @brief Object for exporting a single contact to vcf
     */
    private static ImportExportService exportSingleContact;
    
    /**
     * @brief Object for exporting an entire address book to vcf
     */
    private static ImportExportService exportAddressBook;
    
    /**
     * @brief Object for importing a single contact from vcf
     */
    private static ImportExportService importSingleContact;
    
    /**
     * @brief Object for importing an entire address book from vcf
     */
    private static ImportExportService importAddressBook;
    
    /**
     * @brief ObservableSet to store imported address book contacts
     */
    private static ObservableSet<Contact> importedAddressBook;
    
    /**
     * @brief Single imported contact from vcf
     */
    private static Contact importedContact;
    
    /**
     * @brief ObservableSet of contacts to be exported to vcf
     */
    private static ObservableSet<Contact> addressBookToExport;
    
    /**
     * @brief Single contact to be exported to vcf
     */
    private static Contact contactToExport;
    
    
    public ImportExportServiceTest() {
    }
    
    /**
     * @brief @brief Set up resources before running the test class
     * 
     * Sets up temporary directiories and file paths for vcf import/export tests.
     * Uses system temporary directory to create test vcf files.
     * 
     */
    @BeforeAll
    public static void setUpClass() {
        importedAddressBook = FXCollections.observableSet();
        addressBookToExport = FXCollections.observableSet();
        
        String tempDir = System.getProperty("java.io.tmpdir");
        exportSingleContact = new ImportExportService(tempDir + "test1.vcf", addressBookToExport);
        exportAddressBook = new ImportExportService(tempDir + "test2.vcf", addressBookToExport);

        String path1 = ImportExportServiceTest.class.getResource("/assets/testFiles/filevcard.vcf").getPath().replaceAll("%20", "\\ ");
        String path2 = ImportExportServiceTest.class.getResource("/assets/testFiles/rubrica.vcf").getPath().replaceAll("%20", "\\ ");
        importSingleContact = new ImportExportService(path1, importedAddressBook);
        importAddressBook = new ImportExportService(path2, importedAddressBook);
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
     * @brief Test importing multiple contacts from a vcf file
     * 
     * This test checks that the importFromFile() method correctly reads from 
     * the file and returns an ObservaleSet. 
     * 
     * @throws Exception
     */
    @Test
    public void testImportFromFile() throws Exception {
        System.out.println("T064");
        importedAddressBook = importAddressBook.importFromFile();
        
        assertNotNull(importedAddressBook, "Il set non dovrebbe essere null");
    }

    /**
     * @brief Test exporting multiple contacts to a vcf file
     * 
     * This test checks that the exportToFile() method correctly serializes in
     * a file the contents of the Set.
     * 
     * @throws Exception
     */
    @Test
    public void testExportToFile() throws Exception {
        System.out.println("T065");
        Contact c1 = new Contact("gerardo", "selce");
        Contact c2 = new Contact("valerio", "volzone");
        
        addressBookToExport.add(c1);
        addressBookToExport.add(c2);
        
        exportAddressBook.exportToFile();
        
        File file = new File(exportAddressBook.getFileName());
        assertTrue(file.length()>0, "Il file non dovrebbe essere vuoto");
    }

    /**
     * @brief Test importing a single contact from a vcf file
     * 
     * This test checks that the importSingleContact() method correctly reads 
     * the contact from the file and adds it to the ObservableSet.
     * 
     * @throws Exception
     */
    @Test
    public void testImportSingleContact() throws Exception {
        System.out.println("T066");
        importedContact = importSingleContact.importSingleContact();
        
        assertNotNull(importedContact, "L'oggetto Contact non dovrebbe essere null");
    }

    /**
     * @brief Test exporting a single contact to a vcf file
     * 
     * This test checks that the exportSingleContact() method correctly writes
     * in a file a contact's informations.
     * 
     * @throws Exception
     */
    @Test
    public void testExportSingleContact() throws Exception {
        System.out.println("T067");
        Contact c = new Contact("sharon", "schiavano");
        
        exportSingleContact.exportSingleContact(c);
        
        File file = new File(exportSingleContact.getFileName());
        assertTrue(file.length()>0, "Il file non dovrebbe essere vuoto");
    }
    
}
