package g12swe.addressbook.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * @file RootController.java
 * @brief Controller for managing the overall structure and flow of the application.
 * 
 * Responsibilities include loading and initializing views, including the main interface
 * and detailed contact views. This controller ensures smooth navigation and layout
 * responsiveness as user resizes the application window.
 */
public class RootController {

    @FXML
    private StackPane mainView;
    @FXML
    private StackPane secondaryView;
    @FXML
    private HBox rootView;
    
    public void showMainViewOnly() {
        rootView.getChildren().clear(); // Rimuove tutte le view
        rootView.getChildren().add(mainView); // Aggiunge solo MainView
        HBox.setHgrow(mainView, Priority.ALWAYS); // Forza MainView a occupare tutto lo spazio
    }

    public void showBothViews() {
        rootView.getChildren().clear(); // Ripulisce il contenitore
        rootView.getChildren().addAll(mainView, secondaryView); // Aggiunge entrambe le view
        HBox.setHgrow(mainView, Priority.ALWAYS); // Configura la crescita delle view
        HBox.setHgrow(secondaryView, Priority.ALWAYS);
    }
    
}
