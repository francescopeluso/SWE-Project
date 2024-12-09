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
    
    public FileService(String fileName, ObservableSet<Contact> contacts){
        super(fileName, contacts);
    }
    

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
