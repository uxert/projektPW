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

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.*;

public class HelloController {
    final int repairmenAmount = 3;
    PausableThreadPoolExecutor receptionist = new PausableThreadPoolExecutor(1, 150);
    PausableThreadPoolExecutor repairmen = new PausableThreadPoolExecutor(repairmenAmount, 250);
    private ItemShelfMonitor myShelf;

    private final CFG myCFG = new CFG();

    public CFG getCFG() {
        return myCFG;
    }

    boolean isTheStoreRunning = false, isTheStoreClosed = false;
    public LinkedBlockingDeque<Circle> itemsOnShelf;
    public LinkedBlockingDeque<Integer> availableRepairmen = new LinkedBlockingDeque<>(3);

    @FXML
    private HBox shelfGUI;
    public HBox getShelfGUI()
    {
        return shelfGUI;
    }

    @FXML
    private Label welcomeText;

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
    @FXML
    private StackPane itemsOnShelfCounterContainerGUI;
    private Label waitingItemsCountGUI;
    private Label itemsOnShelfCountGUI;
    private SimpleIntegerProperty waitingItems;
    private SimpleIntegerProperty shelfCount;

    StackPane[] repairmenGUI;

    @FXML
    protected void openTheStore()
    {
        if (isTheStoreClosed)
        {
            welcomeText.setText("You cannot reopen the store once you closed it");
            return;
        }
        System.out.println("=============Opening the store==============");
        isTheStoreRunning = true;
        repairmen.resume();
        receptionist.resume();
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
        FixedItem temp = new FixedItem("No address (yet)", myCFG);
        welcomeText.setText("You clicked to add a new item");
        receptionist.submit(new TaskAddOnShelf(myShelf, temp, this));
    }
    public void decrementWaitingCount()
    {
        this.waitingItems.set(this.waitingItems.get() - 1);
    }
    public void incShelfCount(){ this.shelfCount.set(this.shelfCount.get() + 1);}
    public void decShelfCount(){ this.shelfCount.set(this.shelfCount.get() - 1);}
    @FXML
    protected void pauseTheStore()
    {
        System.out.println("-------Store is now closing the doors and halting, no new items will be accepted---------");
        isTheStoreRunning = false;
        receptionist.pause();
        repairmen.pause();
        welcomeText.setText("The simulation is paused");
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

    public void configParams()
    {
        Properties p = new Properties();
        try {
            p.load(getClass().getResourceAsStream("configParams.properties"));
        } catch (IOException | NullPointerException e) {
            System.out.println("-------------Properties file not found or not loadable, default values will be used");
            return;
        }
        myCFG.readAnimationTimes(p);

    }
    public void initialize()
    {
        configParams();

        waitingItems = new SimpleIntegerProperty(0);
        waitingItemsCountGUI = new Label();
        waitingItemsCountGUI.textProperty().bind(Bindings.convert(waitingItems));
        waitingItemsCountGUI.setFont(new Font(20));

        itemsOnShelfCountGUI = new Label();
        shelfCount = new SimpleIntegerProperty(0);
        itemsOnShelfCountGUI.textProperty().bind(Bindings.convert(shelfCount));
        itemsOnShelfCountGUI.setFont(new Font(20));

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
        myShelf = new ItemShelfMonitor(myCFG.maxItemCount, this);
        itemsOnShelf = new LinkedBlockingDeque<>();
        shelfGUI.setSpacing(10);

        Circle tempName = new Circle(50);
        tempName.setFill(Color.TRANSPARENT);
        tempName.setStroke(Color.BLACK);
        tempName.setStrokeWidth(5);
        waitingItemsContainterGUI.getChildren().add(tempName);
        waitingItemsContainterGUI.getChildren().add(waitingItemsCountGUI);

        tempName = new Circle(50);
        tempName.setFill(Color.TRANSPARENT);
        tempName.setStroke(Color.BLACK);
        tempName.setStrokeWidth(5);
        itemsOnShelfCounterContainerGUI.getChildren().add(tempName);
        itemsOnShelfCounterContainerGUI.getChildren().add(itemsOnShelfCountGUI);
    }

}