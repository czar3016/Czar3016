/*Zachary Czarnecki HMWK07
*Test Recurssion
 *  */
package com.company;

import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.NoSuchElementException;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws Exception {
        //Scanner
        Scanner input = new Scanner(System.in);
        // Factorial calculation
        System.out.println("Factorial(12) = " + NFactorial(12) + "\n");
        System.out.println("Factorial(25) = " + NFactorial(25) + "\n");
        System.out.println("Factorial(-5) = " + NFactorial(-5) + "\n");
        //Fibonacci Calculation
        System.out.println("Fibonacchi(5) = " + Fib(5) + "\n");
        System.out.println("Fibonacchi(12) = " + Fib(12) + "\n");
        System.out.println("Fibonacchi(1) = " + Fib(1) + "\n");
        System.out.println("Fibonacchi(8) = " + Fib(8) + "\n");
        //Arrays for Selection Sorting and Binary Search
        Integer[] ivalue = {13, -8, 29, 15, 75, -88, 19, 21, 17, 85, 12};
        Double[] dvalue = {14.3, 29.2, 55.2, -17.8, 42.1, -88.3, 94.2, 33.5, -12.5};
        MyRecursiveMgr<Integer> myInts = new MyRecursiveMgr<Integer>();
        MyRecursiveMgr<Double> myDoubs = new MyRecursiveMgr<Double>();
        List<String> stockList = new ArrayList<String>();
        //add numbers to Generic
        for (int i = 0; i <= 10; i++) myInts.setvalue(ivalue[i]);
        for (int i = 0; i <= 8; i++) myDoubs.setvalue(dvalue[i]);
        //Sorting Logic
        myInts.SelectionSort(10);
        System.out.println("\nThis is ivalue Sorted.");
        for (int i = 0; i <= 10; i++) System.out.println("[" + i + "]" + myInts.getvalue(i));
        myDoubs.SelectionSort(8);
        System.out.println("\nThis is dvalue Sorted.");
        for (int i = 0; i <= 8; i++) System.out.println("[" + i + "]" + myDoubs.getvalue(i));

        //Binary Search - Recursive call to generic
        if(myInts.BinarySearch(myInts, 21, 0, 10)>0)
            System.out.println("\nFound 21 at " + myInts.BinarySearch(myInts, 21, 0, 10));
        else//so print null instead of -1 if not there
            System.out.println("\n21 not found");
        if(myInts.BinarySearch(myInts, 75, 0, 10)>0)
            System.out.println("\nFound 75 at " + myInts.BinarySearch(myInts, 75, 0, 10));
         else
            System.out.println("\n75 not found");
        if(myInts.BinarySearch(myInts, -88, 0, 10)>0)
            System.out.println("\nFound -88 at " + myInts.BinarySearch(myInts, -88, 0, 10));
         else
            System.out.println("\n-88 not found");
        if(myInts.BinarySearch(myInts, 21, 0, 10)>0)
            System.out.println("\nFound 85 at " + myInts.BinarySearch(myInts, 85, 0, 10));
         else
            System.out.println("\n85 not found");
        if(myInts.BinarySearch(myInts, 49, 0, 10)>0)
            System.out.println("\nFound 49 at " + myInts.BinarySearch(myInts, 49, 0, 10));
        else
            System.out.println("\n49 not found");
        if(myDoubs.BinarySearch(myDoubs, 55.2, 0, 8)>0)
            System.out.println("\nFound 55.2 at " + myDoubs.BinarySearch(myDoubs, 55.2, 0, 8));
        else
            System.out.println("\n55.2 not found");
        if(myDoubs.BinarySearch(myDoubs, -17.8, 0, 8)>0)
            System.out.println("\nFound -17.8 at " + myDoubs.BinarySearch(myDoubs, -17.8, 0, 8));
        else
            System.out.println("\n-17.8 not found");
        if(myDoubs.BinarySearch(myDoubs, 94.2, 0, 8)>0)
            System.out.println("\nFound 94.2 at " + myDoubs.BinarySearch(myDoubs, 94.2, 0, 8));
        else
            System.out.println("\n94.2 not found");
        if(myDoubs.BinarySearch(myDoubs, 25.3, 0, 8)>0)
            System.out.println("\nFound 25.3 at " + myDoubs.BinarySearch(myDoubs, 25.3, 0, 8));
        else
            System.out.println("\n25.3 not found");
    }

    //Factorial Method
    public static int NFactorial(int N) {
        //System.out.println("Calculating NFactorial(" + N + ")");
        if (N == 0) {
            return 0;
        } else if (N <= 0) {
            return (NFactorial(N + 1) + N);
        } else {
            return (NFactorial(N - 1) + N);
        }
    }

    //Fibonacci Method
    public static int Fib(int N) {
        int fibIM1, fibIM2, sum;
        //System.out.println("Calculating Fib(" + N + ")");
        if (N == 0) {
            //System.out.println("Fib(0) is 0");
            return 0;
        } else if (N == 1) {
            //System.out.println("Fib(1) is 1");
            return 1;
        } else {
            fibIM1 = Fib(N - 1);
            fibIM2 = Fib(N - 2);
            sum = fibIM1 + fibIM2;
            //System.out.println("Returning Fib for " + N + " " + (N - 1) + " value " + fibIM1 + " Fib " + (N - 2) + " value " + fibIM2 +
                    //"return " + sum);
            return sum;
        }
    }//end Fibonacchi
}//end Main

class MyRecursiveMgr<T extends Comparable> {
    protected ArrayList<T> values = new ArrayList<T>();
    protected int mcount;
    protected T max;

    public MyRecursiveMgr() {
        //constructor
        mcount = 0;
    }//end constructor

    public int setvalue(T x) {
        //place stuff in Generic array
        values.add(mcount++, x);
        return mcount;
    }//end setvalue

    public T getvalue(int i) {
        if (i <= mcount) return values.get(i);
        else
            return values.get(0);
    }//end getvalue

    public void SelectionSort(int high) {
        //recursive Selection sort
        //System.out.println("In SelectSort with value " + high);
        //stopping condition
        if (high > 0) {
            int indexofMax = 0;
            max = values.get(0);
            for (int i = 1; i <= high; i++) {
                if ((values.get(i)).compareTo(max) == 1) {
                    //record index of max values
                    max = values.get(i);
                    indexofMax = i;
                }
            }//end of For
            values.set(indexofMax, values.get(high));
            values.set(high, max);
            //recursively call sort one element shorter
            SelectionSort(high - 1);
        }//end if
        else return; //end condition
        return;
    }//end sort

    public static <T extends Comparable> int BinarySearch(MyRecursiveMgr<T> A, T value, int low, int high){
        //True recursive value call on A
        int mid = low + ((high - low) / 2);
        if (high < low)
            return -1; // not found
        if (A.getvalue(mid).compareTo(value) > 0)
            //getvalue calls the Manager Array position recursivly - this is go down logic
            return BinarySearch(A, value, low, mid - 1);
        else if (A.getvalue(mid).compareTo(value) < 0)
            //- this is go up logic
            return BinarySearch(A, value, mid + 1, high);
        else
            return mid; //en condition
    }
}//end Generic
