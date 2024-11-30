package g20swe.addressbook.model.contacts;

import g20swe.addressbook.exceptions.InvalidEmailAddressException;
import g20swe.addressbook.exceptions.InvalidPhoneNumberException;
import java.util.Arrays;
import java.util.List;

public class Contact implements Comparable<Contact> {
    
    private final List<EmailAddress> emailAddresses; //Arrays.asList(new EmailAddress[3]);
    private final List<PhoneNumber> phoneNumbers;
    private String name;
    private String surname;
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
