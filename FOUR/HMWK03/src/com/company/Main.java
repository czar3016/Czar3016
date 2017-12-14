/*Zachary Czarnecki CSC-406 HMWK03
/*Two threads run through two different runnable classes; Add contains three through arrays that generate the fill of
two output arrays, Dstore(int) and Cstore (String), each time with certain amounts of a set of two variables. The
Delete class deletes entries from both arrays based on the value of Cstore. Both of these are called through the Buffer
class with the actual functions to storedata, cleardata, and printdata (for the sake of echo printing).
An switch statement is used to divide the array into thirds*/
package com.company;

import java.io.*;
import javax.swing.*;
import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.lang.*; //allows the threads to be created as objects
import java.lang.String;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //create and launch Buffer
        Buffer Buf1 = new Buffer();
        //add and delete threads
        Add tadd = new Add(Buf1);
        Delete tdelete = new Delete(Buf1);

        executor.execute(tadd);
        executor.execute(tdelete);
        System.out.println("Thread add and delete created and launched");
        //closings
        System.out.flush();
        executor.shutdown();
        while (!executor.isTerminated()) ;
        System.out.println("What is in the Buffer?");
        Buf1.printdata(); //???
        System.out.flush();
    }//end of main

    public static class Buffer {
        private int[] Dstore;
        private String[] Cstore;
        private int fill;
        private int fillP;
        private int fillF;
        private int fillM;
        private ArrayList<String> list = new ArrayList<String>(); //need this for it's contains function
        private static Lock lockstrclr = new ReentrantLock();//for objects in this class
        private static Condition infostorage = lockstrclr.newCondition();//allows a condition to be applied to the Lock

        public Buffer() {
            fill = 0;
            fillP = 0;
            fillF = 10;
            fillM = 20;
            //this will offset the storage
            //set the buffer storage
            Dstore = new int[30];
            Cstore = new String[30];
            //initialize to zero
            for (int i = 0; i < Dstore.length; i++) {
                Dstore[i] = 0;
                Cstore[i] = "null";
            }
        }//end constructor

        public int getFill() {
            return fill;
        }

        public void printdata() {
            //list the storage array
            for (int i = 0; i < Dstore.length; i++) {
                System.out.println("Dstore[" + i + "]=" + Dstore[i] + "  Cstore[" + i + "]=" + Cstore[i]);
                System.out.flush();
            }
        }//end printdata

        public void storedata(String router, int amt, int value) { //call names here from bottom run classes
            lockstrclr.lock();//lock acquired
            char first=router.charAt(0);
            try {
                switch (first) {
                    case 'P':
                        //while ((fillP + amt - 1) > 10) infostorage.await();//wait till there's room
                        for (int i = fillP; i <= fillP + amt-1; i++) {
                            Cstore[i] = router;
                            Dstore[i] = value;
                            list.add(Cstore[i]);
                            fill++;
                         }fillP = fillP + amt;//point to next spot
                        break;
                    case 'F':
                        while ((fillF + amt - 1 ) > 19) infostorage.await();//wait till there's room
                        for (int i = fillF; i <= fillF + amt - 1; i++) {
                            Cstore[i] = router;
                            Dstore[i] = value;
                            list.add(Cstore[i]);
                            fill++;
                        }fillF = fillF + amt;//point to next spot
                        break;
                    case 'M':
                        while ((fillM + amt - 1) > 29) infostorage.await();//wait till there's room
                        for (int i = fillM; i <= fillM + amt - 1; i++) {
                            Cstore[i] = router;
                            Dstore[i] = value;
                            list.add(Cstore[i]);
                            fill++;
                        }fillM = fillM+ amt;//point to next spot
                        break;
                    default:
                        System.out.println("Invalid Terminal");
                        break;
                }//end switch
                System.out.println("In storedata amt " + amt + " value " + value + " fill " + fill);
                System.out.flush();

                //echo array
                System.out.println("Now the array is: ");
                for (int i = 0; i <= fill - 1; i++) {
                    System.out.println("Dstore["+i+"]="+Dstore[i]+"  Cstore["+i+"]="+Cstore[i]);
                }
                System.out.flush();
            }//end try
            catch (Exception ex) {
                System.out.println("Trouble in storedata catch");
                ex.printStackTrace();
            }//end catch
            finally {
                //release the lock signal all
                infostorage.signalAll();
                lockstrclr.unlock();
            }//end finally
        }//end Stordata function

        public void cleardata(String router, int amt) {
            lockstrclr.lock();
            boolean jim=list.contains(router);//holds off delete
            char first=router.charAt(0);
            try {
                switch (first) {
                    case 'P':
                        while (fillP < 1 && Cstore[0]!="null") infostorage.await();//there must be at least 1
                        for (int i = fillP-1; i >=fillP-amt; i--) {
                            System.out.println("Removing " + i);
                            System.out.println("Clearing Dstore[" + Dstore[fill] + "] at Cstore " + Cstore[fill]);
                            //reset values
                            Dstore[i] = 0;
                            Cstore[i] = "null";
                            fill--;
                            list.remove(Cstore[i]);
                        }fillP = fillP- amt;
                        break;
                    case 'F':
                        while (fillF < 10&& Cstore[10]!="null") infostorage.await();//there must be at least 1
                        for (int i = fillF-1; i >=fillF-amt; i--) {
                            System.out.println("Removing " + i);
                            System.out.println("Clearing Dstore[" + Dstore[fill] + "] at Cstore " + Cstore[fill]);
                            //reset values
                            Dstore[i] = 0;
                            Cstore[i] = "null";
                            fill--;
                            list.remove(Cstore[i]);
                        }fillF = fillF- amt;
                        break;
                    case 'M':
                        while (fillM < 20&& Cstore[20]!="null") infostorage.await();//there must be at least 1
                        for (int i = fillM-1; i >=fillM-amt; i--) {
                            System.out.println("Removing " + i);
                            System.out.println("Clearing Dstore[" + Dstore[fill] + "] at Cstore " + Cstore[fill]);
                            //reset values
                            Dstore[i] = 0;
                            Cstore[i] = "null";
                            fill--;
                            list.remove(Cstore[i]);
                        }fillM = fillM-amt;
                        break;
                    default:
                        System.out.println("Invalid Terminal");
                        break;
                }//end switch

                System.out.flush(); System.out.println("In cleardata amt " + amt + " of fill " + fill);
                System.out.flush();
                //echo array
                System.out.println("Now the array is: ");
                for (int i = 0; i <= fill - 1; i++) {
                    System.out.println("Dstore["+i+"]="+Dstore[i]+"  Cstore["+i+"]="+Cstore[i]);
                }
                System.out.flush();

                System.out.println("Now fill is " + fill);
                System.out.flush();
                System.out.println("Leaving Cleardata fill is " + fill);
                System.out.flush();
            }//end Try
            catch (InterruptedException ex) {
                System.out.println("Trouble in cleardata catch");
                ex.printStackTrace();
            }//end catch
            finally {
                //now buffer is clear tell conditional to all
                infostorage.signalAll();
                lockstrclr.unlock();
            }//end finally
        }//end cleardata
    }//end buffer

    //add stuff
    public static class Add implements Runnable {
        //this is the buffer to add
        Buffer Bufx;
        public Add(Buffer x) {
            Bufx = x;
        }//end constructor
        public void run() {
            String[] addD = {"PB1", "FB2", "PB1", "MB3", "FB3"};//which to add
            int[] addn = {5, 6, 8, 10, 6};//amount to add
            int[] nadd = {-3, 78, 13, 22, 75};//these are what get added
            for (int i = 0; i < addn.length; i++) {
                Bufx.storedata(addD[i], addn[i], nadd[i]);
                System.out.println("Just added " + addn[i] + " to the buffer int " + nadd[i] + " for computer " + addD[i]);
                System.out.println("Buffer fill is " + Bufx.getFill());
                System.out.flush();
                Thread.yield();
            }//end for

        }//end run
    }//end add

    //delete stuff
    public static class Delete implements Runnable {
        Buffer Bufx;
        public Delete(Buffer x) {
            Bufx = x;
        }//end constructor
        public void run() {
            String[] addD = {"FB2", "PB1", "MB3", "PB1"};
            int[] subn = {2, 2, 4, 3};//subtract this amount from buffer
            for (int i = 0; i < subn.length; i++) {
                Bufx.cleardata(addD[i], subn[i]);
                System.out.println("Just deleted " + subn[i] + " from the buffer for computer " + addD[i]);
                System.out.println("Buffer fill is " + Bufx.getFill());
                System.out.flush();
                Thread.yield();
            }//end for
        }//end run
    }//end delete
}//end all
