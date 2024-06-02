package projekt_PW;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args)
    {
        final int repairmenAmount = 3;
        final int maxItemCount = 100;

        ItemShelfMonitor myShelf = new ItemShelfMonitor(maxItemCount, repairmenAmount);
    }
}
