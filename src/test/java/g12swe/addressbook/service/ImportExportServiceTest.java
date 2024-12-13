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
     * Test of importFromFile method, of class ImportExportService.
     */
    @Test
    public void testImportFromFile() throws Exception {
        importedAddressBook = importAddressBook.importFromFile();
        
        assertNotNull(importedAddressBook, "Il set non dovrebbe essere null");
    }

    /**
     * Test of exportToFile method, of class ImportExportService.
     */
    @Test
    public void testExportToFile() throws Exception {
        Contact c1 = new Contact("gerardo", "selce");
        Contact c2 = new Contact("valerio", "volzone");
        
        addressBookToExport.add(c1);
        addressBookToExport.add(c2);
        
        exportAddressBook.exportToFile();
        
        File file = new File(exportAddressBook.getFileName());
        assertTrue(file.length()>0, "Il file non dovrebbe essere vuoto");
    }

    /**
     * Test of importSingleContact method, of class ImportExportService.
     */
    @Test
    public void testImportSingleContact() throws Exception {
        importedContact = importSingleContact.importSingleContact();
        
        assertNotNull(importedContact, "L'oggetto Contact non dovrebbe essere null");
    }

    /**
     * Test of exportSingleContact method, of class ImportExportService.
     */
    @Test
    public void testExportSingleContact() throws Exception {
        Contact c = new Contact("sharon", "schiavano");
        
        exportSingleContact.exportSingleContact(c);
        
        File file = new File(exportSingleContact.getFileName());
        assertTrue(file.length()>0, "Il file non dovrebbe essere vuoto");
    }
    
}
