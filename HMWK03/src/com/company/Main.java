/*CSC254 HMWK03
Zachary Czarnecki
Read text file and mark array digits with down if they are equal to sum of previous digits, up if they are equal to sum
of the upcoming digits, and a box if both.*/
package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    public final static int MAXARRAY = 10;
    public final static char DASH = '-';
    public final static char BOX = '\u25a1';
    public final static char DOWN ='\u21d3';
    public final static char UP = '\u21d1';
    public final static String FILENAME = "input.txt";

    public static void main(String[] args) {
        double[] digits = new double[MAXARRAY];
        char[] symbols = new char[MAXARRAY]; // dashes
        int n; //elements in list
        n = buildArrarys(digits, symbols);

        System.out.printf("Down %c Up %c Box %c\n", DOWN, UP, BOX);

        fillSymbols(symbols, n);
        markbelowPOS(digits, symbols, n);
        markabovePOS(digits, symbols, n);
        printArrays(digits, symbols, n);
    }

    public static int buildArrarys(double[] digits, char[] symbols) {
        int n = 0;
        n = readNumbers(digits);
        fillSymbols(symbols, n);
        return n;
    }

    public static int readNumbers(double[] digits) {
        Scanner input;
        int n = 0;
        try {
            input = new Scanner(new File(FILENAME));
            while (input.hasNextDouble() && n < MAXARRAY) {
                digits[n] = input.nextDouble();
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

    public static void fillSymbols(char[] symbols, int n) {
        for (int i = 0; i < n; i++) {
            symbols[i] = DASH;
        }
    }

    public static void markbelowPOS(double[] digits, char[] symbols, int n) {//If the sum of the numbers below a given position sum up to equal the number in this position
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = i - 1; j >= 0; j--) {
                sum += digits[j];
            }
            if (digits[i] == sum) {
                symbols[i] = DOWN;
            }
        }
    }

    public static void markabovePOS(double[] digits, char[] symbols, int n) {//If the sum of the numbers above a given position sum up to equal the number in this position
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += digits[j];
            }

            if(digits[i]==sum){
                if(symbols[i] == DOWN){
                    symbols[i] = BOX;
                }else{
                    symbols[i]=UP;
                }
            }
        }
    }

    public static void printArrays(double[] digits, char[] symbols, int n) {
        System.out.println("----------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("\t%c %g\n", symbols[i], digits[i]);
        }
    }
}
