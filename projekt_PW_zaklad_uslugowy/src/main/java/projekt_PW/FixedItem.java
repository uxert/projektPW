package projekt_PW;

public class FixedItem {
    String address;
    TaskRepair repairman;
    boolean waitingForRepair, alreadyRepaired;
    CFG myCFG;

    public FixedItem(String adr, CFG myCFG)
    {
        this.address = adr;
        alreadyRepaired = false;
        waitingForRepair = true;
        this.myCFG = myCFG;
    }

    public void fixItem() throws InterruptedException {
        // this is the minimal amount of time that is required to fix the item
        long timeNeeded = myCFG.baseFixTime;
        // this provides some randomness - simulates complications during the repair
        timeNeeded += (long) (Math.random() * myCFG.randomFixTime);
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
