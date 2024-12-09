module g12swe.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires java.desktop;
    requires java.base;

    exports g12swe.addressbook;
    opens g12swe.addressbook.controllers to javafx.fxml;
    requires com.googlecode.ezvcard;
}
