package projekt_PW;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HelloController {

    boolean isTheStoreRunning = false;
    private RepairStore myStore;
    @FXML
    private Button addRepairButton;

    @FXML
    private HBox shelfGUI;

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
        myStore = new RepairStore(this);
        welcomeText.setText("Opening the shop");
        myStore.start();
    }

    @FXML
    protected void onAddItemClick()
    {
        FixedItem temp = new FixedItem("No address (yet)");
        myStore.receptionist.clientQueue.add(temp); // adds to the queue of clients waiting to be served
        welcomeText.setText("You clicked to add the new item");

    }

}