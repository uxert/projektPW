package projekt_PW;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.TimeUnit;

public class TaskAddOnShelf extends Task<Boolean> {

    private final ItemShelfMonitor shelf;
    private final FixedItem item;
    private final HelloController control;
    public TaskAddOnShelf(ItemShelfMonitor shelf, FixedItem item, HelloController control)
    {
        this.shelf = shelf;
        this.item = item;
        this.control = control;
    }

    /**
     *
     * @return Returns true if an item has been successfully added to the shelf and false if it has not
     */
    public Boolean call() throws InterruptedException {
        Circle itemGUI = new Circle(150, 200, 25, Color.color(Math.random(), Math.random(), Math.random()));
        Runnable giveReceptionistItemRunnable = () ->{
            control.decrementWaitingCount();
            control.receptionistGUI.getChildren().add(itemGUI);
        };
        Platform.runLater(giveReceptionistItemRunnable);
        item.address = "Timestamp: " + System.currentTimeMillis();
        TimeUnit.MILLISECONDS.sleep(300);
        if (shelf.checkForSpace())
        {
            shelf.addItemToShelf(item);
            return true;
        }
        return false;
    }
}
