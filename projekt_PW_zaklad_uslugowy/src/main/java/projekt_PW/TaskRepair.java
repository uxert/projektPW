package projekt_PW;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

public class TaskRepair extends Task<Void> {
    final private ItemShelfMonitor shelf;
    final private HelloController control;
    FixedItem item;
    public TaskRepair(ItemShelfMonitor shelf, HelloController control, FixedItem item) {
        this.shelf = shelf;
        this.control = control;
        this.item = item;
    }

    public Void call() throws InterruptedException
    {
        shelf.assignItem(this);
        item.fixItem();
        shelf.sendRepairedItem(this);
        TimeUnit.MILLISECONDS.sleep(500);

        Runnable removeItemRunnable = () -> {
            control.itemsOnShelf.removeFirst();
            control.getShelfGUI().getChildren().removeFirst();
        };
        Platform.runLater(removeItemRunnable);

        return null;
    }
}
