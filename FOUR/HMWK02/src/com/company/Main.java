/*Zachary Czarnecki CSC 406 HMWK02
* Use ExecutorService to run 9 threads that simulate the cost of printing from 9 ports on one company router. The cost
* per character is different depending on what branch requested the print(Production, Financial, and Marketing), and
* whether or not the characters were processed physically(print) or digitally(data). The data is ran through the
* "Running" class, before being passed down to the Router class where the costs and totals for each branch and each
* process are calculated in a lock, before being passed back up to the main class. The executor is terminated, and the
* totals for characters sent to print/data, and the matching total costs are printed.*/
package com.company;
import java.io.*;
import javax.swing.*;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.lang.*; //allows the threads to be created as objects
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws Exception{
        //create Thread pool
        ExecutorService executor= Executors.newFixedThreadPool(9);
        //Production
        Router Job1=new Router("PB",1,'D',60000);
        Router Job2=new Router("PB",3,'P',100000);
        Router Job3=new Router("PB",2,'D',75000);
        //Financial threads
        Router Job4=new Router("FB",1,'P',30000);
        Router Job5=new Router("FB",2,'D',150000);
        Router Job6=new Router("FB",3,'P',89000);
        //Marketing
        Router Job7=new Router("MB",1,'P',200000);
        Router Job8=new Router( "MB",2,'D',140000);
        Router Job9=new Router("MB",3,'P',135000);

        executor.execute(new Running(Job1));
        executor.execute(new Running(Job2));
        executor.execute(new Running(Job3));
        executor.execute(new Running(Job4));
        executor.execute(new Running(Job5));
        executor.execute(new Running(Job6));
        executor.execute(new Running(Job7));
        executor.execute(new Running(Job8));
        executor.execute(new Running(Job9));
        System.out.flush();

        executor.shutdown();
        //shutdown threads
        while (!executor.isTerminated());
        //call totals
        System.out.println("What is the amount characters sent by Production? "+Router.ProductionChars);
        System.out.println("What is the process charge of characters sent by Production? "+Router.ProductionProcess);
        System.out.println("What is the amount characters sent by Financial? "+Router.FinancialChars);
        System.out.println("What is the process charge of characters sent by Financial? "+Router.FinancialProcess);
        System.out.println("What is the amount characters sent by Marketing? "+Router.MarketingChars);
        System.out.println("What is the process charge of characters sent by Marketing? "+Router.MarketingProcess);
        System.out.println("What is the total amount characters sent to print? "+Router.printChars);
        System.out.println("What is the process charge of characters sent to print? "+Router.printProcess);
        System.out.println("What is the total amount characters sent to data? "+Router.dataChars);
        System.out.println("What is the process charge of characters sent to data? "+Router.dataProcess);
        System.out.flush();

    }//end sv main

    public static  class Running implements Runnable{
        //this calls the threads
        Router network;
       /* String Tree;
        int harbor;
        char printdata;
        int counter;
        public Running (String Branch, int port, char dataprint, int charcount){
            Tree=Branch;
            harbor=port;
            printdata=dataprint;
            counter=charcount;
        }//end constructor*/
        public Running (Router x){
            network=x;
        }//end constructor
        public void run(){
            //this sends them down to get mathed
            network.Charge(1.0); //run math once
        }
    }//end Running

    public static class Router {
        String Tree;
        int harbor;
        char printdata;
        int counter;
        //create Lock
        private static Lock lock=new ReentrantLock();
        PrintWriter out1;
        public Router (String Branch, int port, char dataprint, int charcount){
            Tree=Branch;
            harbor=port;
            printdata=dataprint;
            counter=charcount;
        }//end constructor
        //total variables for end - must be public static to call in top
        public static int ProductionChars=0;
        public static double ProductionProcess=0;
        public static int FinancialChars=0;
        public static double FinancialProcess=0;
        public static int MarketingChars=0;
        public static double MarketingProcess=0;
        public static int dataChars=0;
        public static double dataProcess=0;
        public static int printChars=0;
        public static double printProcess=0;

        public void Charge (double amt){
            lock.lock();
            try {
                if (Tree == "PB") {
                    if (printdata == 'P') {
                        amt = counter * .007;
                        //totalling charge and usage
                        printProcess+=amt;
                        printChars+=counter;
                        //Individual Totals
                        ProductionProcess+=amt;
                        ProductionChars+=counter;
                    }
                    if (printdata == 'D') {
                        amt = counter * .008;
                        dataProcess+=amt;
                        dataChars+=counter;
                        //Individual Totals
                        ProductionProcess+=amt;
                        ProductionChars+=counter;
                    }
                }//end Tree If
                if (Tree == "FB") {
                    if (printdata == 'P') {
                        amt = counter * .009;
                        printProcess+=amt;
                        printChars+=counter;
                        //Individual Totals
                        FinancialProcess+=amt;
                        FinancialChars+=counter;
                    }
                    if (printdata == 'D') {
                        amt = counter * .007;
                        dataProcess+=amt;
                        dataChars+=counter;
                        //Individual Totals
                        FinancialProcess+=amt;
                        FinancialChars+=counter;
                    }
                }//end Tree If
                if (Tree == "MB") {
                    if (printdata == 'P') {
                        amt = counter * .0095;
                        printProcess+=amt;
                        printChars+=counter;
                        //Individual Totals
                        MarketingProcess+=amt;
                        MarketingChars+=counter;
                    }
                    if (printdata == 'D') {
                        amt = counter * .0082;
                        dataProcess+=amt;
                        dataChars+=counter;
                        //Individual Totals
                        MarketingProcess+=amt;
                        MarketingChars+=counter;
                    }
                }//end Tree If
                Thread.sleep(5);
            }//end try
            catch (InterruptedException ex){
                System.out.println("Trouble");
                ex.printStackTrace();
            }
            System.out.println("Branch "+Tree+" proccesed "+counter+" characters with method "+printdata+" from port #"+
            harbor+" for a total of "+amt+"cents.");
            System.out.flush();
            lock.unlock();
        }//end Charge
    }//end Router
}//end all
