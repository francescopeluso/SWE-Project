package g12swe.addressbook.models.contacts;

import java.time.LocalDateTime;
import g12swe.addressbook.models.contacts.Contact;

/**
 * @file ExtendedContact.java
 * @brief This class extends the Contact class and contains the implementations 
 * of additional information regarding the contact.
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class ExtendedContact extends Contact{
    
    private String address;             ///< The contact's address
    private LocalDateTime birthday;     ///< The contact's birthday
    private String notes;               ///< A description added by the user
    private String pronouns;            ///< The contact's pronouns
    
    /**
     * @brief Constructor of the class.
     * 
     * This constructor creates an ExtendedContact object and initializes the attributes
     * with the values of the input parameters.
     * It also initializes the class collections and the uniqueId.
     * 
     * @param[in] name represents the name.
     * @param[in] surname represents the surname.
     * @param[in] address represents the address.
     * @param[in] birthday represents the birthday.
     * @param[in] notes represents the description of the contact.
     * @param[in] pronouns represents the pronouns of the contact.
     */
    public ExtendedContact(String name, String surname, String address, LocalDateTime birthday, String notes, String pronouns){
        super(name, surname);
        
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
        this.pronouns = pronouns;
    }

    /**
     * @return the address 
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param[in] address the address to set 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the birthday 
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * @param[in] birthday the birthday to set 
     */
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the notes 
     */
    public String getNotes() {
        return notes;
    }
    
    /**
     * @param[in] notes the notes to set 
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the pronouns 
     */
    public String getPronouns() {
        return pronouns;
    }

    /**
     * @param[in] pronouns the pronouns to set 
     */
    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }
    
    
}
