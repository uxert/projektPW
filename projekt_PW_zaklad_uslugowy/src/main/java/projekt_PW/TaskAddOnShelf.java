package projekt_PW;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.TimeUnit;

public class TaskAddOnShelf extends Task<Boolean> {

    private ItemShelfMonitor shelf;
    private FixedItem item;
    private HelloController control;
    public TaskAddOnShelf(ItemShelfMonitor shelf, FixedItem item, HelloController control)
    {
        this.shelf = shelf;
        this.item = item;
        this.control = control;
    }

    /**
     *
     * @return Returns true if an item has been successfully added to the shelf and false if it has not
     * @throws InterruptedException
     */
    public Boolean call() throws InterruptedException {
        Circle itemGUI = new Circle(150, 200, 25, Color.color(Math.random(), Math.random(), Math.random()));
        System.out.println(itemGUI);
        System.out.println("<<<<");
        Runnable giveReceptionistItemRunnable = () ->{
            control.receptionistGUI.getChildren().add(itemGUI);
        };
        Platform.runLater(giveReceptionistItemRunnable);
        System.out.println(">>>>");
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
