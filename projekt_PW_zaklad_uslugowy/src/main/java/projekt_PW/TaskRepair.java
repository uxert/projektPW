package projekt_PW;

import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

public class TaskRepair extends Task<Void> {
    private ItemShelfMonitor shelf;
    private HelloController control;
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
        return null;
    }
}
