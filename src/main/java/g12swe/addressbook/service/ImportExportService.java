package g12swe.addressbook.service;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Email;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import g12swe.addressbook.exceptions.InvalidEmailAddressException;
import g12swe.addressbook.exceptions.InvalidPhoneNumberException;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.EmailAddress;
import g12swe.addressbook.models.contacts.PhoneNumber;
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

    /**
     * @brief Constructor for the ImportExportService.
     *
     * @param fileName The name of the file used for importing and exporting data.
     * @param contacts The set of contacts to manage.
     */
    public ImportExportService(String fileName, ObservableSet<Contact> contacts){
        super(fileName, contacts);
    }

     /**
     * @brief Imports contacts from a vCard file.
     *
     * Reads contacts from the file specified in the constructor, parses the vCard format,
     * and returns them as an observable set.
     *
     * @return An observable set of contacts imported from the file.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws IOException If an I/O error occurs during reading.
     */
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
        catch(IOException | InvalidEmailAddressException | InvalidPhoneNumberException e){
            e.printStackTrace();
        }
        
        return FXCollections.observableSet(tempSet);
    }
    
    /**
     * @brief Exports all contacts to a vCard file.
     *
     * Serializes all contacts into the vCard format and writes them to the file specified
     * during construction.
     *
     * @throws FileNotFoundException If the file cannot be created or opened.
     * @throws IOException If an I/O error occurs during writing.
     */

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

    /**
     * @brief Imports a single contact from a vCard file.
     *
     * Reads the first contact in the vCard file specified during construction
     * and returns it as a Contact object.
     *
     * @return The imported Contact object, or null if an error occurs.
     * @throws InvalidEmailAddressException If an email address is invalid.
     * @throws InvalidPhoneNumberException If a phone number is invalid.
     */
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
    
     /**
     * @brief Exports a single contact to a vCard file.
     *
     * Serializes the provided contact into the vCard format and writes it to the
     * file specified during construction.
     *
     * @param c The contact to export.
     * @throws FileNotFoundException If the file cannot be created or opened.
     * @throws IOException If an I/O error occurs during writing.
     */
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
