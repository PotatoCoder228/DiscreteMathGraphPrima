package org.example;

import java.util.HashMap;

/**
 * Class of top
 */

public class Top {
    private String name;
    private HashMap<String, Integer> waysToTops = new HashMap<>();

    public Top(int graphCounter, int[] ways) {
        name = String.valueOf(graphCounter);
        int counter = 0;
        for (Integer way : ways) {
            waysToTops.put(String.valueOf(counter), way);
            counter += 1;
        }
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getWaysToTops() {
        return waysToTops;
    }

    public void updateTop(String string) {
        waysToTops.put(string, 0);
    }
}
