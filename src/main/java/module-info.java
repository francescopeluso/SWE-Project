module g20swe.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires java.desktop;
    requires java.base;

    exports g20swe.addressbook;
    opens g20swe.addressbook.controller to javafx.fxml;
}
