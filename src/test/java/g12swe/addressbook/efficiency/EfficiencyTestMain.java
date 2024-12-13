package g12swe.addressbook.efficiency;

import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableSet;

public class EfficiencyTestMain {

    
    public static void main(String[] args) throws IOException {
        
        AddressBook ab = new AddressBook();
        EfficiencyTest test = new EfficiencyTest("C:\\Users\\ACER\\G12-Rubrica\\test.bin", ab.getContactList());
        
        //test.generateFile(5000);
        
        // --- PRIMO CASO DI TEST (CARICAMENTO DA FILE) ---
        
        ObservableSet<Contact> contacts = ab.getContactList();
        long startTime1 = System.nanoTime();
        contacts = test.importFromFile();
        long endTime1 = System.nanoTime();
        
        long duration1 = (endTime1-startTime1);
        
        // MEDIAMENTE 0.15 s (OTTIMO!!!)
        System.out.println("Durata caricamento file (ns): " + duration1);
        
        // --- FINE PRIMO CASO DI TEST ---
        // --- SECONDO CASO DI TEST (RICERCA DI UN CONTATTO) ---
        
        List<Contact> contacts2 = new ArrayList<>(contacts);
        Contact c = contacts2.get(2423);
        
        long startTime2 = System.nanoTime();
        boolean b = contacts.contains(c);
        long endTime2 = System.nanoTime();
        
        long duration2 = (endTime2-startTime2);
       
        // MEDIAMENTE 0.2 ms (OTTIMO!!!)
        System.out.println("Durata ricerca di un contatto (ns): " + duration2);
        
        // --- FINE SECONDO CASO DI TEST ---
    }
    
}
