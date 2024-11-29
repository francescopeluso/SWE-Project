package g20swe.addressbook.model.contacts;

import g20swe.addressbook.validation.Validatable;
import java.util.regex.Pattern;


public class EmailAddress implements Validatable{
    
    private String emailAddress;
    
    public EmailAddress(){
        this.emailAddress = null;
    }
    
    public EmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    public String getEmailAddress(){
        return this.emailAddress;
    }
    
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean validate() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return Pattern.matches(emailRegex, this.emailAddress);
    }
    
     
}
