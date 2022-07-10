package org.example;

import java.util.*;

public class AlgorithmPrima {
    private final LinkedList<Top> tops;
    int wayLength = 0;
    private LinkedList<Top> linkedTops = new LinkedList<>();

    public AlgorithmPrima(LinkedList<Top> tops) {
        this.tops = tops;
    }

    public void getShortestWay() {
        Top top1 = tops.getFirst();//Взяли 1 вершину
        int counter = tops.size();
        //updateTops(top.getName());//"Удалили" эту вершину из списка
        Top top2 = getNextTop(top1);//Получили индекс следующей ближайшей вершины
        updateTops(top1, top2);
        linkedTops.add(top1);
        linkedTops.add(top2);
        while (counter != 1) {
            top2 = getNextTop();
            counter -= 1;
        }
        System.out.println("\nThe finish result: " + wayLength + "\n");
    }

    public void updateTops(Top top1, Top top2) {
        top1.updateTop(top2.getName());
        top2.updateTop(top1.getName());
        for(Top top:tops){
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

    public Top getNextTop() {
        LinkedList<LinkedList<Integer>> valuesForTops = new LinkedList<>();
        LinkedList<Integer> minValues = new LinkedList<>();
        HashMap<Integer, Integer> rowOfMinValue = new HashMap<>();
        for(Top top:linkedTops){
            valuesForTops.add(new LinkedList<>(top.getWaysToTops().values()));
        }
        Integer val = -1;
        int row = 0;
        int column = 0;
        int counter = 0;
        for(LinkedList<Integer> values:valuesForTops){
            counter = 0;
            for(Integer value:values){
                if(val.equals(-1)&&!value.equals(0)){
                    val = value;
                    row = counter;
                }else if(val > value&&!value.equals(0)){
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
        wayLength += Collections.min(minValues);
        row = rowOfMinValue.get(Collections.min(minValues));
        int min = 0;
        counter = 0;
        for(Integer value:minValues){
            if(value.intValue() == Collections.min(minValues).intValue()){
                min = counter;
            }
            counter+=1;
        }
        column  = Integer.parseInt(linkedTops.get(min).getName());//TODO Как посчитать строку?
        System.out.println(minValues);
        System.out.println(Collections.min(minValues)+" : x = " + column+" y = "+row);
        updateTops(tops.get(row), tops.get(column));
        linkedTops.add(tops.get(row));
        System.out.println("Вершина:"+tops.get(column).getName());
        return tops.get(column);
    }
}
