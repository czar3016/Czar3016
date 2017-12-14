/*Zachary Czarnecki CSC-406 Test 1
* Use the executor function to run threads that simulate bidding on a painting on eBay. The object is declared an
 * instance of the class 'ForSale' while the 6 bids are run though a function of the class called 'MyBid'. In 'MyBid',
 * various factors are considered: whether the item has already been sold, if the item's time has yet to expire, if the
 * bidder has pushed more than the minimum amount, and if the bidder has simply been out bid (Currentbid is kept as a
 * running tally to allow this. Two methods of calling the function with the executor where used: making 6 instances of
 * 'ForSale' and running those through individual calls to the ExecutorService that passed them to a Runnable class
 * called 'Bidder' where the 'MyBid' function was called only once; and making one instance of ForSale, executing it,
 * and having Bidder make the 6 calls to MyBid. This code shows the random result,though changing some comments will
 * make the code have a consistent result*/
package com.company;
import java.io.*;
import javax.swing.*;
import java.lang.IllegalStateException;
import java.sql.Time;
import java.util.NoSuchElementException;
import java.lang.*; //allows the threads to be created as objects
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Main {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        //set up the item
        //make calls to MyBid in Runnable Bidder
        /*ForSale Dutch=new ForSale("Dutch Masters Oil Painting", 4827, 5000.00, 60000.00,18490, 17050);
         executor.execute(new Bidder(Dutch));*/

        //Make calls to MyBid in multiple executor threads
        ForSale bid1=new ForSale("Dutch Masters Oil Painting", 4827, 5000.00, 60000.00,18490/*17050+(24*60)*/, 17050
                ,5283,4000,18481);
        ForSale bid2=new ForSale("Dutch Masters Oil Painting", 4827, 5000.00, 60000.00,18490/*17050+(24*60)*/, 17050
                ,4681,15000,18482);
        ForSale bid3=new ForSale("Dutch Masters Oil Painting", 4827, 5000.00, 60000.00,18490/*17050+(24*60)*/, 17050
                ,5283,14500,18483);
        ForSale bid4=new ForSale("Dutch Masters Oil Painting", 4827, 5000.00, 60000.00,18490/*17050+(24*60)*/, 17050
                ,5283,17500,18485);
        ForSale bid5=new ForSale("Dutch Masters Oil Painting", 4827, 5000.00, 60000.00,18490/*17050+(24*60)*/, 17050
                ,4681,25000,18489);
        ForSale bid6=new ForSale("Dutch Masters Oil Painting", 4827, 5000.00, 60000.00,18490/*17050+(24*60)*/, 17050
                ,5283,32000,18495);
        //must pass through Bidder runnable
        executor.execute(new Bidder(bid1));
        executor.execute(new Bidder(bid2));
        executor.execute(new Bidder(bid3));
        executor.execute(new Bidder(bid4));
        executor.execute(new Bidder(bid5));
        executor.execute(new Bidder(bid6));
        System.out.flush();
        executor.shutdown();
        while (!executor.isTerminated());
        System.out.println("And the Winner is: "+ForSale.victor);
    }//end main
   //need running class to parse threads through to ForSale
    public static  class Bidder implements Runnable{
       ForSale Dutch;
       public Bidder (ForSale x){
           Dutch=x;
        }//end constructor
        public void run(){
           //calls to MyBid must be made in runnable to process in thread correctly. when running through main
           /* Dutch.MyBid(4827,5283,4000,18481);
            Dutch.MyBid(4827,4681,15000,18482);
            Dutch.MyBid(4827,5283,14500,18483);
            Dutch.MyBid(4827,5283,17500,18485);
            Dutch.MyBid(4827,4681,25000,18489);
            Dutch.MyBid(4827,5283,32000,18495);*/
           //multiple executors in Main
            Dutch.MyBid(4827);
        }
    }//end Bidder
    public static class ForSale {
        String ItemDes; //name
        int itemno; //lot designation
        double MinBid; //must be this much
        double Buyitnow;//immediate stop
        int Timedone; //stop bidding when ctime=this
        public static int ctime; //current time
        //Mybid variables
        int bidno;//bidderID
        double bidamt;//$ amout
        int curtime;//time of user bid
        //public variables for outside influence
        public static double Newbid=0;
        public  double Currentbid=MinBid;//Minbid is default current bid - cannot be static
        public static int victor=0;//this is display who won at the end
        public static int sold=0; //if sold
        private static Lock lock=new ReentrantLock();//lock
        public ForSale(String ItemDes, int itemno, double MinBid, double Buyitnow, int Timedone, int ctime,int bidno,
                       double bidamt, int curtime ){
            //constructor for item entry
            this.ItemDes=ItemDes;
            this.itemno=itemno;
            this.MinBid=MinBid;
            this.Buyitnow=Buyitnow;
            this.Timedone=Timedone;
            this.ctime=ctime;
            //these would have been the only differences between instances of ForSale had the multi-executor option been
            //used
            this.bidno=bidno;
            this.bidamt=bidamt;
            this.curtime=curtime;
        }//end constructor

        public void MyBid(int itemno/*,int bidno, double bidamt, int curtime*/){
            lock.lock();//one at a time, please
            try{
                if (sold==0&& curtime<Timedone && bidamt>MinBid) {//must not be sold, unexpired, and must bid enough
                    if(bidamt<Currentbid){//bid inbetween Mid and High
                        System.out.println("Sorry, "+bidno+", your bid of $"+bidamt+" is not enough for "+itemno+", "
                                +ItemDes);
                        ctime=curtime;//reset current time of sale
                        System.out.flush();
                    }//end < if
                    if (bidamt == Buyitnow) {
                        System.out.println("Great Job, " + bidno + "! You just bought " + itemno + " " + ItemDes +
                                " for $" + bidamt + "amount");
                        ctime=curtime;//reset ctime
                        sold = 1;//stop auction
                        victor = bidno; //set winner
                        System.out.flush();
                    }//end Buyitnow if
                    if (bidamt>Currentbid){
                        victor = bidno; //set leader
                        ctime=curtime;//reset ctime
                        Currentbid=bidamt;//reset currentbit
                        System.out.println("Congratualtions, "+bidno+"! You are currently the highest bidder for "+
                        itemno+", "+ItemDes+"with a bid of $"+bidamt+", but keep watching. Good stuff goes fast on eBay!");
                        System.out.flush();
                    }//end success bid
                }//end sold if
                else if(sold==0&&bidamt<MinBid){//must bid higher
                    System.out.println("Sorry, "+bidno+", your bid of $"+bidamt+" is not enough for "+itemno+", "
                            +ItemDes);
                    ctime=curtime;//reset ctime
                    System.out.flush();
                }//end expiration if
                else if(curtime>Timedone){//bidding is done
                    System.out.println("Sorry, "+bidno+", you are too late to get "+itemno+", "+ItemDes);
                    ctime=curtime;//reset ctime
                    sold = 1;//stop auction
                    System.out.flush();
                }//end expiration if
                Thread.sleep(5);
            }//end try
            catch (InterruptedException ex){//error catch
                System.out.println("Trouble");
                ex.printStackTrace();
            }
            lock.unlock();//unlock
        }//end MyBid
    }//end ForSale
}//end all
