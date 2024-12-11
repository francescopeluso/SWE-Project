package g12swe.addressbook.service;

import g12swe.addressbook.models.contacts.Contact;
import javafx.collections.ObservableSet;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ContactFileService extends Service<ObservableSet<Contact>>{
    
    private final FileService fileService;
    private final boolean isImport;
    
    public ContactFileService(FileService fileService, boolean isImport){
        this.fileService = fileService;
        this.isImport = isImport;
    }
    
    @Override
    protected Task<ObservableSet<Contact>> createTask(){
        
        return new Task<ObservableSet<Contact>>(){
            @Override
            protected ObservableSet<Contact> call() throws Exception {
                try{
                    if(isImport){
                        return fileService.importFromFile();
                    }
                    else{
                        fileService.exportToFile();
                        return null;
                    }
                    
                }
                catch(Exception e){
                    System.out.println("Errore durante l'operazione");
                    return null;
                }
                
            }

        };
    }
    
}
