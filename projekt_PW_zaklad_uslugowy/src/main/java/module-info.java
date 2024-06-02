module org.example.projekt_pw_zaklad_uslugowy {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projekt_pw_zaklad_uslugowy to javafx.fxml;
    exports org.example.projekt_pw_zaklad_uslugowy;
}