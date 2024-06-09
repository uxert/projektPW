package projekt_PW;

import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

public class TaskAddOnShelf extends Task<Boolean> {

    private ItemShelfMonitor shelf;
    private FixedItem item;
    public TaskAddOnShelf(ItemShelfMonitor shelf, FixedItem item)
    {
        this.shelf = shelf;
        this.item = item;
    }

    /**
     *
     * @return Returns true if an item has been successfully added to the shelf and false if it has not
     * @throws InterruptedException
     */
    public Boolean call() throws InterruptedException {
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
