package g12swe.addressbook.service;
import g12swe.addressbook.models.contacts.Contact;
import java.io.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * @file FileService.java
 * @brief Service for managing file operations in the address book application.
 *
 * This class handles low-level file operations, such as reading from and 
 * writing to storage. It ensures data consistency and supports the persistence 
 * of address book data.
 *
 * Key functionalities include:
 * - Saving the current state of the address book to a file.
 * - Loading address book data from existing files at startup.
 *
 * This service operates independently but may be used by other services 
 * like <code>ImportExportService</code>.
 */
public class FileService extends AddressBookService {
    
    /**
     * @brief Constructor for the FileService.
     *
     * @param fileName The name of the file used for saving and loading data.
     * @param contacts The observable set of contacts to manage.
     */
    public FileService(String fileName, ObservableSet<Contact> contacts){
        super(fileName, contacts);
    }
    
     /**
     * @brief Imports contacts from a file.
     *
     * This method reads contact data from the file specified in
     * the constructor and converts it into an observable set.
     *
     * @return An observable set of contacts loaded from the file.
     *         Returns null if the file does not contain valid data.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException If an I/O error occurs during reading.
     */

    @SuppressWarnings("unchecked")
    @Override
    public ObservableSet<Contact> importFromFile() throws FileNotFoundException, IOException{
        Set<Contact> tempSet;
        
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(super.getFileName())))){
            tempSet = (Set<Contact>) ois.readObject();
        } catch (ClassNotFoundException ex) {
            return null;
        }
        
        return FXCollections.observableSet(tempSet);
    }
    
    /**
     * @brief Exports contacts to a file.
     *
     * This method exports the current set of contacts
     * to the file specified in the constructor.
     *
     * @throws FileNotFoundException If the file cannot be created or opened.
     * @throws IOException If an I/O error occurs during writing.
     */

    @Override
    public void exportToFile() throws FileNotFoundException, IOException{
        Set<Contact> tempContacts = new TreeSet<Contact>(super.getContacts());
        
        System.out.println(super.getFileName());
        System.out.println(super.getContacts());
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(super.getFileName())))){
            oos.writeObject(tempContacts);
        } 
    }
}
