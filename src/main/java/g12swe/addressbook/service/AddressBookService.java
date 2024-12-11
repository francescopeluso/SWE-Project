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
    
    private final String fileName;
    private ObservableSet<Contact> contacts;
    
    public AddressBookService(String fileName, ObservableSet<Contact> contacts){
        this.fileName = fileName;
        this.contacts = contacts;
    }
    
    public String getFileName(){
        return this.fileName;
    }
    
    public ObservableSet<Contact> getContacts(){
        return this.contacts;
    }
    
    public abstract ObservableSet<Contact> importFromFile() throws FileNotFoundException, IOException;
    public abstract void exportToFile() throws FileNotFoundException, IOException;
}
