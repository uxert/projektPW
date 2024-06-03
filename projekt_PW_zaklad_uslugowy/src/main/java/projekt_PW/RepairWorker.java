package projekt_PW;

public class RepairWorker extends Thread{
    public FixedItem currentlyRepairedItem;
    public boolean keepRepairing; // this variable is used to gently end repairman work before closing
    //the shop without interrupting him
    public boolean isWaitingForRepair;
    public final ItemShelfMonitor shelf;

    public RepairWorker(ItemShelfMonitor shelf)
    {
        this.shelf = shelf;
        this.keepRepairing = true;
        this.isWaitingForRepair = true;
    }
    public void run()
    {
        System.out.printf("Repairman with name %s begins his work\n", Thread.currentThread().getName());
        while(this.keepRepairing)
        {
            try {
                this.shelf.assignItem(this);
            } catch (InterruptedException e) {
                //if the thread was interrupted while waiting for new repair it can end its work instantly
                System.out.printf("Repairman with name %s has to end his work\n", Thread.currentThread().getName());
                return;
            }
            try {
                this.currentlyRepairedItem.fixItem();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.shelf.sendRepairedItem(this);
        }
        System.out.printf("Repairman with name: %s ends work\n",Thread.currentThread().getName());
    }

}
