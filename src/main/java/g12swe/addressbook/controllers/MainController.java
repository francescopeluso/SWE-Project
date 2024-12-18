package g12swe.addressbook.controllers;

import g12swe.addressbook.App;
import g12swe.addressbook.exceptions.LimitReachedException;
import g12swe.addressbook.models.AddressBook;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.ExtendedContact;
import g12swe.addressbook.service.ContactFileService;
import g12swe.addressbook.service.FileService;
import g12swe.addressbook.service.ImportExportService;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @file MainController.java
 * @brief Controller for managing the main view of the address book application.
 *
 * This controller is responsible for handling the main interface, which displays a list of contacts,
 * and all the buttons and menus that implements part of the software features.
 */
public class MainController implements Initializable {
    
    /**
     * Reference to ContactController of this application.
     */
    private ContactController contactController;
    
    /**
     * Reference to AddressBook which is initialized in <code>initialize()</code>
     */
    private AddressBook ab;
    
    /**
     * This <code>ObservableList</code> is used to synchronize the content
     * of the TableView FXML object with the content of the TreeSed used
     * in the <code>AddressBook</code> to contain all the contacts.
     */
    private ObservableList<Contact> observableContactsList;
    
    @FXML
    private TextField searchField; ///< TextField for searching contacts.
    
    @FXML
    private Button addContactBtn; ///< Button for adding a new contact.
    
    @FXML
    private ListView<Contact> contactListView; ///< ListView displaying the contact list.

    @FXML
    private MenuItem importSingleContactButton; ///< MenuItem for importing a single contact.
    
    @FXML
    private MenuItem exportSingleContactButton; ///< MenuItem for exporting a single contact.
    
    private ContactFileService cfs; ///< ListView displaying the contact list.
    

    /**
     * @brief Initializes the controller and its bindings.
     *
     * This method sets up the initial state of the controller, including loading
     * the AddressBook, populating the UI, and configuring bindings.
     *
     * @param url URL for the root object.
     * @param rb ResourceBundle for localization.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ab = new AddressBook();
        observableContactsList = FXCollections.observableArrayList();

        FileService fileService = new FileService(App.getSaveFilePath(), ab.getContactList());
           
        cfs = new ContactFileService(fileService);
        cfs.setOnSucceeded(event -> {
            
            ObservableSet<Contact> importedContacts = cfs.getValue();
            
            if (importedContacts != null) {
                ab.getContactList().clear();
                ab.getContactList().addAll(importedContacts);
                observableContactsList.clear();
                observableContactsList.addAll(importedContacts);
            }
                
        });

        cfs.setOnFailed(event -> {
            System.out.println("Errore nell'importazione");
        });

        cfs.start(); 
        
        
        ab.getContactList().addListener((SetChangeListener.Change<? extends Contact> change) -> {
            if (change.wasAdded()) {
                observableContactsList.add(change.getElementAdded());
            }
            if (change.wasRemoved()) {
                observableContactsList.remove(change.getElementRemoved());
            }
            
            contactListView.setItems(observableContactsList);
        });

        contactListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty || contact == null) {
                    setText(null);
                } else {
                    setText(contact.getName() + " " + contact.getSurname());
                }
            }
        });

        searchField.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> filterContacts());

        contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (contactController != null) {
                contactController.loadContactDetails(newValue); // Pass null when no selection
            }
            exportSingleContactButton.setDisable(newValue == null);
        });

        exportSingleContactButton.setDisable(true);

        if (!ab.getContactList().isEmpty() && contactController != null) {
            contactListView.getSelectionModel().selectFirst();
            Contact firstContact = contactListView.getSelectionModel().getSelectedItem();
            if (firstContact != null) {
                contactController.loadContactDetails(firstContact);
            }
        }

    }
    
    /**
     * Associate this controller to the ContactController of the application.
     * @param contactController 
     */
    public void setContactController(ContactController contactController) {
        this.contactController = contactController;
    }

