package g12swe.addressbook.service;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Email;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import g12swe.addressbook.exceptions.InvalidEmailAddressException;
import g12swe.addressbook.exceptions.InvalidPhoneNumberException;
import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.EntryCategory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    public Contact importSingleContact() throws InvalidEmailAddressException, InvalidPhoneNumberException{
        VCard vcard = Ezvcard.parse(super.getFileName()).first();
        
        String name = vcard.getStructuredName().getGiven();
        String surname = vcard.getStructuredName().getFamily();
        String email1 = vcard.getEmails().get(0).getValue();
        String email2 = vcard.getEmails().get(1).getValue();
        String email3 = vcard.getEmails().get(2).getValue();
        String phoneNumber1 = vcard.getTelephoneNumbers().get(0).getText();
        String phoneNumber2 = vcard.getTelephoneNumbers().get(1).getText();
        String phoneNumber3 = vcard.getTelephoneNumbers().get(2).getText();
        
        Contact c = new Contact(name, surname);
        
        c.addEmailAddress(email1, null);
        c.addEmailAddress(email2, null);
        c.addEmailAddress(email3, null);
        c.addPhoneNumber(phoneNumber1, null);
        c.addPhoneNumber(phoneNumber2, null);
        c.addPhoneNumber(phoneNumber3, null);
        
        return c;
    }
    
    public void exportSingleContact(Contact c) throws FileNotFoundException, IOException{
        VCard vcard = new VCard();
        
        StructuredName name = new StructuredName();
        name.setGiven(c.getName());
        name.setFamily(c.getSurname());
        
        Email email1 = new Email(c.getEmailAddresses().get(0).getEmailAddress());
        Email email2 = new Email(c.getEmailAddresses().get(1).getEmailAddress());
        Email email3 = new Email(c.getEmailAddresses().get(2).getEmailAddress());
        
        Telephone phoneNumber1 = new Telephone(c.getPhoneNumbers().get(0).getPhoneNumber());
        Telephone phoneNumber2 = new Telephone(c.getPhoneNumbers().get(1).getPhoneNumber());
        Telephone phoneNumber3 = new Telephone(c.getPhoneNumbers().get(2).getPhoneNumber());
        
        vcard.setStructuredName(name);
        vcard.addEmail(email1);
        vcard.addEmail(email2);
        vcard.addEmail(email3);
        vcard.addTelephoneNumber(phoneNumber1);
        vcard.addTelephoneNumber(phoneNumber2);
        vcard.addTelephoneNumber(phoneNumber3);
        
        OutputStream os = new FileOutputStream(super.getFileName());
        Ezvcard.write(vcard).go(os);
    }
    
}
