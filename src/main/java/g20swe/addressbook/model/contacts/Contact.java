package g20swe.addressbook.model.contacts;

import g20swe.addressbook.exceptions.InvalidEmailAddressException;
import g20swe.addressbook.exceptions.InvalidPhoneNumberException;
import java.util.Arrays;
import java.util.List;


/**
 * @file Contact.java
 * @brief This class implements the contact entity.
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class Contact implements Comparable<Contact> {
    
    /**
     * Each contact can be associated with 0 to 3 emails. This is managed through
     * a 3-element EmailAddress list.
     */
    private final List<EmailAddress> emailAddresses;
    
    /**
     * Each contact can be associateed with 0 to 3 phone numbers. This is managed
     * through a 3-element PhoneNumber list.
     */
    private final List<PhoneNumber> phoneNumbers;
    
    private String name;            ///< The contact's name.
    private String surname;         ///< The contact's surname.
    
    /**
     * Each contact can be identified with a unique id.
     */
    private final int uniqueId;     
    
    /**
     * The static attribute that increments the unique id.
     */
    public static int uniqueContacts = 0;  
    
    
    /**
     * @brief Constructor of the class.
     * 
     * This constructor creates a Contact object and initialize the attributes
     * with the values of the input parameters.
     * It also initialize the class collections and the uniqueId.
     * 
     * @param[in] name represents the name
     * @param[in] surname represents the surname
     */
    public Contact(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.emailAddresses = Arrays.asList(new EmailAddress[3]);
        this.phoneNumbers = Arrays.asList(new PhoneNumber[3]);
        
        this.uniqueId = ++uniqueContacts;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
    public List<PhoneNumber> getPhoneNumbers(){
        return this.phoneNumbers;
    }
    
    public List<EmailAddress> getEmailAddresses(){
        return this.emailAddresses;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setSurname(String surname){
        this.surname = surname;
    }
    
    public void addEmailAddress(String emailAddress, EntryCategory category) throws InvalidEmailAddressException{
        EmailAddress ea = new EmailAddress(emailAddress, category);
        if(ea.isValid())
            this.emailAddresses.add(ea);
        else
            throw new InvalidEmailAddressException("Il formato dell'indirizzo email inserito non è valido! Riprova.");
    }
    
    public void addPhoneNumber(String phoneNumber, EntryCategory category) throws InvalidPhoneNumberException {
        PhoneNumber pn = new PhoneNumber(phoneNumber, category);
        if (pn.isValid())
            this.phoneNumbers.add(pn);
        else
            throw new InvalidPhoneNumberException("Il formato del numero di telefono inserito non è valido! Riprova.");
    }
    
    // Precondizioni: email presente nel contatto
    public void removeEmailAddress(String emailAddress){
        EmailAddress ea = new EmailAddress(emailAddress, null);
        this.emailAddresses.remove(ea); 
    }

    // Precondizioni: numero presente nel contatto
    public void removePhoneNumber(String phoneNumber){
        PhoneNumber pn = new PhoneNumber(phoneNumber, null);
        this.phoneNumbers.remove(pn);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.uniqueId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
            
        if (obj == null)
            return false;
        
        if (this.getClass() != obj.getClass())
            return false;

        return this.uniqueId == ((Contact)obj).uniqueId;
    }

    @Override
    public int compareTo(Contact o) {
        return this.surname.compareToIgnoreCase(o.surname);
    }
    
}
