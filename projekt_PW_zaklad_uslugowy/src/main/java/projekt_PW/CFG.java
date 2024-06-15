package projekt_PW;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class CFG {
    private String filename;
    private HashMap<String, Integer> params;

    public HashMap<String, Integer> getParams() {
        return (HashMap<String, Integer>) Collections.unmodifiableMap(params);
    }

    private LinkedList<String> paramNames;
    public CFG(String filename)
    {
        this.filename = filename;
        params = new HashMap<>();
        paramNames = new LinkedList<>();
        paramNames.add("Pick from the waiting queue time");
        paramNames.add("Give an address time");
        paramNames.add("Move item to shelf animation time");
        paramNames.add("Fix item minimal time");
        paramNames.add("Fix item additional complications time");
        paramNames.add("Send item animation time");
        paramNames.add("Shelf max capacity");

        System.out.println("What do you want to do?");
        System.out.println("L - load existing parameters");
        System.out.println("S - set parameters");

    }



}
