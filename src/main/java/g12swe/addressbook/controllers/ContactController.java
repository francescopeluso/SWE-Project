package g12swe.addressbook.controllers;

import g12swe.addressbook.exceptions.MandatoryFieldsException;
import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.PhoneNumber;
import java.util.List;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @file ContactController.java
 * @brief Controller for managing interactions and data display in the <code>ContactView</code>.
 *
 * This class handles the presentation and manipulation of individual contact details 
 * as part of the overall application flow.
 */
public class ContactController {
    
    /**
     * Reference to MainController of this application.
     */
    private MainController mainController;
    
    private Label fullNameLabel;
    @FXML
    private VBox phoneNumbersList;
    @FXML
    private VBox emailAddressesList;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Button addPhoneButton;
    @FXML
    private Button addEmailButton;
    @FXML
    private Button editOrSaveButton;
    
    
    private boolean isEditMode = false;
    private Contact selected = null;
    
    /**
     * Associate this controller to the MainController of the application.
     * @param mainController 
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void loadContactDetails(Contact contact) {
        this.selected = contact;
        List<PhoneNumber> contactPhoneNumbers = this.selected.getPhoneNumbers();
    
        phoneNumbersList.getChildren().clear();

        for (int i = 0; i < contactPhoneNumbers.size(); i++) {
            HBox phoneNumberBox = new HBox();

            TextField phoneNumberField = new TextField(contactPhoneNumbers.get(i).getPhoneNumber());
            phoneNumberField.setPromptText("Numero di telefono");
            phoneNumberBox.getChildren().add(phoneNumberField);

            Button removeButton = new Button("-");
            removeButton.setOnAction(event -> {
                // logica rimozione contatto da implementare
            });
            phoneNumberBox.getChildren().add(removeButton);

            phoneNumbersList.getChildren().add(phoneNumberBox);
        }

        if (contactPhoneNumbers.size() < 3) {
            HBox emptyBox = new HBox();
            
            TextField phoneNumberField = new TextField();
            phoneNumberField.setPromptText("Numero di telefono");
            emptyBox.getChildren().add(phoneNumberField);

            // Aggiungi il pulsante per rimuovere il numero
            Button removeButton = new Button("-");
            removeButton.setOnAction(event -> {
                // Implementa la logica di rimozione del numero di telefono
            });
            emptyBox.getChildren().add(removeButton);

            phoneNumbersList.getChildren().add(emptyBox);
        }

        firstNameField.setText(this.selected.getName());
        lastNameField.setText(this.selected.getSurname());
        
        isEditMode = false;
        disableEditMode();
       
    }
    
    @FXML
    private void handleEditOrSaveAction(ActionEvent event) {
        if (!isEditMode) {
            // Switch to Edit Mode
            enableEditMode();
            editOrSaveButton.setText("Salva");
        } else {
            // Save changes and exit Edit Mode
            try {
                saveContactChanges();
                disableEditMode();
                editOrSaveButton.setText("Modifica");
            } catch (MandatoryFieldsException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Errore");
                alert.setHeaderText("Errore durante il salvataggio");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
        isEditMode = !isEditMode;
    }
    
    private void enableEditMode() {
        // Make fields editable
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);

        // Show add/remove buttons for phone numbers and emails
        addPhoneButton.setVisible(true);

        // Additional fields can be made editable here
    }

    private void disableEditMode() {
        // Make fields non-editable
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);

        // Hide add/remove buttons
        addPhoneButton.setVisible(false);

        // Additional fields can be made non-editable here
    }

    private void saveContactChanges() throws MandatoryFieldsException {
        // Validate and save contact information
        // This could involve calling a method in mainController to update the contact
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        
        if (firstNameField.getText().length() < 1 && lastNameField.getText().length() < 1)
            throw new MandatoryFieldsException("Deve essere inserito almeno uno tra il nome e il cognome.");
        
        System.out.println(firstName + " " + lastName);
        this.selected.setName(firstName);
        this.selected.setSurname(lastName);
        
        mainController.updateListView();

        // Add validation logic here

        // Example of potential save method
        // mainController.updateContact(new Contact(firstName, lastName));
    }
    
    private void updateUIForEditMode() {
        // Enable/disable text fields based on edit mode
        firstNameField.setEditable(isEditMode);
        lastNameField.setEditable(isEditMode);

        // Update save button visibility
        editOrSaveButton.setText("Salva");

        // Handle phone number fields
        for (Node node : phoneNumbersList.getChildren()) {
            if (node instanceof HBox) {
                HBox phoneBox = (HBox) node;
                TextField phoneField = (TextField) phoneBox.getChildren().get(0);
                Button removeButton = (Button) phoneBox.getChildren().get(1);

                phoneField.setEditable(isEditMode);
                removeButton.setVisible(isEditMode);
            }
        }

        // Similarly handle email fields, address, birthday, notes, etc.
    }

    
}
