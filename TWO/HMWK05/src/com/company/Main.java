/*Zachary Czarnecki
HMWK05
 Take in data from txt file on Colleges in the USA  and divide on tab. Read in the School name, City, State, Latitude
 and Longtitude. Use Arguments to sort*/
package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public final static int MAXARRAY = 100;
    public final static String FILENAME = "input.txt";
    public static void main(String[] args) {
        int n = 0;
        Scanner input = null;
        States[] list= new States [MAXARRAY];
        try {
            input = new Scanner(new File(FILENAME));
            String junk = input.nextLine();
            while (input.hasNextLine() && n < MAXARRAY) {
                String line = input.nextLine();
                String[] tokens = line.split("\t");
                String name = tokens[0];
                String city = tokens[2];
                String state = tokens[3];
                String Latitude = tokens[5];
                String Longitude = tokens[6];
                if (tokens.length== 7) {
                    States section = new States(name, city, state, Latitude, Longitude);
                    list[n]=section;
                    n++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Oops! Nuthin' there!");
            System.exit(1);
        }
        printArrays(list, n);
    }
    public static void printArrays(States[]list, int n) {
        System.out.println("----------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("\t%s\n", list[i]);
        }
    }
}
