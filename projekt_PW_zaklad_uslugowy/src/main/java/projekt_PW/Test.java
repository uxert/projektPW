package projekt_PW;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public void closeStore(ArrayDeque<RepairWorker> workers)
    {

    }

    public static void main(String[] args) throws InterruptedException {
        final int repairmenAmount = 3;
        final int maxItemCount = 100;

        ItemShelfMonitor myShelf = new ItemShelfMonitor(maxItemCount, repairmenAmount);
        RepairWorker[] repairmen = new RepairWorker[repairmenAmount];

        ItemManagerWorker receptionist = new ItemManagerWorker(myShelf);

        for(int i = 0; i < repairmenAmount; i++)
        {
            repairmen[i] = new RepairWorker(myShelf);
        }
        for (int i = 0; i < repairmenAmount; i++)
        {
            repairmen[i].start();
        }
        receptionist.start();
        //this loop populates the shelf with items - for code testing purposes
        for (int i = 0; i < 10; i++)
        {
            FixedItem tempItem = new FixedItem("adres: " + i);
            myShelf.addItemToShelf(tempItem);
            TimeUnit.MILLISECONDS.sleep((long) (300+ Math.random() * 150));
        }

        receptionist.interrupt();
        for (int i = 0; i < 3; i++)
        {
            repairmen[i].keepRepairing = false; //setting this to false means, that
            //when a worked finishes his repair he will not take any new repairs
        }
        //however, when a worker is already waiting for an item to repair he will have to be interrupted
        for (int i = 0; i < 3; i++)
        {
            if (repairmen[i].isWaitingForRepair) {
                System.out.println("Interrupting worker " + i);
                repairmen[i].interrupt();
            }
        }
        receptionist.join();
        for (int i = 0; i < 3; i++)
        {
            repairmen[i].join();
        }

        System.out.println("The store was closed successfully");
    }
}
