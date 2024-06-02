package projekt_PW;
import java.util.ArrayDeque;
import java.util.concurrent.locks.ReentrantLock;

/**
this is a monitor class that provides synchronization over the access to the item shelf. This class provides methods
 for adding new items and for assigning given worker item to repair
 */
//This could be probably achieved using Collections.synchronizedDeque but that way it can provide more functionality
public class ItemShelfMonitor {
    final private ArrayDeque<FixedItem> shelf;
    final ReentrantLock myLock = new ReentrantLock();
    final RepairWorker[] workerPool;
    private int itemCount, maxItemCount;
    int workerAmount;
    public ItemShelfMonitor(int maxItemCount, int workerAmount)
    {
        this.itemCount = 0;
        this.shelf = new ArrayDeque<FixedItem>(maxItemCount);
        this.maxItemCount = maxItemCount;
        this.workerPool = new RepairWorker[workerAmount];
        for(int i = 0; i < workerAmount; i++)
        {
            workerPool[i] = new RepairWorker();
        }

    }

    /**
     * tries to add item to the shelf. If the shelf is already full item is not added and will be lost
     * @param item item to be added
     */
    public void addItemToShelf(FixedItem item)
    {
        myLock.lock();
        if (this.itemCount < this.maxItemCount)
        {
            shelf.add(item);
            this.itemCount++;
        }
        myLock.unlock();
    }

    public void assignWorker()
    {
        // do nothing I am tired and I'm going to sleep gn
    }
}
