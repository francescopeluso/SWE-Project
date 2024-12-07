package g12swe.addressbook.service;

import g12swe.addressbook.models.AddressBook;

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
    
    public AddressBookService(String fileName){
        this.fileName = fileName;
    }
    
    public String getFileName(){
        return this.fileName;
    }
    
    public abstract void importFromFile();
    public abstract AddressBook exportToFile();
    
}
