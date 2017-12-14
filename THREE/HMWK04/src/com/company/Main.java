/*Zachary Czanrecki HMWK 04
Take the data and logic provided by HMWK02 part 1 and read it into a Generic LinkedList instead of an ArrayList.
The Students are still stored in descending order of % score. The add and delete methods are now part of the
StudentClassManager generic. The addinorder() function in the allows the Manager to read in the records in order by
default, though a standard addnode() is written. just in case. Pointers are utilized though out.The deleteNode()
function is called in main by the DeleteStudent class so Students can be deleted by ID alone.*/
package com.company;
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

    public static void main(String[] args) {
        //table variables
        int i, num;
        String StudentID;
        String StudentName;
        int Test1;
        int Test2;
        int Test3;
        Student obj;
        int TotalNoHours;
        double CumulativeGPA;
        try {
            FileInputStream istream = new FileInputStream("input.txt"); //read in variables
            Scanner input = new Scanner(istream);
            //create LinkList
            StudentClassManager<Student> Academic_Class=new StudentClassManager<Student>();
            while (input.hasNextLine()) {
                //Echo Input for easy system survaliance
                System.out.println("Echo input data");//print to console
                StudentID = input.next();//loop while
                System.out.println("ID echo " + StudentID);
                StudentName = input.next();
                System.out.println("Name echo " + StudentName);
                Test1 = input.nextInt();
                System.out.println("Grade 1 echo " + Test1);
                Test2 = input.nextInt();
                System.out.println("Grade 2 echo " + Test2);
                Test3 = input.nextInt();
                System.out.println("Grade 3 echo " + Test3);
                TotalNoHours = input.nextInt();
                System.out.println("Total Hours echo " + TotalNoHours);
                CumulativeGPA = input.nextDouble();
                System.out.println("GPA echo " + CumulativeGPA);
                System.out.println(StudentID + ", " + StudentName + " got scores " + Test1 + " " + Test2 + " " + Test3 + " and has "
                        + TotalNoHours + " Hours with a " + CumulativeGPA + " GPA\n");
                //new Student class variable to hold each line in sections
                Student ReportCards = new Student(StudentID, StudentName, Test1, Test2, Test3, TotalNoHours, CumulativeGPA);
                //run addinorder to add records to LinkList arranged - addnode is not run as it would be overwritten by addinorder
                Academic_Class.addinorder(ReportCards);
            }//end while
            // - now that data is read in, time to print the Link List
            System.out.println("Getting Students from Report Cards container");
            num=Academic_Class.getmcount();//make sure to run before each for to keep passes up to time.
            for(i=1; i<=num;i++){
                System.out.println(Academic_Class.findnode(i));
            }
            //Delete dropping Students
            System.out.println("\nDropped Students deleted.");
            DeleteStudent(Academic_Class, "42PA");
            DeleteStudent(Academic_Class, "45A3");
           // Academic_Class.deleteNode(new Student("42P4"));//only possible with shell constructor
            //Academic_Class.deleteNode(new Student("45A3"));
            num=Academic_Class.getmcount();//re-count num for proper display
            for(i=1; i<=num;i++){
                System.out.println(Academic_Class.findnode(i));
            }

            //Add 3 new students
            Academic_Class.addinorder(new Student("67T4", "Clouse_B", 80, 75, 98, 102, 3.65));
            Academic_Class.addinorder(new Student("45P5", "Garrison_J", 75, 78, 72, 39, 1.85));
            Academic_Class.addinorder(new Student("89P0", "Singer_A", 85, 95, 99, 130, 3.87));
            num=Academic_Class.getmcount();//re-count num for proper display
            //Re-print with new students
            System.out.println("New Students Added");
            for(i=1; i<=num;i++){
                System.out.println(Academic_Class.findnode(i));
            }
        }//end try

        //catch error in-case of invalid input read
        catch (FileNotFoundException e) {
            System.err.println("OOPS! NOTHIN FOUND!");
            System.exit(10);
        }
        finally {
            System.exit(2);//close system
        }
    }
    //call deletenode to erase by certain variable
    public static void DeleteStudent(StudentClassManager<Student> Academic_Class, String StudentID){
        for(int i=0; i<Academic_Class.getmcount()-1;i++){
            if(Academic_Class.findnode(i).getStudentID().equals(StudentID)){
                Academic_Class.deleteNode(i);
            }//end if
        }//end for
    }//end DeleteStudent

    public static class Student implements Comparable<Student> {
        //passed in
        String StudentID;
        String StudentName;
        int Test1;
        int Test2;
        int Test3;
        int TotalNoHours;
        double CumulativeGPA;
        //return
        double NewGPA;
        double percentages;
        char letter;
        int ClassGP;
        String ClassRank;
        public Student(String ID){//ID only constructor for search and destroy
            StudentID = ID;
        }
        public Student(String ID, String StudentN, int one, int two, int three, int TotalHours, double GPA) {
            //make sure to pass in all five variables
            StudentID = ID;
            StudentName = StudentN;
            Test1 = one;
            Test2 = two;
            Test3 = three;
            TotalNoHours = TotalHours;
            CumulativeGPA = GPA;
            //simple calculation of percentage
            percentages = ((one + two + three) / 3);

            //declare letter
            if (percentages >= 90) {
                letter = 'A';
                ClassGP = 4;
            } else if (percentages >= 80) {
                letter = 'B';
                ClassGP = 3;
            } else if (percentages >= 70) {
                letter = 'C';
                ClassGP = 2;
            } else if (percentages >= 60) {
                letter = 'D';
                ClassGP = 1;
            } else {
                letter = 'F';
                ClassGP = 0;
            }
            NewGPA = (((GPA * TotalHours) + 2 * ClassGP) / (TotalHours + 2));

            //declare Class Rank
            if (TotalNoHours > 90) {
                ClassRank = "SR";
            } else if (TotalNoHours > 60) {
                ClassRank = "JR";
            } else if (TotalNoHours > 30) {
                ClassRank = "SO";
            } else {
                ClassRank = "FR";

            }
        }//end Student constructor

        //Format Print
        public void listStudents() {
            System.out.println("\nStudent Report Card\nStudentID " + StudentID);
            System.out.println("Name " + StudentName);
            System.out.println("Test 1 grade " + Test1);
            System.out.println("Test 2 grade " + Test2);
            System.out.println("Test 3 grade " + Test3);
            System.out.println(percentages + "%");
            System.out.println("Final Grade " + letter);
            System.out.println("Year Rank " + ClassRank);
            System.out.println("Total Hours " + TotalNoHours);
            System.out.println("Old GPA " + CumulativeGPA);
            System.out.println("New GPA " + NewGPA);
        }

        @Override //toString override needed for StudentClassManager read in.
        public String toString() {
            String s = "";
            s += "\nStudent Report Card\nStudentID " + StudentID;
            s += "\nName " + StudentName;
            s += "\nTest 1 grade " + Test1;
            s += "\nTest 2 grade " + Test2;
            s += "\nTest 3 grade " + Test3;
            s += "\n" + percentages + "%";
            s += "\nFinal Grade " + letter;
            s += "\nYear Rank " + ClassRank;
            s += "\nTotal Hours " + TotalNoHours;
            s += "\nOld GPA " + CumulativeGPA;
            s += "\nNew GPA " + NewGPA+"\n";
            return s;
        }

        //Rank based on percentage - Higher to lower
        public int compareTo(Student o) {
            if (getpercentages() > o.getpercentages()) return 1;
            else if (getpercentages() < o.getpercentages()) return -1;
            else return 0;
        }

        public double getpercentages() {
            return percentages;
        }//for sorting purposes

        public String getStudentID() {
            return StudentID;
        }//for easy deletion
    }//end class Student
}//end main

    //make Generic class to more objects - must be outside main
    class StudentClassManager <T extends Comparable<T>>{ //implemtents compareTo of Students class to T function
        protected  myList <T> head, tail;//pointers
        protected int mcount;//for keeping track nodes in list
        public StudentClassManager(){
            myList<T> head=null;
            myList<T> tail=null;
            int mcount=0;
        }//end constructor

        public int getmcount(){return mcount;}
        //internal class for node construction

        private static class myList<T>{
            protected T nodevalue;
            protected myList<T> next;
            public myList(T x){
                nodevalue=x;
                next=null;//pointer to next node
            }//end constructor
        }//end of internal class

        public T findnode(int x){ //gets nodes for search
            if((x<0)||(x>mcount))System.out.println("ERROR in getnode"+x+"while list holds"+mcount);
            int ict=1;
            myList<T> curnode=head;
            while(ict<x){
                curnode=curnode.next;
                ict++;
            }//end while - current node set
            return curnode.nodevalue;
        }//end findnode

        public int addnode (T x){
            myList<T> newnode=new myList<T>(x);
            if(head==null){//empty
                head=newnode;
                tail=newnode;
            }//end if
            else{//already occupied
                newnode.next=head;
                head=newnode;
            }//end else
            mcount++;
            return mcount;
        }//end addnode

        public void deleteNode(T x, int i) {
            boolean found=false;
            myList<T> cnode;
            myList<T> nnode;
           // myList<T> temp;
            cnode=head;
            nnode=cnode.next;
            int count=0;
            //temp=head;
            //String xId = ((Main.Student)x).getStudentID();
            //casting up to Student was the only way I could get this to work based on ID call alone.
           // while(cnode!=null) {
               // String cId = ((Main.Student)cnode.nodevalue).getStudentID();
                /*if (xId.compareTo(cId)==0){
                    found=true;
                    nnode.next = cnode.next;
                    mcount--;
                }*/
                /*if (i==0){ //generic delete call.
                    head=head.next;
                    cnode.next=temp.next;
                    mcount--;
                }else if(i>0 && i<mcount){
                    for(int j=0; j<mcount; j++){
                        if(j==i-1){
                            temp.next=temp.next.next;
                        }
                        temp=temp.next;
                    }
                }else{//movin' right along!
                    nnode=cnode;
                    cnode = cnode.next;
                }
                if (i==0){
                    head=nnode;
                    cnode.next=null;
                    mcount--;
                }
                while (count<i-1){
                    cnode=nnode;
                    nnode=nnode.next;
                    count++;
                }
                cnode.next=nnode.next;
                nnode.next=null;
                mcount--;
            }*/
            if (nnode.nodevalue.compareTo(x)==0){
            found=true;
            nnode.next = cnode.next;
            mcount--;
        }
        }

        public void addinorder(T x){
            myList<T> newnode=new myList<T>(x);
            myList<T> cnode, nnode;
            if(mcount==0){
                head=newnode;
                tail=newnode;
                mcount=1;
                return;
            }//end if
            if(x.compareTo(head.nodevalue)==1){//slap on the front
                newnode.next=head;
                head=newnode;
                mcount++;
                return;
            }
            if(x.compareTo(tail.nodevalue)==-1){//slap on the back
                tail.next=newnode;
                tail=newnode;
                mcount++;
                return;
            }
            cnode=head;
            nnode=head.next;
            while(x.compareTo(nnode.nodevalue)!=1){
                cnode=nnode;
                nnode=nnode.next;//chain forward
            }
            cnode.next=newnode;
            newnode.next=nnode;
            mcount++;
            return;
        }
    }//end StudentClassManager - Code
