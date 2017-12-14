/*HMWK04
Zachary Czarnecki
Read in sorted String array from a document while skiping lines less than 2. Array more than 20 have the first and
last 10 read only*/
package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    public final static int MAXARRAY = 2000;
    public final static String FILENAME = "holidays.txt";

    public static void main(String[] args) {
        String[] words = new String[MAXARRAY];
        int n; //elements in list
        n = buildArrarys(words);
        selectionSort(words, n);
        printArrays(words, n);
    }

    public static int buildArrarys(String[] words) {
        int n = 0;
        n = readWords(words);
        return n;
    }

    public static void selectionSort(String[] words, int n) {
        for (int i = 0; i < n - 1; i++) {
            String currentMin = words[i];
            int currentMinIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (currentMin.compareTo(words[j]) > 0) {
                    currentMin = words[j];
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {
                words[currentMinIndex] = words[i];
                words[i] = currentMin;
            }
        }
    }

    public static int readWords(String[] words) {
        Scanner input;
        int n = 0;
        try {
            input = new Scanner(new File(FILENAME));
            while (input.hasNext() && n < MAXARRAY) {
                String WERD = input.nextLine();
                if (WERD.length() > 2) {
                    words[n] = WERD;
                    n++;
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Oops! Nuthin' there!");
            System.exit(1);
        }
        return n;
    }

    public static void printArrays(String[] words, int n) {
        System.out.println("----------------------------");
        if(n> 20){
            for (int i = 0; i < 10; i++) {
                System.out.printf("\t %s\n", words[i]);
                System.out.println("----------------------------");
            }
            System.out.println(".........");
            for (int i=n-10; i<n; i++) {
                System.out.printf("\t %s\n", words[i]);
                System.out.println("----------------------------");
            }
        }else{
        for (int i = 0; i < n; i++) {
            System.out.printf("\t %s\n", words[i]);
            System.out.println("----------------------------");
        }
    }
}
}