    /**
     * @brief Retrieves the AddressBook associated with this controller.
     * @return The AddressBook instance.
     */
    public AddressBook getAddressBook() {
        return ab;
    }
    
    /**
     * @brief Updates the ListView with sorted contacts.
     */
    public void updateListView() {
        List<Contact> sortedList = new ArrayList<>(ab.getContactList());
        Collections.sort(sortedList);
        observableContactsList.setAll(sortedList);
        contactListView.setItems(observableContactsList);
    }
    
    /**
     * @brief Saves the current state of the AddressBook.
     */
    public void saveAddressBookState() {
        cfs.performContactOperation();
    }
    
    /**
     * @brief Closes the application.
     *
     * This method exits the application when the exit button is clicked.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void exitProgram(ActionEvent event) throws IOException {
        FileService fileService = new FileService(App.getSaveFilePath(), ab.getContactList());
        fileService.exportToFile();
        Platform.exit();
    }
    
    /**
     * @brief Opens the documentation in the web browser.
     *
     * Navigates to the documentation hosted on GitHub.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void openDocumentation(ActionEvent event) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI("https://github.com/francescopeluso/SWE-Project/tree/main/docs");
                desktop.browse(uri);
            } else {
                System.err.println("Browsing not supported on this system.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      /**
     * @brief Opens the GitHub repository in the web browser.
     *
     * Navigates to the repository's URL.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void openRepository(ActionEvent event) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = new URI("https://github.com/francescopeluso/SWE-Project.git");
                desktop.browse(uri);
            } else {
                System.err.println("Browsing not supported on this system.");
            }
        } catch (Exception e) {}
    }

    /**
     * @brief Handles importing contacts from a vCard file.
     *
     * Asks the user to select a file to import and then imports the contacts
     * from the file. Displays a success or error alert.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void importVCard(ActionEvent event) {
        try {
            Stage stage = (Stage) contactListView.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importa file VCF");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("VCard Files", "*.vcf")
            );
            
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                ImportExportService importService = new ImportExportService(file.getAbsolutePath(), ab.getContactList());
                ObservableSet<Contact> importedContacts = importService.importFromFile();
                
                if (importedContacts != null) {
                    ab.getContactList().addAll(importedContacts);
                    updateListView();
                    saveAddressBookState();
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Importazione completata");
                    alert.setHeaderText(null);
                    alert.setContentText("I contatti sono stati importati con successo");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Si è verificato un errore durante l'importazione");
            alert.showAndWait();
        }
    }
    
    /**
     * @brief Handles exporting contacts from a vCard file.
     *
     * Asks the user to select a path to save the file and then exports the contacts
     * to a vCard file. Displays a success or error alert.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void exportVCard(ActionEvent event) {
        try {
            Stage stage = (Stage) contactListView.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salva file VCF");
            fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("VCard Files", "*.vcf")
            );
            
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
            ImportExportService exportService = new ImportExportService(file.getAbsolutePath(), ab.getContactList());
            exportService.exportToFile();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Esportazione completata");
            alert.setHeaderText(null);
            alert.setContentText("I contatti sono stati esportati con successo");
            alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Si è verificato un errore durante l'esportazione");
            alert.showAndWait();
        }
    }

    /**
     * @brief Handles importing a single contact from a vCard file.
     *
     * 
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void exportSingleVCard(ActionEvent event) {
        try {
            Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                Stage stage = (Stage) contactListView.getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Salva file VCF");
                fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("VCard Files", "*.vcf")
                );
                
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    ImportExportService exportService = new ImportExportService(file.getAbsolutePath(), ab.getContactList());
                    exportService.exportSingleContact(selectedContact);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Esportazione completata");
                    alert.setHeaderText(null);
                    alert.setContentText("Il contatto è stato esportato con successo");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Si è verificato un errore durante l'esportazione");
            alert.showAndWait();
        }
    }

    /**
     * @brief Handles inserting a new contact.
     *
     * Asks the user to input the contact details and then adds the contact to the AddressBook.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    private void insertContact(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactView.fxml"));
            Parent root = loader.load();
            
            ContactController contactController = loader.getController();
            contactController.setMainController(this);
            contactController.prepareForNewContact(); // Call new method
            
            Stage stage = new Stage();
            stage.setTitle("Nuovo Contatto");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(contactListView.getScene().getWindow());
            stage.showAndWait();
            
        } catch (IOException e) {}
    }

    
    /**
     * @brief Adds a new contact to the AddressBook.
     *
     * @param contact The contact to add.
     */

