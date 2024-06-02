package projekt_PW;

public class FixedItem {
    String adress;
    RepairWorker repairman;
    boolean waitingForRepair;

    public FixedItem(String adr, RepairWorker repairman)
    {
        waitingForRepair = true;
    }

    public void fixItem() throws InterruptedException {
        // this is the minimal amount of time that is required to fix the item
        long timeNeeded = 300;
        // this puts some randomness - simulates complications during the repair
        timeNeeded += (long) (Math.random() * 300);
        Thread.sleep(timeNeeded);
    }

    public void setRepair(String adr, RepairWorker repairman)
    {
        this.waitingForRepair = false;
        this.adress = adr;
        this.repairman = repairman;
        repairman.currentlyRepairedItem = this;
    }
}
