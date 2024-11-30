package g20swe.addressbook.model.contacts;

import g20swe.addressbook.validation.Validatable;
import java.util.regex.Pattern;


/**
 * @file EmailAddress.java
 * @brief This class implements the email entity with its category.
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class EmailAddress implements Validatable{
    
    private String emailAddress; ///< The email address
    private EntryCategory category;    ///< The category of the email address
    
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
     * with the value of the input parameters
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
     * @param category the category to set
     */
    public void setCategory(EntryCategory category){
        this.category = category;
    }
    
    /**
     * @param emailAddress the email address to set 
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
    public boolean validate() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return Pattern.matches(emailRegex, this.emailAddress);
    }
    
     
}
