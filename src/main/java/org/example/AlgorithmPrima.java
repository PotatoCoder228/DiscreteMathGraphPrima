package org.example;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class AlgorithmPrima {
    private final LinkedList<Top> tops;
    private ArrayDeque<Top> array = new ArrayDeque<>();
    int wayLength = 0;

    public AlgorithmPrima(LinkedList<Top> tops) {
        this.tops = tops;
    }

    public void getShortestWay() {
        Top top = tops.getFirst();//Взяли 1 вершину
        int counter = tops.size();
        array.addLast(top);//Добавили в очередь для дальнейшего построения\
        updateTops(top.getName());//"Удалили" эту вершину из списка
        top = getNextTop(top);//Получили индекс следующей ближайшей вершины
        array.addLast(top);
        updateTops(top.getName());
        while (counter != 2) {
            top = getNextTop(array.getFirst(), array.getLast());
            updateTops(top.getName());
            counter -= 1;
        }
        System.out.println("\nThe finish result: " + wayLength + "\n");
        Iterator<Top> iterator = array.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getName() + "-");
        }
    }

    public void updateTops(String string) {
        for (Top top : tops) {
            top.updateTop(string);
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

    public Top getNextTop(Top top1, Top top2) {
        LinkedList<Integer> values1 = new LinkedList<>(top1.getWaysToTops().values());
        LinkedList<Integer> values2 = new LinkedList<>(top2.getWaysToTops().values());
        Integer value1 = 0;
        Integer value2 = 0;
        for (Integer val : values1) {
            if (value1.equals(0) && !val.equals(0)) {
                value1 = val;
            } else if (!val.equals(0)) {
                if (value1 > val) {
                    value1 = val;
                }
            }
        }
        for (Integer val : values2) {
            if (value2.equals(0) && !val.equals(0)) {
                value2 = val;
            } else if (!val.equals(0)) {
                if (value2 > val) {
                    value2 = val;
                }
            }
        }
        Top top;
        if (value1 <= value2) {
            System.out.println(value1);
            wayLength += value1;
            top = tops.get(values1.indexOf(value1));
            array.addFirst(top);
            return top;
        } else {
            System.out.println(value2);
            wayLength += value2;
            top = tops.get(values2.indexOf(value2));
            array.addLast(top);
            return top;
        }
    }
}
