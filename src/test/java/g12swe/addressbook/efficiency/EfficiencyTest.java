package g12swe.addressbook.efficiency;

import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.service.FileService;
import java.io.IOException;
import java.util.Random;
import javafx.collections.ObservableSet;


public class EfficiencyTest {
    
    private static final String[] NOMI = {
        "Marco", "Luca", "Giovanni", "Alessandro", "Andrea", 
        "Carlo", "Francesco", "Paolo", "Roberto", "Matteo",
        "Anna", "Laura", "Chiara", "Sofia", "Elena", 
        "Maria", "Giulia", "Valentina", "Francesca", "Sara"
    };
    private static final String[] COGNOMI = {
        "Rossi", "Bianchi", "Esposito", "Ferrari", "Colombo", 
        "Romano", "Ricci", "Greco", "Marino", "Conti",
        "Bruno", "Villa", "Russo", "Costa", "Lombardi"
    };
    
    private final FileService fileService;
    
    public EfficiencyTest(String fileName, ObservableSet<Contact> contacts){
        this.fileService = new FileService(fileName, contacts);
    }
    
    public void generateFile(int num) throws IOException{
        Random random = new Random();
        Contact c = null;
        
        for(int i=0; i<num; i++){
            
            c = new Contact(NOMI[random.nextInt(NOMI.length)], COGNOMI[random.nextInt(COGNOMI.length)]);
            this.fileService.getContacts().add(c);
            
        }
        
        this.fileService.exportToFile();
    }
    
    public ObservableSet<Contact> importFromFile() throws IOException{
        return this.fileService.importFromFile();
    }
}
