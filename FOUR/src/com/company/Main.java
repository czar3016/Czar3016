/*Zachary Czarnecki CSC 406: HMWK 1 - Threads
* Use threads to simulate the data process of an old, single processor with a primative GUI, word processor, a Data
* Storage (DS) Device, and all hooked up to a printer. The GUI polls every 2 seconds. The Word Processor runs for 10
* cycles and should then yield to the DS, which retrieves 20 and leaves, while the Printer runs on intervals of 60
* characters. Two outpust are provided: one where all 4 threads have NORM_PRIORITY, and another output where GUI reads
* out last. I set GUI ito MIN, the Word Processor to MAX so it print all out first, and left Data Storage and Printer
* to NORM.*/
package com.company;
import java.io.*;
import javax.swing.*;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.lang.*; //allows the threads to be created as objects
public class Main {

    public static void main(String[] args) throws Exception {
	    Runnable printGUI=new PrintGUI(600);
        Runnable printWP=new PrintWP('A', 1000);
        Runnable printDS=new PrintDS(2500);
        Runnable printR=new PrintR(3600);

        //the Threads
        Thread thread1=new Thread(printGUI);
        Thread thread2=new Thread(printWP);
        Thread thread3=new Thread(printDS);
        Thread thread4=new Thread(printR);

        //priorities - the CSE's recommendation
        /*thread1.setPriority(Thread.NORM_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.NORM_PRIORITY);
        thread4.setPriority(Thread.NORM_PRIORITY);*/

        //My suggestions
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(Thread.NORM_PRIORITY);
        thread4.setPriority(Thread.NORM_PRIORITY);
        //starting threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
class PrintGUI implements Runnable{
    private int times;//this is the number of times to repeat the print
    public PrintGUI(int t){
        times=t;
    }
    //every task object must have a run method to override the system's own.
    public void run(){
        for(int i=1; i<=times; i++){
            if (i%1==0){
                System.out.print("GUI Poll");
                System.out.println("\n"); //add extra processing time
                Thread.yield();
            }
        }//end for
    }//end of run
}//end of  class

class PrintWP implements Runnable{
    private char charToPrint;
    private int times;//this is the number of times to repeat the print
    public PrintWP(char c, int t){
        charToPrint=c;
        times=t;
    }
    //every task object must have a run meth to override the system's own.
    public void run(){
        for(int i=1; i<=times; i++){
            if (i%10==0){ //process of 10
                System.out.println("WP"+charToPrint+"\n"); //skip a line
                Thread.yield();
            }
        }//end for
    }//end of run
}//end of  class

class PrintDS implements Runnable{
    private int times;//this is the number of times to repeat the print
    public PrintDS(int t){
        times=t;
    }
    //every task object must have a run meth to override the system's own.
    public void run(){
        for(int i=1; i<=times; i++){
            if (i%20==0){ //increments of 20
                System.out.println("DS"+i);
                Thread.yield();
            }
        }//end for
    }//end of run
}//end of class

class PrintR implements Runnable{
    private int times;//this is the number of times to repeat the print
    int n=1;
    public PrintR(int t){
        times=t;
    }
    //every task object must have a run meth to override the system's own.
    public void run(){
        for(int i=1; i<=times; i++){
            //simulated character count
            int j= i-60;
            int k= j+60;
            //+/-60 is to compensate for starting at 0
            if (i%60==0){ //increments of 60
                System.out.println("Print Line "+n+" characters "+j+" thru "+ k);
                n++;
                Thread.yield();
            }
        }//end for
    }//end of run
}//end of class