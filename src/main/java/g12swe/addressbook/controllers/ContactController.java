package g12swe.addressbook.controllers;

import g12swe.addressbook.models.contacts.Contact;
import g12swe.addressbook.models.contacts.PhoneNumber;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
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
    @FXML
    private Label fullNameLabel;
    @FXML
    private VBox phoneNumbersList;
    @FXML
    private VBox emailAddressesList;
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void loadContactDetails(Contact contact) {
        List<PhoneNumber> contactPhoneNumbers = contact.getPhoneNumbers();
    
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

        fullNameLabel.setText(contact.getName() + " " + contact.getSurname());
        
        //contactEmailLabel.setText(contact.getEmailAddresses().isEmpty() ? "No email" : contact.getEmailAddresses().get(0).getEmailAddress());
        //contactPhoneLabel.setText(contact.getPhoneNumbers().isEmpty() ? "No phone" : contact.getPhoneNumbers().get(0).getPhoneNumber());
    }
    
}
