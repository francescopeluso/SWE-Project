package g20swe.addressbook;

import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RootView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.widthProperty().addListener((o, oldValue, newValue)->{
            if(newValue.intValue() < 256F) {
                stage.setResizable(false);
                stage.setWidth(256);
                stage.setResizable(true);
            }
        });
        
        stage.heightProperty().addListener((o, oldValue, newValue)->{
            if(newValue.intValue() < 440F) {
                stage.setResizable(false);
                stage.setHeight(440);
                stage.setResizable(true);
            }
        });
                
        stage.setTitle("Rubrica telefonica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}