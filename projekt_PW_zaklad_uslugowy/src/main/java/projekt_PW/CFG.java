package projekt_PW;


import java.util.Properties;

public class CFG {

    public int maxItemCount = 100;
    public int addToShelfTime = 250;
    public int baseFixTime = 2000;
    public int randomFixTime = 1000;
    public int baseSendTime = 500;

    public void readAnimationTimes(Properties p)
    {
        System.out.println("-------Reading from properties file...");

        maxItemCount = Integer.parseInt(p.getProperty("maxItemCount", "100"));
        addToShelfTime = Integer.parseInt(p.getProperty("addToShelfTime", "250"));
        baseFixTime = Integer.parseInt(p.getProperty("baseFixTime", "2000"));
        randomFixTime = Integer.parseInt(p.getProperty("randomFixTime", "1000"));
        baseSendTime = Integer.parseInt(p.getProperty("baseSendTime", "500"));
    }

}
