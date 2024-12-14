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


public class ImportExportServiceTest {
    
    private static ImportExportService exportSingleContact;
    private static ImportExportService exportAddressBook;
    private static ImportExportService importSingleContact;
    private static ImportExportService importAddressBook;
    
    private static ObservableSet<Contact> importedAddressBook;
    private static Contact importedContact;
    private static ObservableSet<Contact> addressBookToExport;
    private static Contact contactToExport;
    
    
    public ImportExportServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        importedAddressBook = FXCollections.observableSet();
        addressBookToExport = FXCollections.observableSet();
        
        exportSingleContact = new ImportExportService("C:\\Users\\ACER\\Desktop\\testvcard\\test1.vcf", addressBookToExport);
        exportAddressBook = new ImportExportService("C:\\Users\\ACER\\Desktop\\testvcard\\test2.vcf", addressBookToExport);
        importSingleContact = new ImportExportService("C:\\Users\\ACER\\Desktop\\testvcard\\filevcard.vcf", importedAddressBook);
        importAddressBook = new ImportExportService("C:\\Users\\ACER\\Desktop\\testvcard\\rubrica.vcf", importedAddressBook);
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
     * @brief Test of importFromFile method, of class ImportExportService.
     * 
     * This test checks that the importFromFile() method correctly reads from 
     * the file and returns an ObservaleSet. 
     */
    @Test
    public void testImportFromFile() throws Exception {
        System.out.println("T064");
        importedAddressBook = importAddressBook.importFromFile();
        
        assertNotNull(importedAddressBook, "Il set non dovrebbe essere null");
    }

    /**
     * @brief Test of exportToFile method, of class ImportExportService.
     * 
     * This test checks that the exportToFile() method correctly serializes in
     * a file the contents of the Set.
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
     * @brief Test of importSingleContact method, of class ImportExportService.
     * 
     * This test checks that the importSingleContact() method correctly reads 
     * the contact from the file and adds it to the ObservableSet.
     */
    @Test
    public void testImportSingleContact() throws Exception {
        System.out.println("T066");
        importedContact = importSingleContact.importSingleContact();
        
        assertNotNull(importedContact, "L'oggetto Contact non dovrebbe essere null");
    }

    /**
     * @brief Test of exportSingleContact method, of class ImportExportService.
     * 
     * This test checks that the exportSingleContact() method correctly writes
     * in a file a contact's informations.
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
