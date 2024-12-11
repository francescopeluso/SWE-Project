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
import g12swe.addressbook.models.contacts.EmailAddress;
import g12swe.addressbook.models.contacts.EntryCategory;
import g12swe.addressbook.models.contacts.PhoneNumber;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
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
public class ImportExportService extends AddressBookService {

    public ImportExportService(String fileName, ObservableSet<Contact> contacts){
        super(fileName, contacts);
    }

    @Override
    public ObservableSet<Contact> importFromFile() throws FileNotFoundException, IOException {
        Set<Contact> tempSet = new TreeSet<>();
        List<VCard> vcards;
        
        try{
            String fileContent = new String(Files.readAllBytes(Paths.get(super.getFileName())));
            vcards = Ezvcard.parse(fileContent).all();
            
            for(VCard vcard : vcards){
                String name = vcard.getStructuredName().getGiven();
                String surname = vcard.getStructuredName().getFamily();

                List<Email> emails = vcard.getEmails();
                List<Telephone> phoneNumbers = vcard.getTelephoneNumbers();

                Contact c = new Contact(name, surname);

                for (Email e : emails) {
                    c.addEmailAddress(e.getValue(), null);
                }

                for (Telephone t : phoneNumbers) {
                    c.addPhoneNumber(t.getText(), null);
                }
                
                tempSet.add(c);
            }
            
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (InvalidEmailAddressException ex) {
            ex.printStackTrace();
        } catch (InvalidPhoneNumberException ex) {
            ex.printStackTrace();
        }
        
        return FXCollections.observableSet(tempSet);
    }

    @Override
    public void exportToFile() throws FileNotFoundException, IOException {
        List<VCard> vcards = new ArrayList<>();
        Set<Contact> tempSet = new TreeSet<>(super.getContacts());
        
        for(Contact c : tempSet){
            VCard vcard = new VCard();

            StructuredName name = new StructuredName();
            name.setGiven(c.getName());
            name.setFamily(c.getSurname());

            List<EmailAddress> emails = c.getEmailAddresses();
            List<PhoneNumber> numbers = c.getPhoneNumbers();

            for (EmailAddress ea : emails) {
                vcard.addEmail(new Email(ea.getEmailAddress()));
            }

            for (PhoneNumber pn : numbers) {
                vcard.addTelephoneNumber(new Telephone(pn.getPhoneNumber()));
            }

            vcard.setStructuredName(name);
            
            vcards.add(vcard);
        }
        
        OutputStream os = new FileOutputStream(super.getFileName());
        Ezvcard.write(vcards).go(os);
    }

    public Contact importSingleContact() throws InvalidEmailAddressException, InvalidPhoneNumberException {
        VCard vcard;
        
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(super.getFileName())));
            vcard = Ezvcard.parse(fileContent).first();
            
            
            String name = vcard.getStructuredName().getGiven();
            String surname = vcard.getStructuredName().getFamily();
            
            List<Email> emails = vcard.getEmails();
            List<Telephone> phoneNumbers = vcard.getTelephoneNumbers();
            
            Contact c = new Contact(name, surname);
            
            for(Email e : emails){
                c.addEmailAddress(e.getValue(), null);
            }
            
            for(Telephone t : phoneNumbers){
                c.addPhoneNumber(t.getText(), null);
            }
            
            return c;
            
        } catch (IOException e) {
            e.printStackTrace();
        }        
        
        
        
        return null;
    }
    
    public void exportSingleContact(Contact c) throws FileNotFoundException, IOException{
        VCard vcard = new VCard();
        
        StructuredName name = new StructuredName();
        name.setGiven(c.getName());
        name.setFamily(c.getSurname());
        
        List<EmailAddress> emails = c.getEmailAddresses();
        List<PhoneNumber> numbers = c.getPhoneNumbers();
        
        for(EmailAddress ea : emails){
            vcard.addEmail(new Email(ea.getEmailAddress()));
        }
        
        for(PhoneNumber pn : numbers){
            vcard.addTelephoneNumber(new Telephone(pn.getPhoneNumber()));
        }
        
        vcard.setStructuredName(name);
        
        OutputStream os = new FileOutputStream(super.getFileName());
        Ezvcard.write(vcard).go(os);
    }
    
}
