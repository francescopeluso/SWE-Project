package g12swe.addressbook.service;

import g12swe.addressbook.models.contacts.Contact;
import javafx.collections.ObservableSet;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ContactFileService extends Service<ObservableSet<Contact>>{
    
    private final FileService fileService;
    private boolean isImport;
    
    public ContactFileService(FileService fileService){
        this.fileService = fileService;
        this.isImport = false;
    }
    
    public ContactFileService(FileService fileService, boolean isImport){
        this.fileService = fileService;
        this.isImport = isImport;
    }
    
    public void setIsImport(boolean isImport){
        this.isImport = isImport;
    }
    
    public FileService getFileService(){
        return this.fileService;
    }
    
    public boolean getIsImport(){
        return this.isImport;
    }
    
    @Override
    protected Task<ObservableSet<Contact>> createTask(){
        
        return new Task<ObservableSet<Contact>>(){
            @Override
            protected ObservableSet<Contact> call() throws Exception {
                try{
                    if(isImport){
                        ObservableSet<Contact> c = fileService.importFromFile();
                        return c;
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
