package g12swe.addressbook.service;

import g12swe.addressbook.models.contacts.Contact;
import javafx.collections.ObservableSet;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * @class ContactFileService
 * @brief Service class for handling asynchronous contact file operations.
 *
 * This class extends JavaFX's Service class to provide asynchronous file operations
 * for contacts. It manages both importing and exporting contacts using a FileService,
 * running these potentially time-consuming operations in background threads to avoid
 * blocking the UI.
 */

public class ContactFileService extends Service<ObservableSet<Contact>>{
    
    private final FileService fileService; ///< The FileService instance used for file operations 
    
    /**
     * @brief Constructor for ContactFileService
     * 
     * @param fileService The FileService instance to use for file operations
     */
    public ContactFileService(FileService fileService){
        this.fileService = fileService;
    }
    
    /**
     * @brief Creates a new Task for importing contacts
     * 
     * Creates and returns a Task that reads contacts from a file asynchronously.
     * The task attempts to import contacts using the FileService and handles any
     * potential errors during the operation.
     * 
     * @return A Task that will return an ObservableSet of Contact objects when complete
     *         or null if an error occurs during import
     * @override Service.createTask()
     */
    @Override
    protected Task<ObservableSet<Contact>> createTask(){
        
        return new Task<ObservableSet<Contact>>(){
            @Override
            protected ObservableSet<Contact> call() throws Exception {
                try{
                    ObservableSet<Contact> c = fileService.importFromFile();
                    return c;      
                } catch(Exception e){
                    System.out.println("Errore durante l'operazione");
                    return null;
                }
                
            }

        };
    }
    
    
    /**
     * @brief Performs asynchronous contact export operation
     * 
     * Creates and starts a new Thread with a Task that exports contacts to a file.
     * The operation runs asynchronously and includes success/failure handlers.
     * On successful completion, prints a confirmation message.
     * On failure, prints an error message.
     */
    
    public void performContactOperation(){

        Task<Void> operationTask = new Task<>(){
            
            @Override
            protected Void call() {
                try{
                    fileService.exportToFile();
                } catch(Exception e){
                    System.out.println("Errore durante l'operazione");
                }
                
                return null;
            }
        };
        
        operationTask.setOnSucceeded(event -> {
            System.out.println("Rubrica salvata correttamente.");
        });
        
        new Thread(operationTask).start();
    }
    
}
