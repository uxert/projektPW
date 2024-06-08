package projekt_PW;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class HelloController {

    boolean isTheStoreRunning = false;
    private RepairStore myStore;
    private ArrayDeque<Circle> itemsOnShelf;
    public void addToShelfAnimation()
    {
        Circle temp = new Circle(25);
        itemsOnShelf.add(temp);
        shelfGUI.getChildren().add(temp);
    }

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
    protected void onAddItemClick() throws InterruptedException {
        if(!isTheStoreRunning)
        {
            welcomeText.setText("The shop is still closed!");
            return; // does nothing if there is NO shop currently running
        }
        FixedItem temp = new FixedItem("No address (yet)");
        myStore.receptionist.clientQueue.add(temp); // adds to the queue of clients waiting to be served
        welcomeText.setText("You clicked to add a new item");
        addToShelfAnimation();
    }

    public void initialize()
    {
        itemsOnShelf = new ArrayDeque<>();
        shelfGUI.setSpacing(10);

        System.out.println("Faktycznie sie inicjalizuje ");
    }

}