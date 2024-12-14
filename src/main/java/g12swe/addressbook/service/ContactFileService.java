package g12swe.addressbook.service;

import g12swe.addressbook.models.contacts.Contact;
import javafx.collections.ObservableSet;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ContactFileService extends Service<ObservableSet<Contact>>{
    
    private final FileService fileService;
    
    public ContactFileService(FileService fileService){
        this.fileService = fileService;
    }
    
    @Override
    protected Task<ObservableSet<Contact>> createTask(){
        
        return new Task<ObservableSet<Contact>>(){
            @Override
            protected ObservableSet<Contact> call() throws Exception {
                try{
                    
                    ObservableSet<Contact> c = fileService.importFromFile();
                    return c;    
                    
                }
                catch(Exception e){
                    System.out.println("Errore durante l'operazione");
                    return null;
                }
                
            }

        };
    }
    
    public void performContactOperation(){
        
        Task<Void> operationTask = new Task<>(){
            
            @Override
            protected Void call() {
             
                try{
                    fileService.exportToFile();
                }
                catch(Exception e){
                    System.out.println("Errore durante l'operazione");
                }
                
                return null;
            }
        };
        
        operationTask.setOnSucceeded(event -> {
        
            System.out.println("rubrica salvata!!");
        });
        
        new Thread(operationTask).start();
    }
    
}
