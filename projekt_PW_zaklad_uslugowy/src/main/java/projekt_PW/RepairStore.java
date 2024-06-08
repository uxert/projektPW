package projekt_PW;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class RepairStore extends Thread{

    private  HelloController frontControl;

    ItemManagerWorker receptionist;
    public RepairStore(HelloController cont)
    {
        frontControl = cont;
    }

    public void closeStore(ArrayDeque<RepairWorker> workers)
    {

    }

    public void run(){
        frontControl.isTheStoreRunning = true;
        final int repairmenAmount = 3;
        final int maxItemCount = 100;

        ItemShelfMonitor myShelf = new ItemShelfMonitor(maxItemCount, repairmenAmount, frontControl);
        RepairWorker[] repairmen = new RepairWorker[repairmenAmount];

        receptionist = new ItemManagerWorker(myShelf);

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
        for (int i = 0; i < 50; i++)
        {
//            FixedItem tempItem = new FixedItem("adres: " + i);
//            myShelf.addItemToShelf(tempItem);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (300+ Math.random() * 150));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
        try {
            receptionist.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 3; i++)
        {
            try {
                repairmen[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("------------The store was closed successfully");
        frontControl.isTheStoreRunning = false;
    }
}
