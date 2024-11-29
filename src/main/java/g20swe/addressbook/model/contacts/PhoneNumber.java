package g20swe.addressbook.model.contacts;

import g20swe.addressbook.validation.Validatable;
import java.util.regex.Pattern;

public class PhoneNumber implements Validatable{
    
    private String phoneNumber;
    private EntryLabel label;
    
    public PhoneNumber(){
        this.phoneNumber = null;
        this.label = null;
    }
    
    public PhoneNumber(String phoneNumber, EntryLabel label){
        this.phoneNumber = phoneNumber;
        this.label = label;
    }
    
    public EntryLabel getLabel(){
        return this.label;
    }
    
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public void setLabel(EntryLabel label){
        this.label = label;
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
