package g12swe.addressbook.model.contacts;

import g12swe.addressbook.contacts.validation.Validatable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @file PhoneNumber.java 
 * @brief This class implements the phone number entity with its category.
 * 
 * More detailed information about the file and its role is in the project.
 * 
 */
public class PhoneNumber implements Validatable{
    
    private String phoneNumber;           ///< The phone number
    private EntryCategory category;       ///< The category of the email address
    
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
     * This constructor creates a PhoneNumeber object and initializes attributes
     * with the value of the input parameters
     * 
     * @param[in] phoneNumber represents the phone number
     * @param[in] category represents the category of the email 
     */
    public PhoneNumber(String phoneNumber, EntryCategory category){
        this.phoneNumber = phoneNumber;
        this.category = category;
    }
    
    /**
     * @return the category 
     */
    public EntryCategory getCategory(){
        return this.category;
    }
    
    /**
     * @return the phone number 
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    /**
     * @param[in] category the category to set
     */
    public void setCategory(EntryCategory category){
        this.category = category;
    }
    
    /**
     * @param[in] phoneNumber the phone number to set 
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
    
    /**
     * @brief Override the equals method.
     * 
     * @param[in] obj
     * @return False if the parameter is null or does not belong to the 
     * PhoneNumber class. True if the calling object is equal to the parameter
     * or if they belong to the same class and their phoneNumber attributes are 
     * equal.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        
        if(this == obj)
            return true;
        
        if(obj.getClass() != PhoneNumber.class)
            return false;
        
        PhoneNumber pn = (PhoneNumber)obj;
        return this.phoneNumber.equals(pn.getPhoneNumber());
    }

    /**
     * @brief Override the hashCode method.
     * 
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.phoneNumber);
        return hash;
    }
    
}
