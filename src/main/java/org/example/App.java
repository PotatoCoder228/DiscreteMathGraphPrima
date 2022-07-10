package org.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * App for search the shortest way between tops of graph
 */

public class App {
    private static final LinkedList<Top> tops = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("\nWelcome in the App,\nfor search the shortest way between tops(1 top <= 2 ways).");
        System.out.println("ATTENTION! If you enter incorrect data, a app give you wrong result!!!");
        try {
            System.out.print("\nEnter the number of tops:");
            Scanner scanner = new Scanner(System.in);
            int numberOfTops = Integer.parseInt(scanner.nextLine());
            if (numberOfTops == 0) {
                throw new NullPointerException();
            }
            TopsFactory factory = new TopsFactory();
            int counter = 0;
            while (numberOfTops != factory.getNumberOfTops()) {
                System.out.print("Enter ways value for " + counter + " in format \"top0,top1,...\":");
                tops.add(factory.generateTop(scanner.nextLine()));
                counter += 1;
            }
            AlgorithmPrima realisation = new AlgorithmPrima(tops);
            realisation.getShortestWay();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Oops, something went wrong. The app is shutting down...");
        } catch (NumberFormatException e) {
            System.out.println("Oops, you inputted is not number. The app is shutting down...");
        } catch (NoSuchElementException e) {
            System.out.println("I'm sorry, I'm stupid and can't handle ctrl+d. The app is shutting down...");
        }
    }
}
