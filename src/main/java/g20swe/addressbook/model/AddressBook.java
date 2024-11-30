package g20swe.addressbook.model;

import g20swe.addressbook.model.contacts.Contact;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class AddressBook {
    
    private final Set<Contact> contactList;
    
    public AddressBook() {
        this.contactList = new TreeSet<>();
    }
    
    public void addContact(Contact c) {
        this.contactList.add(c);
    }
    
    public boolean removeContact(Contact c) {
        return this.contactList.remove(c);
    }
    
    public Set<Contact> getContactList() {
        return Collections.unmodifiableSet(contactList);
    }
    
}
