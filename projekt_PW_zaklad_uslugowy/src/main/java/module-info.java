module org.example.projekt_pw_zaklad_uslugowy {
    requires javafx.controls;
    requires javafx.fxml;


    opens projekt_PW to javafx.fxml;
    exports projekt_PW;
    exports projekt_PW.runnable_animations;
    opens projekt_PW.runnable_animations to javafx.fxml;
}