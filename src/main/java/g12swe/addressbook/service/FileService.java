package g12swe.addressbook.service;

import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableList;

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
public class FileService extends AddressBookService{

    public FileService(String fileName, ObservableList<Contact> contacts){
        super(fileName, contacts);
    }
    
    
    @Override
    public void importFromFile() {
        try(ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(super.getFileName())))){
            Set<Contact> contacts = new HashSet<>(super.getContacts());
            ous.writeObject(contacts);
        } catch (IOException ex) {
            return;
        }
    }

    @Override
    public AddressBook exportToFile() {
        return null;
    }

    
}
