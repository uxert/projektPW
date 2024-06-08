package projekt_PW;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ItemManagerWorker extends Thread{
    int addressCounter = 0; // this will be used to give every item a unique address.
    //used only for "student-debugging" purposes, for GUI app it does not matter what address does an item have
    boolean isThereSpace;
    final ItemShelfMonitor shelf;
    final BlockingQueue<FixedItem> clientQueue = new LinkedBlockingQueue<>();
    public ItemManagerWorker(ItemShelfMonitor shelf)
    {
        this.shelf = shelf;
    }


    public void runManager() throws InterruptedException {
        int adr_count = 0;
        while (true) // will work until interrupted
        {
//            FixedItem tempItem = new FixedItem("addr - " + adr_count);
//            shelf.addItemToShelf(tempItem);
//            adr_count++;
            if (!clientQueue.isEmpty())
            {

                FixedItem temp = clientQueue.take();
                temp.address = "Address: " + addressCounter;
                Thread.sleep((long) (300 + Math.random()*200));
                addressCounter++;
                shelf.addItemToShelf(temp);
            }
            Thread.sleep((long) (100 + Math.random() * 200));
        }
    }
    public void run()
    {
        try {
            runManager();
        } catch (InterruptedException e) {
            System.out.println("Receptionist ends his work");
            return;
        }
    }
}
