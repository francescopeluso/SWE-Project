package g12swe.addressbook.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * @file RootController.java
 * @brief
 * 
 * More detailed information about the file and its role is in the project.
 * 
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
