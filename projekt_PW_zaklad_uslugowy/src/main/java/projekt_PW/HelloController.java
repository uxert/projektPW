package projekt_PW;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloController {

// There are
    ExecutorService receptionist = Executors.newSingleThreadExecutor();
    ExecutorService repairmen = Executors.newFixedThreadPool(3);
    private ItemShelfMonitor myShelf;


    final int repairmenAmount = 3;
    final int maxItemCount = 100;

    boolean isTheStoreRunning = false, isTheStoreClosed = false;
    public ArrayDeque<Circle> itemsOnShelf;


    @FXML
    private Button addRepairButton;

    @FXML
    private HBox shelfGUI;
    public HBox getShelfGUI()
    {
        return shelfGUI;
    }

    @FXML
    private Label welcomeText;

    public AnchorPane getShopPane() {
        return shopPane;
    }

    @FXML
    private AnchorPane shopPane;

    @FXML
    public VBox receptionistGUI;
    @FXML
    private Pane repairmanGUI1;
    @FXML
    private Pane repairmanGUI2;
    @FXML
    private Pane repairmanGUI3;
    Pane[] repairmenGUI;

    @FXML
    protected void openTheStore()
    {
        if (isTheStoreClosed)
        {
            welcomeText.setText("You cannot reopen the store");
            return;
        }
        System.out.println("=============Opening the store==============");
        isTheStoreRunning = true;
    }

    @FXML
    protected void onAddItemClick() throws InterruptedException {
        if(!isTheStoreRunning)
        {
            welcomeText.setText("The shop is not open!");
            return; // does nothing if there is NO shop currently running
        }
        FixedItem temp = new FixedItem("No address (yet)");
        welcomeText.setText("You clicked to add a new item");
        receptionist.submit(new TaskAddOnShelf(myShelf, temp, this));
    }

    @FXML
    protected void closeStore()
    {
        System.out.println("-------Store is now closing the doors, no new items will be accepted------------");
        isTheStoreRunning = false;
        welcomeText.setText("The store will not accept any new items to repair");
    }

    @FXML
    protected void endForAll()
    {
        if (isTheStoreClosed)
        {
            return;
        }
        System.out.println("<<<<<<<<<<<<Store is now ending its work for good>>>>>>>>>>>>>");
        isTheStoreRunning = false;
        repairmen.shutdown();
        receptionist.shutdown();
        welcomeText.setText("The store has closed for good, you may close the app now");
        isTheStoreClosed = true;
    }

    public void initialize()
    {
        //sets background colors
        shopPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
        Background workersBackGUI = new Background(new BackgroundFill(Color.LAVENDER, null, null));
        repairmenGUI = new Pane[]{repairmanGUI1, repairmanGUI2, repairmanGUI3};
        for (Pane pane : repairmenGUI) {
            pane.setBackground(workersBackGUI);
        }
        BorderStroke borderStroke = new BorderStroke(
                Color.BLACK, // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(5), // Corner radii
                new BorderWidths(2) // Border widths
        );
        Border border = new Border(borderStroke);
        receptionistGUI.setBorder(border);
        receptionistGUI.setAlignment(Pos.CENTER);

        //creates the shelf
        myShelf = new ItemShelfMonitor(maxItemCount, repairmenAmount, this);
        itemsOnShelf = new ArrayDeque<>();
        shelfGUI.setSpacing(10);
    }

}