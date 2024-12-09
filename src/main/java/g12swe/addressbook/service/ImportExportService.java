package g12swe.addressbook.service;

import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.ObservableSet;

/**
 * @file ImportExportService.java
 * @brief Service for importing and exporting contacts in the address book application.
 *
 * This class facilitates the transfer of contact data to and from external sources, 
 * supporting various formats as needed (mainly, the vCard format).
 *
 * Key functionalities include:
 * - Importing individual contacts or entire address books from external files.
 * - Exporting contact data to standard formats for sharing or backup purposes.
 *
 * This service builds on the file operations provided by <code>FileService</code> 
 * and integrates with the application's controllers for seamless user interaction.
 */
public class ImportExportService extends AddressBookService{

    public ImportExportService(String fileName, ObservableSet<Contact> contacts){
        super(fileName, contacts);
    }

    @Override
    public ObservableSet<Contact> importFromFile() throws FileNotFoundException, IOException {
        return null;
    }

    @Override
    public void exportToFile() throws FileNotFoundException, IOException {
        
    }

}
