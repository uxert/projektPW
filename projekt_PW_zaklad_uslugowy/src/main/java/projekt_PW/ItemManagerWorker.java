package projekt_PW;

public class ItemManagerWorker extends Thread{
    final ItemShelfMonitor shelf;

    public ItemManagerWorker(ItemShelfMonitor shelf)
    {
        this.shelf = shelf;
    }

    public void runManager() throws InterruptedException {
        int adr_count = 0;
        while (true) // will work until interrupted
        {
            FixedItem tempItem = new FixedItem("addr - " + adr_count);
            shelf.addItemToShelf(tempItem);
            adr_count++;
            Thread.sleep((long) (300 + Math.random() * 400));
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
