/* HMWK08
Zachary Czarnecki
Create a Pancake class to read flavor and calories into an array and arraylist. Print the first two array objects in
order, and print a sorted arraylist.
*/
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public final static String FILENAME = "input.txt";
    public static void main(String[] args) {
        int n = 0;
        Scanner input;
        Pancake[] list= new Pancake[4];
        ArrayList<Pancake> breakfest=new ArrayList<>();
        try {
            input = new Scanner(new File(FILENAME));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                if (tokens.length==2) {
                    String flavor = tokens[0];
                    int calories = Integer.parseInt(tokens[1]);
                    Pancake section = new Pancake(flavor, calories);
                    list[n]=section;
                    n++;
                    breakfest.add(section);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Oops! Nuthin' there!");
            System.exit(1);
        }
        Pancake a=list[0];
        Pancake b=list[1];
        if (a.compareTo(b)<0) {
            System.out.println(a);
            System.out.println(b);
        }
        else {
            System.out.println(b);
            System.out.println(a);
        }
        Collections.sort(breakfest);
        System.out.println(breakfest);
    }

}
