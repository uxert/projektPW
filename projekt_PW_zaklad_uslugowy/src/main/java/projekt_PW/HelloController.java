package projekt_PW;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    boolean isTheStoreRunning = false;
    RepairStore myStore;


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onSecondButtonClicked()
    {
        if (isTheStoreRunning) return; //does not do anything if the store is already running
        try {
            myStore = new RepairStore(this);
            myStore.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        welcomeText.setText("Odpalam sklep");
        myStore.start();
    }
}