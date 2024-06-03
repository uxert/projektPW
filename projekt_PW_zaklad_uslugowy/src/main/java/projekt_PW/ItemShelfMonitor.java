package projekt_PW;
import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
this is a monitor class that provides synchronization over the access to the item shelf. This class provides methods
 for adding new items and for assigning given worker item to repair
 */
//This could be probably achieved using Collections.synchronizedDeque but that way it can provide more functionality
public class ItemShelfMonitor {
    private final ArrayDeque<FixedItem> shelf;
    final ReentrantLock myLock = new ReentrantLock(true);
    Condition noItems = myLock.newCondition();

    private final int maxItemCount;
    private int itemCount;

    private int maxWorkerAmount;
    public ItemShelfMonitor(int maxItemCount, int workerAmount)
    {
        this.itemCount = 0; // keeps track of how many items are in the store
        //this is not the same as number of items waiting on the shelf!
        //there can be maxItemCount items in the whole store, not just laying idle on the shelf

        this.shelf = new ArrayDeque<FixedItem>(maxItemCount);
        this.maxItemCount = maxItemCount;
        this.maxWorkerAmount = workerAmount;
    }

    /**
     * tries to add item to the shelf. If the shelf is already full item is not added and will be lost
     * @param item item to be added
     */
    public void addItemToShelf(FixedItem item)
    {
        myLock.lock();
        try {
            if (this.itemCount < this.maxItemCount)
            {
                shelf.add(item);
                this.itemCount++;
                System.out.println("Item with address: " + item.address + " is added to the shelf");
                noItems.signal(); /*signals that there is an item to repair (in case
            any worker is already waiting for it) */
            }
        }
        finally {
            myLock.unlock();
        }
    }

    /**
     * this function gives a repairman an item to repair.
     *  If there is nothing to repair it makes the repairman await on condition
     * @param repairman reference to a FREE worker looking for an item to repair
     */
    public void assignItem(RepairWorker repairman) throws InterruptedException {
        myLock.lock();
        try {
            if (this.shelf.isEmpty())
                noItems.await();
            FixedItem tempItem = shelf.removeFirst();
            repairman.currentlyRepairedItem = tempItem;
            repairman.isWaitingForRepair = false;
            tempItem.setRepair(repairman);
        } finally {
            myLock.unlock();
        }
    }

    /**
     * this function removes item currently held by given RepairWorker and removes it from evidence
     * @param repairman reference to repairman holding an ALREADY REPAIRED item
     */
    public void sendRepairedItem(RepairWorker repairman)
    {
        myLock.lock();
        try {
            FixedItem tempItem = repairman.currentlyRepairedItem;
            if (tempItem.alreadyRepaired)
            {
                itemCount--;
                System.out.println("Item successfully repaired, sending by worker " + Thread.currentThread().getName());
                tempItem.repairman = null;
                repairman.currentlyRepairedItem = null;
                repairman.isWaitingForRepair = true;
            }
            else {
                System.out.println("This item is not yet repaired, cannot send!");
            }
        } finally {
            myLock.unlock();
        }
    }
}
