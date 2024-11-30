package g20swe.addressbook.model.contacts;

import g20swe.addressbook.exceptions.InvalidPhoneNumberException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Contact implements Comparable<Contact> {
    
    private final List<EmailAddress> emailAddresses; //Arrays.asList(new EmailAddress[3]);
    private final List<PhoneNumber> phoneNumbers;
    private final String name;
    private final String surname;
    private final int uniqueId;
    
    public static int uniqueContacts = 0;
    
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
    
    public void addPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        PhoneNumber pn = new PhoneNumber(phoneNumber);
        if (pn.isValid())
            this.phoneNumbers.add(pn);
        else
            throw new InvalidPhoneNumberException("Il formato del numero di telefono inserito non è valido! Riprova.");
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
