package projekt_PW;

public class FixedItem {
    String address;
    RepairWorker repairman;
    boolean waitingForRepair, alreadyRepaired;

    public FixedItem(String adr)
    {
        this.address = adr;
        alreadyRepaired = false;
        waitingForRepair = true;
    }

    public void fixItem() throws InterruptedException {
        // this is the minimal amount of time that is required to fix the item
        long timeNeeded = 300;
        // this provides some randomness - simulates complications during the repair
        timeNeeded += (long) (Math.random() * 300);
        Thread.sleep(timeNeeded);

        System.out.println("Item is now fixed, after duration of: " + timeNeeded);
        alreadyRepaired = true;
        waitingForRepair = false;
        repairman.isWaitingForRepair = false;
    }

    public void setRepair(RepairWorker repairman)
    {
        this.waitingForRepair = false;
        this.repairman = repairman;
        repairman.currentlyRepairedItem = this;
    }
}
