package projekt_PW;

import javafx.concurrent.Task;

public class FixedItem {
    String address;
    TaskRepair repairman;
    boolean waitingForRepair, alreadyRepaired;

    public FixedItem(String adr)
    {
        this.address = adr;
        alreadyRepaired = false;
        waitingForRepair = true;
    }

    public void fixItem() throws InterruptedException {
        // this is the minimal amount of time that is required to fix the item
        long timeNeeded = 1000;
        // this provides some randomness - simulates complications during the repair
        timeNeeded += (long) (Math.random() * 1500);
        Thread.sleep(timeNeeded);

        System.out.println("Item is now fixed, after duration of: " + timeNeeded);
        alreadyRepaired = true;
        waitingForRepair = false;
    }

    public void setRepair(TaskRepair repairman)
    {
        this.waitingForRepair = false;
        this.repairman = repairman;
        repairman.item = this;
    }
}
