package g20swe.addressbook.model.contacts;

import g20swe.addressbook.validation.Validatable;
import java.util.regex.Pattern;

public class PhoneNumber implements Validatable{
    
    private String phoneNumber;
    
    public PhoneNumber(){
        this.phoneNumber = null;
    }
    
    public PhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean validate() {
        String numberRegex = "^(\\+?\\d{1,4}[\\s\\-]?)?(\\(?\\d{2,4}\\)?[\\s\\-]?)?[\\d\\s\\-]{6,10}$";
        
        return Pattern.matches(numberRegex, this.phoneNumber);
    }
    
    
}
