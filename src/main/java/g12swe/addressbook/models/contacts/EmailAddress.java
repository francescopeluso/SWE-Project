package g12swe.addressbook.models.contacts;

import g12swe.addressbook.models.contacts.validation.Validatable;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * @file EmailAddress.java
 * @brief This class implements the email entity with its category.
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class EmailAddress implements Validatable, Serializable {
    
    private String emailAddress; ///< The email address.
    private EntryCategory category;    ///< The category of the email address.
    
    /**
     * @brief Default constructor of the class.
     * 
     * This constructor creates an EmailAddress object and initializes attributes
     * to null.
     * 
     */
    public EmailAddress(){
        this.emailAddress = null;
        this.category = null;
    }
    
    /**
     * @brief Constructor of the class.
     * 
     * This constructor creates an EmailAddress object and initializes attributes
     * with the values of the input parameters
     * 
     * @param[in] emailAddress represents the email address
     * @param[in] category represents the category of the email 
     */
    public EmailAddress(String emailAddress, EntryCategory category){
        this.emailAddress = emailAddress;
        this.category = category;
    }
    
    /**
     * @return the category
     */
    public EntryCategory getCategory(){
        return this.category;
    }
    
    /**
     * @return the email address 
     */
    public String getEmailAddress(){
        return this.emailAddress;
    }
    
    /**
     * @param[in] category the category to set
     */
    public void setCategory(EntryCategory category){
        this.category = category;
    }
    
    /**
     * @param[in] emailAddress the email address to set 
     */
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    /**
     * @brief Verify the correctness of an email address.
     * 
     * 
     * @return true if the emailAddress attribute is valid, false otherwise.
     */
    @Override
    public boolean isValid() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return Pattern.matches(emailRegex, this.emailAddress);
    }
    
    /**
     * @brief Override the equals method.
     * 
     * @param[in] obj
     * @return False if the parameter is null or does not belong to the 
     * EmailAddress class. True if the calling object is equal to the parameter
     * or if they belong to the same class and their emailAddress attributes are 
     * equal.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        
        if(this == obj)
            return true;
        
        if(obj.getClass() != EmailAddress.class)
            return false;
        
        EmailAddress ea = (EmailAddress)obj;
        return this.emailAddress.equals(ea.getEmailAddress());
    }
    
    /**
     * @brief Override the hashCode method.
     * 
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.emailAddress);
        return hash;
    }
}
