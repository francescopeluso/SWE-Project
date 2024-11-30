package g20swe.addressbook.model.contacts;

import g20swe.addressbook.validation.Validatable;
import java.util.regex.Pattern;

/**
 * @file PhoneNumber.java 
 * @brief This class implements the phone number entity with its category.
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class PhoneNumber implements Validatable{
    
    private String phoneNumber;     ///< The phone number
    private EntryLabel category;       ///< The category of the email address
    
    /**
     * @brief Default constructor of the class.
     * 
     * This constructor creates a PhoneNumber object and initializes attributes
     * to null.
     * 
     */
    public PhoneNumber(){
        this.phoneNumber = null;
        this.category = null;
    }
    
    /**
     * @brief Constructor of the class.
     * 
     * This constructor creates a PhoneNumeber object and initializes just the
     * phone number attribute, while leaving category to being null.
     * 
     * @param[in] phoneNumber represents the phone number
     */
    public PhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        this.category = null;
    }
    
    /**
     * @brief Constructor of the class.
     * 
     * This constructor creates a PhoneNumeber object and initializes attributes
     * with the value of the input parameters
     * 
     * @param[in] phoneNumber represents the phone number
     * @param[in] category represents the category of the email 
     */
    public PhoneNumber(String phoneNumber, EntryLabel category){
        this.phoneNumber = phoneNumber;
        this.category = category;
    }
    
    /**
     * @return the category 
     */
    public EntryLabel getCategory(){
        return this.category;
    }
    
    /**
     * @return the phone number 
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    /**
     * @param category the category to set
     */
    public void setCategory(EntryLabel category){
        this.category = category;
    }
    
    /**
     * @param phoneNumber the phone number to set 
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * @brief Verify the correctness of a phone number.
     * 
     * 
     * @return true if the phone number attribute is valid, false otherwise.
     */
    @Override
    public boolean isValid() {
        String numberRegex = "^(\\+?\\d{1,4}[\\s\\-]?)?(\\(?\\d{2,4}\\)?[\\s\\-]?)?[\\d\\s\\-]{6,10}$";
        
        return Pattern.matches(numberRegex, this.phoneNumber);
    }
    
    
}
