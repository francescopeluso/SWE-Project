package g12swe.addressbook.models;

import g12swe.addressbook.models.contacts.Contact;
import java.io.Serializable;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * @file AddressBook.java
 * @brief This class contains the implementation of the address book entity
 * through a collection of Contact objects
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class AddressBook implements Serializable {
    
    private ObservableSet<Contact> contactList;     ///< The collection used is a set
    
    /**
     * @brief Constructor of the class
     * 
     * This constructor creates an AddressBook object and initialize the set as
     * a TreeSet.
     * 
     */
    public AddressBook() {
        TreeSet<Contact> orderedContacts = new TreeSet<>();
        this.contactList = FXCollections.observableSet(orderedContacts);
    }
    
    /**
     * @brief Method to add a contact to the address book.
     * 
     * @pre c is not null
     * @post the contact object will be added to the address book
     * 
     * @param[in] c the contact to add 
     */
    public void addContact(Contact c) {
        this.contactList.add(c);
    }
    
    /**
     * @brief Method to remove a contact from the address book
     * 
     * @pre The contact exists in the address book
     * @post The contact is removed from the address book
     * 
     * @param[in] c the contact to remove
     * @return true if the element is removed from the collection. Otherwise it 
     * is false.
     */
    public boolean removeContact(Contact c) {
        return this.contactList.remove(c);
    }

    /**
     * @brief Method to update a contact in the address book.
     * 
     * @pre The contact exists in the address book
     * @post The contact is updated in the address book
     * 
     * In order to preserve the order of the contacts, the old contact is removed
     * and the new contact is added.
     * 
     * @param[in] oldContact the contact to update
     * @param[in] newContact the updated contact information
     */
    public void updateContact(Contact oldContact, Contact newContact) {
        if (this.contactList.remove(oldContact)) {
            this.contactList.add(newContact);
        }
    }
    
    /**
     * @brief Method to return an unmodifiable set of the address book.
     * 
     * @return the contact list 
     */
    public ObservableSet<Contact> getContactList() {
        return this.contactList;
    }
    
    public void initialize(ObservableSet<Contact> contactList){
        this.contactList = contactList;
    }
    
}
