package g12swe.addressbook.models.contacts;

import g12swe.addressbook.exceptions.InvalidEmailAddressException;
import g12swe.addressbook.exceptions.InvalidPhoneNumberException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @file Contact.java
 * @brief This class contains the implementations of the contact entity.
 * 
 * This class implements the Comparable interface.
 * More detailed information about the file and its role is in the project.
 * 
 */
public class Contact implements Comparable<Contact>, Serializable {
    
    /**
     * Each contact can be associated to 0 to 3 emails. This is managed through
     * a 3-element EmailAddress list.
     */
    private final List<EmailAddress> emailAddresses;
    
    /**
     * Each contact can be associated with 0 to 3 phone numbers. This is managed
     * through a 3-element PhoneNumber list.
     */
    private final List<PhoneNumber> phoneNumbers;
    
    private String name;            ///< The contact's name.
    private String surname;         ///< The contact's surname.
    
    /**
     * Each contact can be identified by a unique id.
     */
    private final int uniqueId;     
    
    /**
     * The static attribute that increments the unique id.
     */
    private static int uniqueContacts = 0;  
    
    
    /**
     * @brief Constructor of the class.
     * 
     * This constructor creates a Contact object and initialize the attributes
     * with the values of the input parameters.
     * It also initializes the class collections and the uniqueId.
     * 
     * @pre name or surname must not be null
     * 
     * @param[in] name represents the name
     * @param[in] surname represents the surname
     */
    public Contact(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.emailAddresses = new ArrayList<>();
        this.phoneNumbers = new ArrayList<>();        
        this.uniqueId = ++uniqueContacts;
    }

    /**
     * @return the name 
     */
    public String getName() {
        return name;
    }

    /**
     * @return the surname 
     */
    public String getSurname() {
        return surname;
    }
    
    /**
     * @return the list of phone numbers 
     */
    public List<PhoneNumber> getPhoneNumbers(){
        return this.phoneNumbers;
    }
    
    /**
     * @return the list of email addresses 
     */
    public List<EmailAddress> getEmailAddresses(){
        return this.emailAddresses;
    }
    
    /**
     * @param[in] name the name to set 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * @param[in] surname the surname to set 
     */
    public void setSurname(String surname){
        this.surname = surname;
    }

    /**
     * @return the uniqueId 
     */
    public int getUniqueId() {
        return uniqueId;
    }
    
    /**
     * @brief Method to add an email to the contact.
     * 
     * @post The EmailAddress object will be added to the list.
     * 
     * @param[in] emailAddress the email to set if it is valid
     * @param[in] category the category to set
     * @throws InvalidEmailAddressException if the emailAddress is not valid 
     */
    public void addEmailAddress(String emailAddress, EntryCategory category) throws InvalidEmailAddressException{
        EmailAddress ea = new EmailAddress(emailAddress, category);
        if(ea.isValid())
            this.emailAddresses.add(ea);
        else
            throw new InvalidEmailAddressException("Il formato dell'indirizzo email inserito non è valido! Riprova.");
    }
    
    /**
     * @brief Method to add a phone number to the contact.
     * 
     * @post The PhoneNumber object will be added to the list.
     * 
     * @param[in] phoneNumber the phone number to set if it is valid
     * @param[in] category the category to set
     * @throws InvalidPhoneNumberException if the phoneNumber is not valid
     */
    public void addPhoneNumber(String phoneNumber, EntryCategory category) throws InvalidPhoneNumberException {
        PhoneNumber pn = new PhoneNumber(phoneNumber, category);
        if (pn.isValid())
            this.phoneNumbers.add(pn);
        else
            throw new InvalidPhoneNumberException("Il formato del numero di telefono inserito non è valido! Riprova.");
    }
    
    /**
     * @brief Method to remove an email address from the contact.
     * 
     * @pre The emailAddress parameter must be valid.
     * @post The EmailAddress object will be removed from the list.
     * 
     * @param[in] emailAddress the email to remove
     */
    public void removeEmailAddress(String emailAddress){
        EmailAddress ea = new EmailAddress(emailAddress, null);
        this.emailAddresses.remove(ea); 
    }

    /**
     * @brief Method to remove a phone number from the contact.
     * 
     * @pre The phoneNumber parameter must be valid.
     * @post The PhoneNumber object will be removed from the list
     * 
     * @param[in] phoneNumber the phone number to remove 
     */
    public void removePhoneNumber(String phoneNumber){
        PhoneNumber pn = new PhoneNumber(phoneNumber, null);
        this.phoneNumbers.remove(pn);
    }
    
    /**
     * @brief Override the hashCode method.
     * 
     * @return the hash code of the contact based on its uniqueID
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.uniqueId;
        return hash;
    }

    /**
     * @brief Override the equals method.
     * 
     * Two contacts are equal if they have the same uniqueID
     * 
     * @param[in] obj
     * @return False if the parameter is null or does not belong to the 
     * Contact class. True if the calling object is equal to the parameter
     * or if they belong to the same class and their uniqueId attributes are 
     * equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
            
        if (obj == null)
            return false;
        
        if (!(obj instanceof Contact))
            return false;

        return this.uniqueId == ((Contact)obj).uniqueId;
    }

    /**
    * @brief Method to compare the calling object to another Contact object.
    * 
    * Compares two Contact objects based on their surname, name, and uniqueId.
    * The comparison follows this order:
    * 1. First compares surnames
    * 2. If surnames are equal, compares names
    * 3. If both surname and name are equal, compares uniqueId
    * 
    * @param[in] o The Contact object to compare with
    * @return A negative integer if this contact comes before the parameter,
    *         zero if they are equal,
    *         a positive integer if this contact comes after the parameter
    */
    @Override
    public int compareTo(Contact o) {
        this.surname = this.surname == null ? "" : this.surname;

        int surnameCompare = this.surname.compareToIgnoreCase(o.surname);  
        if (surnameCompare != 0) {
            return surnameCompare;
        }

        this.name = this.name == null ? "" : this.name;

        int nameCompare = this.name.compareToIgnoreCase(o.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        
        return Integer.compare(this.uniqueId, o.uniqueId);
    }

    /**
     * @brief Generates a string representation of the Contact object
     * 
     * Overrides the default toString method to provide a custom string format
     * that includes te contacts's uniqueID, name and surname
     * 
     * @return A string containing the contact's uniqueID, name and surname
     */
    @Override
    public String toString() {
        return uniqueId + " " + name + " " + surname;
    }

    /**
     * @brief Sets a fixed value for the total number of unique contacts
     * 
     * This static method allows manually setting the counter for unique contacts,
     * which is typically used for managing contact identification.
     * 
     * @param[in] lastUniqueId The fixed value to set for the total number of
     *                         unique contacts
     */
    public static void setLastUniqueId(int lastUniqueId) {
        uniqueContacts = lastUniqueId;
        System.out.println("Last unique id: " + lastUniqueId);
    }

}
