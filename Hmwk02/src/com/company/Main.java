/*CSC-254 HMWK 2
 Zachary Czarnecki
Read a document into an array so that it is printed as an array, display the average, minimum, and maximum
 of that array, and print out two more arrays filtering in only those numbers greater than the average, and those
 withing 5% of the average.
*/
package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public final static int MAXIMUM_LIST_SIZE = 20;
    public final static String FILENAME = "input3.txt";

    public static void main(String[] args) {
        double[] list = new double[MAXIMUM_LIST_SIZE];
        int n = readList(list);
        printlist(list, n);
        System.out.printf("Average is %f\n", calculateAvg(list, n));
        System.out.printf("Minimum is %f\n", findMIN(list, n));
        System.out.printf("Maximum is %f\n\n", findMAX(list, n));
        aboveAverage(list, n);
        fivePercent(list, n);
    }

    public static int readList(double[] list) {
        int n = 0;
        try {
            Scanner input = new Scanner(new File(FILENAME));
            while (input.hasNextDouble() && n < MAXIMUM_LIST_SIZE) {
                double number = input.nextDouble();
                list[n] = number;
                n++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Oops! Nuthin' there!");
            System.exit(1);
        }
        return n;
    }

    public static void printlist(double[] list, int n) {
        System.out.println("THE ARRAY");
        for (int i = 0; i < n; i++) {
            System.out.printf("[%d] %g\n", i, list[i]);
        }
        System.out.printf("\nThere are %d items in the list\n", n);
    }

    public static double calculateAvg(double[] list, int n) {
        double sum = 0;
        double average;
        for (int i = 0; i < n; i++) {
            sum += list[i];
        }
        average = sum / n;
        return average;
    }

    public static double findMIN(double[] list, int n) {
        double minsofar = Double.MAX_VALUE;
        if(n<=0){
            minsofar=0;
        }else{
            for (int i = 0; i < n; i++) {
                if (list[i] < minsofar) {
                    minsofar = list[i];
                }
            }
        }
        return minsofar;
    }

    public static double findMAX(double[] list, int n) {
        double maxsofar = Double.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (list[i] > maxsofar) {
                maxsofar = list[i];
            }
        }
        return maxsofar;
    }

    public static void aboveAverage(double[] list, int n) {
        if (n <= 0) {
            System.out.println("NOTHING!");
        } else {
            System.out.println("\nAbove Average:");
            for (int i = 0; i < n; i++) {
                if (list[i] > calculateAvg(list, n)) {
                    System.out.printf("[%d] %g\n", i, list[i]);
                }
            }
        }
    }

    public static void fivePercent(double[] list, int n) {
        if (n <= 0) {
            System.out.println("YOU GET NOTHING!");
        } else {
            System.out.println("\nWithin 5 percent:");
            for (int i = 0; i < n; i++) {
                if (Math.abs(list[i] - calculateAvg(list, n)) < calculateAvg(list, n) * 0.05) {
                    System.out.printf("[%d] %g\n", i, list[i]);
                }
            }
        }
    }
}
