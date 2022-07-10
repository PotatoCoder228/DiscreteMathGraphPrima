package org.example;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class AlgorithmPrima {
    private final LinkedList<Top> tops;
    int wayLength = 0;
    private final LinkedList<Top> linkedTops = new LinkedList<>();

    public AlgorithmPrima(LinkedList<Top> tops) {
        this.tops = tops;
    }

    public void getShortestWay() {
        Top top1 = tops.getFirst();//Взяли 1 вершину
        for (Top top : tops) {
            top.updateTop(top1.getName());
        }
        linkedTops.add(top1);
        Top top2 = getNextTop(top1);//Получили индекс следующей ближайшей вершины
        updateTops(top1, top2);
        linkedTops.add(top2);
        while (tops.size() != linkedTops.size()) {
            getNextTop();//получаем следующую вершину, ближайшую к ядру
        }
        System.out.println("\nThe finish result: " + wayLength + "\n");
    }

    public void updateTops(Top top1, Top top2) {
        for (Top top : tops) {
            top.updateTop(top2.getName());
        }
        for (Top top : tops) {
            top.updateTop(top1.getName());
        }
        for (Top top : tops) {
            System.out.print("Top's name: " + top2.getName());
            System.out.println(top.getWaysToTops().values());
        }
    }

    public Top getNextTop(Top top) {
        Collection<Integer> collection = top.getWaysToTops().values();
        LinkedList<Integer> values = new LinkedList<>(collection);
        Integer value = 0;
        int index = 0;
        for (Integer val : values) {
            if (value.equals(0) && !val.equals(0)) {
                value = val;
                index = values.indexOf(val);
            } else {
                if (value > val && !val.equals(0)) {
                    index = values.indexOf(val);
                    value = val;
                }
            }
        }
        System.out.println(value);
        wayLength += value;
        return tops.get(index);
    }

    public void getNextTop() {
        LinkedList<LinkedList<Integer>> valuesForTops = new LinkedList<>();
        LinkedList<Integer> minValues = new LinkedList<>();
        HashMap<Integer, Integer> rowOfMinValue = new HashMap<>();
        for (Top top : linkedTops) {
            valuesForTops.add(new LinkedList<>(top.getWaysToTops().values()));
        }
        Integer val = -1;
        int row = 0;
        int column;
        int counter;
        for (LinkedList<Integer> values : valuesForTops) {
            counter = 0;
            for (Integer value : values) {
                if (val.equals(-1) && !value.equals(0)) {
                    val = value;
                    row = counter;
                } else if (val > value && !value.equals(0)) {
                    val = value;
                    row = counter;
                }
                counter += 1;
            }
            minValues.add(val);
            rowOfMinValue.put(val, row);
            val = -1;
            row = 0;
        }
        row = rowOfMinValue.get(Collections.min(minValues));
        int min = 0;
        counter = 0;
        for (Integer value : minValues) {
            if (value.intValue() == Collections.min(minValues).intValue()) {
                min = counter;
            }
            counter += 1;
        }
        column = Integer.parseInt(linkedTops.get(min).getName());
        wayLength += Collections.min(minValues);
        System.out.println(minValues);
        updateTops(tops.get(row), tops.get(column));
        System.out.println(Collections.min(minValues) + " : x = " + column + " y = " + row);
        linkedTops.add(tops.get(row));
        System.out.println("----------------------------------------------------------");
    }
}
