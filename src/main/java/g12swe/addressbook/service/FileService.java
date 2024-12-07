package g12swe.addressbook.service;

import g12swe.addressbook.models.AddressBook;

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

    public FileService(String fileName){
        super(fileName);
    }
   
    
    @Override
    public void importFromFile() {
        
    }

    @Override
    public AddressBook exportToFile() {
        
    }
    
}
