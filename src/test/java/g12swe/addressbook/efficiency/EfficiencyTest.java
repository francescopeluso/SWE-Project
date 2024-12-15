package g12swe.addressbook.efficiency;

import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.service.FileService;
import java.io.IOException;
import java.util.Random;
import javafx.collections.ObservableSet;

/**
 * @file EfficiencyTest.java
 * @brief Utility class for generating and testing contact file efficiency
 * 
 * This class provides methods to generate files with a specified number of 
 * randomly created contacts and import them back for performance testing.
 * 
 * The class uses predefined arrays of the first and last names to create
 * randomized Contact object.
 * 
 */
public class EfficiencyTest {
    
    /**
     * @brief Array of predefined first names for random contact generation
     */
    private static final String[] NOMI = {
        "Marco", "Luca", "Giovanni", "Alessandro", "Andrea", 
        "Carlo", "Francesco", "Paolo", "Roberto", "Matteo",
        "Anna", "Laura", "Chiara", "Sofia", "Elena", 
        "Maria", "Giulia", "Valentina", "Francesca", "Sara"
    };
    
    /**
     * @brief Array of predefined last names for random contact generation
     */
    private static final String[] COGNOMI = {
        "Rossi", "Bianchi", "Esposito", "Ferrari", "Colombo", 
        "Romano", "Ricci", "Greco", "Marino", "Conti",
        "Bruno", "Villa", "Russo", "Costa", "Lombardi"
    };
    
    /**
     * @brief FileService instance for handling file operations
     */
    private final FileService fileService;
    
    
    /**
     * @brief Constructor for EfficiencyTest
     * 
     * Initializes the FileService with a specific file name and contact set
     * 
     * @param[in] fileName Path of the file to be used for contact generation/import 
     * @param[in] contacts ObservableSet to store generated contacts
     */
    public EfficiencyTest(String fileName, ObservableSet<Contact> contacts){
        this.fileService = new FileService(fileName, contacts);
    }
    
    /**
     * @brief Generates a file with a specified number of random contacts
     * 
     * Creates a set number of Contact objects using random combinations of first
     * and last names from predefined arrays. Each contact is added to the contact
     * set and then exported to a file.
     * 
     * @param[in] num Number of random contacts to generate
     * @throws IOException 
     */
    public void generateFile(int num) throws IOException{
        Random random = new Random();
        Contact c = null;
        
        for(int i=0; i<num; i++){
            
            c = new Contact(NOMI[random.nextInt(NOMI.length)],
                    COGNOMI[random.nextInt(COGNOMI.length)]);
            this.fileService.getContacts().add(c);
            
        }
        
        this.fileService.exportToFile();
    }
    
    /**
     * @brief Imports contacts from the previously generated file
     * 
     * Reads contacts from the file using the associated FileService
     * 
     * @return ObservableSet containing the imported contacts
     * @throws IOException 
     */
    public ObservableSet<Contact> importFromFile() throws IOException{
        return this.fileService.importFromFile();
    }
}
