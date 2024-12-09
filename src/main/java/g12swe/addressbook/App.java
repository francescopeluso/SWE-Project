package g12swe.addressbook;

import g12swe.addressbook.controllers.RootController;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


/**
 * @file App.java
 * @brief Application loader.
 * 
 * This file loads up the application and all its core components (stage,
 * scene, view loaders and controllers) and keeps the application layout
 * responsive as window gets resized by user. 
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
        Parent root = loader.load();

        this.fileSetup();

        Scene scene = new Scene(root);
        
        stage.widthProperty().addListener((o, oldValue, newValue) -> {
            
            RootController controller = loader.getController();
            if (newValue.intValue() < 400) {
                controller.showMainViewOnly();
                
                if(newValue.intValue() < 256) {
                    stage.setResizable(false);
                    stage.setWidth(256);
                    stage.setResizable(true);
                }
                
            } else {
                controller.showBothViews();
            }
            
        });
        
        stage.heightProperty().addListener((o, oldValue, newValue)->{
            if(newValue.intValue() < 440F) {
                stage.setResizable(false);
                stage.setHeight(440);
                stage.setResizable(true);
            }
        });
                
        stage.setTitle("Gruppo 12 | Rubrica telefonica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void fileSetup() {
        String userDir = System.getProperty("user.home");
        File baseDir = new File(userDir + File.separator + "G12-Rubrica");

        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                throw new RuntimeException("Impossibile creare la cartella di base: " + baseDir.getAbsolutePath());
            }
        }

        File saveFile = new File(baseDir, "G12-Rubrica-savefile.bin");

        if (!saveFile.exists()) {
            try {
                if (!saveFile.createNewFile()) {
                    throw new RuntimeException("Impossibile creare il file di salvataggio: " + saveFile.getAbsolutePath());
                }
            } catch (IOException e) {
                throw new RuntimeException("Errore nella creazione del file di salvataggio: " + saveFile.getAbsolutePath(), e);
            }
        }
    }

}