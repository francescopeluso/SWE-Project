package g12swe.addressbook.contacts.validation;

/**
 * @file Validatable.java
 * @brief This interface implements the possibility to be validate.
 * 
 * The class that implements this interface will have to define the validate()
 * method which will contain a validation rule for one or more attributes.
 * 
 */
public interface Validatable {
    
    public boolean isValid();
}
