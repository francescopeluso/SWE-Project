package g20swe.addressbook.model.contacts;

import java.util.Arrays;
import java.util.List;

public class Contact {
    
    private final List<EmailAddress> emailAddresses; //Arrays.asList(new EmailAddress[3]);
    private final List<PhoneNumber> phoneNumbers;
    private final String name;
    private final String surname;
    
    public Contact(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.emailAddresses = Arrays.asList(new EmailAddress[3]);
        this.phoneNumbers = Arrays.asList(new PhoneNumber[3]);
    }
    
    
}
