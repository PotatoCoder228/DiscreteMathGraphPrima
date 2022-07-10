package org.example;

/**
 * Factory of objects for graphs
 */

public class TopsFactory {
    private int graphCounter = 0;

    public Top generateTop(String string) {
        int[] ways = parseWays(string);
        Top top = new Top(graphCounter, ways);
        graphCounter += 1;
        return top;
    }

    public int getNumberOfTops() {
        return graphCounter;
    }

    public int[] parseWays(String string) {
        String[] strWays = string.split(",");
        int[] intWays = new int[strWays.length];
        for (int i = 0; i < strWays.length; i++) {
            intWays[i] = Integer.parseInt(strWays[i]);
        }
        return intWays;
    }
}