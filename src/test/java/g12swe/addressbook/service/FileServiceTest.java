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
 * @file FileServiceTest.java
 * @brief Unit tests for the FileService class
 * 
 * This test class verifies the functionality of FileService methods, including
 * file import and export operations for Contact objects.
 * 
 */
public class FileServiceTest {
    
    /**
     * @brief ObservableSet of contacts for the first test file
     */
    private static ObservableSet<Contact> contacts1; 
    
    /**
     * @brief ObservableSet of contacts for the second test file
     */
    private static ObservableSet<Contact> contacts2;
    
    /**
     * @brief FileService object for the first test scenario
     */
    private static FileService fileService1;
    
    /**
     * @brief FileService object for the second test scenario
     */
    private static FileService fileService2;
    
    public FileServiceTest() {
    }
    
    /**
     * @brief Set up resources before running the test class
     * 
     * This method initializes test file paths and FileService instances for
     * subsequent test methods.
     * 
     * @throws java.net.URISyntaxException 
     */
    @BeforeAll
    public static void setUpClass() throws java.net.URISyntaxException {
        contacts1 = FXCollections.observableSet();
        contacts2 = FXCollections.observableSet();
        String path1 = new File(FileServiceTest.class.getResource("/assets/testFiles/test1.bin").toURI()).getPath();
        String path2 = new File(FileServiceTest.class.getResource("/assets/testFiles/test1.bin").toURI()).getPath();
        fileService1 = new FileService(path1, contacts1);
        fileService2 = new FileService(path2, contacts2);
    }
    
    @AfterAll
    public static void tearDownClass() {
        File file2 = new File("C:\\Users\\ACER\\Desktop\\test\\test2.bin");
        if(file2.exists())
            assertTrue(file2.delete(), "il file2 è stato eliminato");
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * @brief Test of importFromFile method, of class FileService.
     * 
     * This test checks that the importFromFile() method correctly reads the 
     * file and returns an ObservableSet.
     */
    @Test
    public void testImportFromFile() throws Exception {
        System.out.println("T062");
        contacts1 = fileService1.importFromFile();
        assertNotNull(contacts1, "il set non dovrebbe essere null");
    }

    /**
     * @brief Test of exportToFile method, of class FileService.
     * 
     * This test checks that the method exportToFile() correctly writes in a 
     * file the content of the Set.
     */
    @Test
    public void testExportToFile() throws Exception {
        System.out.println("T063");
        Contact c1 = new Contact("gerardo", "selce");
        Contact c2 = new Contact("sharon", "schiavano");
        
        contacts2.add(c1);
        contacts2.add(c2);
        
        fileService2.exportToFile();
        
        File file = new File(fileService2.getFileName());
        assertTrue(file.length()>0, "il file non deve essere vuoto");
    }
    
}
