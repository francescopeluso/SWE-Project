package g12swe.addressbook.service;

import g12swe.addressbook.models.contacts.Contact;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.ObservableSet;

/**
 * @file AddressBookService.java
 * @brief Abstract base class for services related to the address book.
 *
 * This class defines the common behaviors and contracts for all services 
 * interacting with the <code>AddressBook</code>. Subclasses should implement 
 * specific functionalities, such as file operations or import/export tasks.
 *
 * This class is designed to be extended and not instantiated directly.
 */
public abstract class AddressBookService {
    
    private final String fileName; ///< The name of the file to read from or write to
    private ObservableSet<Contact> contacts; ///< The collection of contacts to manage
    
     /**
     * @brief Constructor for the AddressBookService class
     * 
     * @param fileName The name of the file to use for import/export operations
     * @param contacts The collection of contacts to manage
     */
    
    public AddressBookService(String fileName, ObservableSet<Contact> contacts){
        this.fileName = fileName;
        this.contacts = contacts;
    }
    
    /**
     * @brief Gets the file name used for import/export operations
     * @return The name of the file
     */
    
    public String getFileName(){
        return this.fileName;
    }
    
    /**
     * @brief Retrieves the collection of contacts.
     * @return The ObservableSet containing all contacts.
     */
    
    public ObservableSet<Contact> getContacts(){
        return this.contacts;
    }
    
    
    /**
     * @brief Imports contacts from a file
     * 
     * @return An ObservableSet containing the imported contacts
     * @throws FileNotFoundException if the specified file cannot be found
     * @throws IOException if an I/O error occurs during reading
     */
    public abstract ObservableSet<Contact> importFromFile() throws FileNotFoundException, IOException;
    
    /**
     * @brief Exports contacts to a file
     * 
     * @throws FileNotFoundException if the specified file cannot be created or opened
     * @throws IOException if an I/O error occurs during writing
     */
    public abstract void exportToFile() throws FileNotFoundException, IOException;
}
