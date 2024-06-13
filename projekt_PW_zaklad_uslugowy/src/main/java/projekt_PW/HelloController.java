package projekt_PW;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.*;

public class HelloController {

// There are
    ExecutorService receptionist = Executors.newSingleThreadExecutor();
    ExecutorService repairmen = Executors.newFixedThreadPool(3);
    private ItemShelfMonitor myShelf;


    final int repairmenAmount = 3;
    final int maxItemCount = 100;

    boolean isTheStoreRunning = false, isTheStoreClosed = false;
    public LinkedBlockingDeque<Circle> itemsOnShelf;
    public LinkedBlockingDeque<Integer> availableRepairmen = new LinkedBlockingDeque<Integer>(3);

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
    private StackPane repairmanGUI1;
    @FXML
    private StackPane repairmanGUI2;
    @FXML
    private StackPane repairmanGUI3;
    @FXML
    private StackPane waitingItemsContainterGUI;
    private Label waitingItemsCountGUI;
    private SimpleIntegerProperty waitingItems;

    StackPane[] repairmenGUI;

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
    protected void onAddItemClick()
    {
        if(!isTheStoreRunning)
        {
            welcomeText.setText("The shop is not open!");
            return; // does nothing if there is NO shop currently running
        }
        waitingItems.set(waitingItems.get() + 1);
        FixedItem temp = new FixedItem("No address (yet)");
        welcomeText.setText("You clicked to add a new item");
        receptionist.submit(new TaskAddOnShelf(myShelf, temp, this));
    }
    public void decrementWaitingCount()
    {
        this.waitingItems.set(this.waitingItems.get() - 1);
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
        waitingItems = new SimpleIntegerProperty(0);
        waitingItemsCountGUI = new Label();
        waitingItemsCountGUI.textProperty().bind(Bindings.convert(waitingItems));
        waitingItemsCountGUI.setFont(new Font(20));
        availableRepairmen.add(0);
        availableRepairmen.add(1);
        availableRepairmen.add(2);

        //sets background colors
        shopPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
        Background workersBackGUI = new Background(new BackgroundFill(Color.LAVENDER, null, null));
        repairmenGUI = new StackPane[]{repairmanGUI1, repairmanGUI2, repairmanGUI3};

        BorderStroke borderStroke = new BorderStroke(
                Color.BLACK, // Border color
                BorderStrokeStyle.SOLID, // Border style
                new CornerRadii(5), // Corner radii
                new BorderWidths(2) // Border widths
        );
        Border border = new Border(borderStroke);
        receptionistGUI.setBorder(border);
        receptionistGUI.setAlignment(Pos.CENTER);

        for (StackPane pane : repairmenGUI) {
            pane.setBackground(workersBackGUI);
            pane.setBorder(border);
            pane.setAlignment(Pos.CENTER);
        }

        //creates the shelf
        myShelf = new ItemShelfMonitor(maxItemCount, repairmenAmount, this);
        itemsOnShelf = new LinkedBlockingDeque<>();
        shelfGUI.setSpacing(10);

        Circle tempName = new Circle(50);
        tempName.setFill(Color.TRANSPARENT);
        tempName.setStroke(Color.BLACK);
        tempName.setStrokeWidth(5);
        waitingItemsContainterGUI.getChildren().add(tempName);
        waitingItemsContainterGUI.getChildren().add(waitingItemsCountGUI);
    }

}