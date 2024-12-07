package g12swe.addressbook.service;
import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import java.io.*;
import java.util.*;

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
    private final AddressBook ab;
    
    public FileService(AddressBook ab){
        this.ab = ab;
    }
    
    public void saveToFile(String filename){
           try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(ab);
        } catch (IOException e) {
            return;
        }
    }
    
    public static AddressBook readFromFile(String filename) throws ClassNotFoundException{
           try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(filename))) {
            return (AddressBook)oos.readObject();
        } catch (IOException e) {
            return null;
        }
    }
}
