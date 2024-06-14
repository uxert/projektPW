package projekt_PW;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.shape.Circle;

import java.util.concurrent.TimeUnit;

public class TaskRepair extends Task<Void> {
    final private ItemShelfMonitor shelf;
    final private HelloController control;
    FixedItem item;
    Circle itemGUI;
    Integer workerNo;
    public TaskRepair(ItemShelfMonitor shelf, HelloController control, FixedItem item) {
        this.shelf = shelf;
        this.control = control;
        this.item = item;
    }

    public Void call() throws InterruptedException
    {
        workerNo = control.availableRepairmen.removeFirst();
        shelf.assignItem(this);

        Runnable removeItemFromShelf = () -> {
            itemGUI = control.itemsOnShelf.removeFirst();
            control.getShelfGUI().getChildren().removeFirst();
        };
        Platform.runLater(removeItemFromShelf);

        Runnable giveItemToRepairman = ()  -> control.repairmenGUI[workerNo].getChildren().add(itemGUI);
        Platform.runLater(giveItemToRepairman);
        item.fixItem();

        shelf.sendRepairedItem(this, 500);
        TimeUnit.MILLISECONDS.sleep(500);
        Runnable removeItemFromRepairman = () -> control.repairmenGUI[workerNo].getChildren().removeFirst();
        Platform.runLater(removeItemFromRepairman);
        control.availableRepairmen.add(workerNo);
        return null;
    }
}