    public void addContact(ExtendedContact contact) throws LimitReachedException {
        ab.addContact(contact);
        updateListView();
        
        // Find and select the newly added contact by matching name and surname
        for (int i = 0; i < observableContactsList.size(); i++) {
            Contact c = observableContactsList.get(i);
            if (c.getName().equals(contact.getName()) && 
                c.getSurname().equals(contact.getSurname())) {
                contactListView.getSelectionModel().select(i);
                contactController.loadContactDetails(c); // Use the stored contact, not the original
                break;
            }
        }
        
        saveAddressBookState();
    }
    
    /**
     * @brief Handles deleting a contact.
     *
     * Removes the selected contact from the AddressBook.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void deleteContact(ActionEvent event) {
        Contact toDelete = contactListView.getSelectionModel().getSelectedItem();
        if (toDelete != null) {
            ab.removeContact(toDelete);
            observableContactsList.setAll(ab.getContactList());
            
            if (observableContactsList.isEmpty()) {
                // Clear the contact details if no contacts remain
                contactController.loadContactDetails(null);
            } else {
                contactListView.getSelectionModel().selectFirst();
            }
            
            saveAddressBookState();
        }
    }
    
    
    /**
     * @brief Resets the AddressBook to its initial state.
     *
     * Clears all contacts from the AddressBook and the ListView.
     * Displays a confirmation alert before proceeding.
     *
     * @param event The action event triggered by the user.
     */

    @FXML
    private void reinitializeAddressBook(ActionEvent event) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Reinizializza Rubrica");
        confirmAlert.setHeaderText("Sei sicuro di voler eliminare tutti i contatti?");
        confirmAlert.setContentText("Questa azione non può essere annullata.");

        if (confirmAlert.showAndWait().get().getButtonData().isDefaultButton()) {
            ab.getContactList().clear();
            observableContactsList.clear();
            contactListView.setItems(observableContactsList);
            saveAddressBookState();
            
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Rubrica reinizializzata");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Tutti i contatti sono stati eliminati con successo.");
            successAlert.showAndWait();
        }
    }
    
    /**
     * @brief Filters contacts in the ListView based on the search field input.
     */
    
    private void filterContacts() {
        String searchText = searchField.getText().toLowerCase();
        
        ObservableList<Contact> filteredList = FXCollections.observableArrayList();
        for (Contact contact : ab.getContactList()) {
            if ((contact.getName() + " " + contact.getSurname()).toLowerCase().contains(searchText)) {
                filteredList.add(contact);
            }
        }
        
        contactListView.setItems(filteredList);
        contactListView.getSelectionModel().selectFirst();
    }
    
    /**
     * Handles when a contact is updated, ensuring proper selection and display
     */
    public void handleContactUpdated(Contact updatedContact) {
        updateListView();
        
        // Find and select the updated contact
        for (int i = 0; i < observableContactsList.size(); i++) {
            Contact c = observableContactsList.get(i);
            if (c.getName().equals(updatedContact.getName()) && 
                c.getSurname().equals(updatedContact.getSurname())) {
                contactListView.getSelectionModel().select(i);
                contactController.loadContactDetails(c);
                break;
            }
        }
    }
}
