/*Zachary Czarnecki
* Part 1
* Read into a custom class "Student" String StudentID, String StudentName, int Test1, int Test2, int Test2 from input.txt
* Once in the class, calculate the average of each test and assign the appropriate letter grade. Store the resulting
* calculations as part of an Array (echoing as you go) of Student and read that array into an ArrayList. Sort the Array
* and print. Create the class DeleteStudent to erase two students based on their ID's, and then make AddStudent to add
* three transfers. Print the ArrayList again to show that the students were removed, and yet another time once the
* transfers have been added.
* Part 2
* Add four  more fields to the Student class. Read in the Integer Total_No_Hours and Double Cumulative_GPA, and calculate
* ClassRank based on Total_No_Hours, and a new GPA that takes the three test scores into account.This also requires a
* fifth variable, ClassGP, based on Percentage, to be made for the sake of the equation
* Part 3
* Create a Generic Manager called StudentClassManager. Pass the ArrayList of Student into the Generic and sort using
* extends Comparable. To ensure that the Generic Manager is truly generic, a Double array is also passed through and printed.*/

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

    public static void main(String[] args) throws Exception{
	    //table variables
        String StudentID;
        String StudentName;
        int Test1;
        int Test2;
        int Test3;
        Student obj;
        int TotalNoHours;
        double CumulativeGPA;
        PrintWriter output;
        output=new PrintWriter(new File("GradeReport.txt"));//create output report

        try{
            FileInputStream istream=new FileInputStream("input.txt"); //read in variables
            Scanner input=new Scanner(istream);
            //create ArrayList
            ArrayList<Student> Academic_Class=new ArrayList<Student>();
            while(input.hasNextLine()){
                //Echo Input for easy system survaliance
                System.out.println("Echo input data");//print to console
                output.println("Echo input data");//print to output
                StudentID=input.next();//loop while
                System.out.println("ID echo "+StudentID);
                output.println("ID echo "+StudentID);
                StudentName=input.next();
                System.out.println("Name echo "+StudentName);
                output.println("Name echo "+StudentName);
                Test1=input.nextInt();
                System.out.println("Grade 1 echo "+Test1);
                output.println("Grade 1 echo "+Test1);
                Test2=input.nextInt();
                System.out.println("Grade 2 echo "+Test2);
                output.println("Grade 2 echo "+Test2);
                Test3=input.nextInt();
                System.out.println("Grade 3 echo "+Test3);
                output.println("Grade 3 echo "+Test3);

                //added for Part 2
                TotalNoHours=input.nextInt();
                System.out.println("Total Hours echo "+TotalNoHours);
                output.println("Total Hours echo "+TotalNoHours);
                CumulativeGPA=input.nextDouble();
                System.out.println("GPA echo "+CumulativeGPA);
                output.println("GPA echo "+CumulativeGPA);
                System.out.println(StudentID+", "+StudentName+" got scores "+Test1+" "+Test2+" "+Test3+" and has "
                +TotalNoHours+"Hours with a "+CumulativeGPA+" GPA");
                output.println(StudentID+", "+StudentName+" got scores "+Test1+" "+Test2+" "+Test3+" and has "
                        + TotalNoHours+"Hours with a "+CumulativeGPA+" GPA");

                //new Student class variable to hold each line in sections
                Student ReportCards=new Student(StudentID, StudentName, Test1, Test2, Test3, TotalNoHours, CumulativeGPA);
                ReportCards.listStudents();//format
               //Add reports to ArrayList ReportCards
                Academic_Class.add(ReportCards);
            }//end while
            System.out.println("Getting Students from Report Cards container");//unsorted print
            output.println("Getting Students from Report Cards container");
            for(int i=0; i<=Academic_Class.size()-1;i++){
                obj=Academic_Class.get(i);
                obj.listStudents();
            }

            //sorting
            System.out.println("This is the sorted values from the ReportCards container");
            output.println("This is the sorted values from the ReportCards container");
            SortLarge(Academic_Class);//sorting
            System.out.println("Getting Students from Report Cards");
            output.println("Getting Students from Report Cards");
            //print sorted Students
            for (int i=0; i<=Academic_Class.size()-1;i++){
                obj=Academic_Class.get(i);
                obj.listStudents();
            }//end for

            //Delete drop Students after Print
            DeleteStudent(Academic_Class, "42PA");
            DeleteStudent(Academic_Class, "45A3");
            //re-print with deletes
            System.out.println("\nDropped Students deleted.");
            output.println("\nDropped Students deleted.");
            for (int i=0; i<=Academic_Class.size()-1;i++){
                obj=Academic_Class.get(i);
                obj.listStudents();
            }//end for

            //Add 3 new students
            AddStudent(Academic_Class, new Student("67T4", "Clouse_B", 80, 75, 98, 102, 3.65));
            AddStudent(Academic_Class, new Student("45P5", "Garrison_J", 75, 78, 72, 39, 1.85));
            AddStudent(Academic_Class, new Student("89P0", "Singer_A", 85, 95, 99, 130, 3.87));
            //re-print with added students
            System.out.println("\nNew Students added.");
            output.println("\nNew Students added.");
            for (int i=0; i<=Academic_Class.size()-1;i++){
                obj=Academic_Class.get(i);
                obj.listStudents();
            }//end for

            //Begin Part 3
            System.out.println("\nEnd of Part 2. Now running Part 3; Generic Class.");
            output.println("\nEnd of Part 2. Now running Part 3; Generic Class.");
            //Convert ArrayList to array for generic reading latter
            Student [] ArrayReports = new Student[Academic_Class.size()];
            ArrayReports=Academic_Class.toArray(ArrayReports);

            //Double Generic Class test array
            double[]doubleshp={2.3, 1.5, 15.6, 0.9, 11.2, 8.9, 12.4, 51.2, -3.5};

            //Generic Class Student Sort
            StudentClassManager<Student> myStudents =new StudentClassManager <Student>();
            for(int i=0; i<=9; i++){ //must be minus one from total
                myStudents.setvalue(ArrayReports[i]);
            }
            System.out.println("These are ordered by the generic class StudentClassManager.\n");
            myStudents.ManageandSort();
           for(int i=0; i<=9; i++){
                System.out.println(myStudents.getvalue(i));
                output.println(myStudents.getvalue(i));
            }//end Generic Print

            //Generic Doubles Test
            StudentClassManager<Double> myDoubles = new StudentClassManager <Double>();
            for(int i=0; i<=8; i++){
                myDoubles.setvalue(doubleshp[i]);
            }
            System.out.println("These are ordered by the generic class StudentClassManager.\n");
            myDoubles.ManageandSort();
            for(int i=0; i<=9; i++){
                System.out.println(myDoubles.getvalue(i));
                output.println(myDoubles.getvalue(i));
            }//end Generic Print
        }//end try

        //catch error in-case of invalid input read
        catch (FileNotFoundException e){
            System.err.println("OOPS! NOTHIN FOUND!");
            output.close();
            System.exit(10);
        }
        finally{
            output.close();
            System.exit(2);//close system
        }
    }//end main

    //Begin Student Class. Comparable makes it legiable to Generic
    public static class Student implements Comparable <Student>{
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

        public Student(String ID, String StudentN, int one, int two, int three, int TotalHours, double GPA){
            //make sure to pass in all five variables
            StudentID=ID;
            StudentName=StudentN;
            Test1=one;
            Test2=two;
            Test3=three;
            TotalNoHours=TotalHours;
            CumulativeGPA=GPA;
          //simple calculation of percentage
            percentages=((one+two+three)/3);

            //declare letter
            if (percentages >= 90) {
                letter = 'A';
                ClassGP=4;
            }
            else if(percentages >= 80){
                letter='B';
                ClassGP=3;
            }
            else if (percentages >= 70) {
                letter = 'C';
                ClassGP=2;
            }
            else if( percentages >= 60) {
                letter = 'D';
                ClassGP=1;
            }
             else {
                letter = 'F';
                ClassGP=0;
            }
            NewGPA=(((GPA*TotalHours)+2*ClassGP)/(TotalHours+2));

            //declare Class Rank
            if (TotalNoHours > 90) {
                ClassRank = "SR";
            }
            else if(TotalNoHours > 60){
                ClassRank="JR";
            }
            else if (TotalNoHours > 30) {
                ClassRank = "SO";
            }
            else {
                ClassRank = "FR";

            }
        }//end Student assign

        //Format Print
        public void listStudents() {
            System.out.println("\nStudent Report Card\n, StudentID " + StudentID);
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

        @Override //toString override needed for getValue read in.
        public String toString(){
            String s = "";
            s +="\nStudent Report Card\n, StudentID " + StudentID;
            s +="\nName " + StudentName;
            s +="\nTest 1 grade " + Test1;
            s +="\nTest 2 grade " + Test2;
            s +="\nTest 3 grade " + Test3;
            s +="\n"+percentages + "%";
            s +="\nFinal Grade " + letter;
            s +="\nYear Rank " + ClassRank;
            s +="\nTotal Hours " + TotalNoHours;
            s +="\nOld GPA " + CumulativeGPA;
            s +="\nNew GPA " + NewGPA;
            return s;
        }

        //Rank based on percentage - Higher to lower
        public int compareTo(Student o){
            if(getpercentages()<o.getpercentages()) return 1;
            else if(getpercentages()>o.getpercentages()) return -1;
            else return 0;
        }
        public double getpercentages(){return percentages;}//for sorting purposes
        public String getStudentID(){return StudentID;}//for easy deletion
    }//end class Student

    //sort ArrayList
    public static void SortLarge(ArrayList<Student> Academic_Class){
        Student xsave, ysave;
        int isw=1, xlast=Academic_Class.size();
        while (isw==1){
            isw=0;
            for(int i=0;i<=xlast-2;i++){
                if((Academic_Class.get(i)).compareTo((Academic_Class.get(i+1)))==1){ //re-arrange via delete
                    xsave=Academic_Class.get(i);
                    ysave=Academic_Class.get(i+1);
                    Academic_Class.remove(i);
                    Academic_Class.add(i, ysave);
                    Academic_Class.remove(i+1);
                    Academic_Class.add(i+1, xsave);
                    isw=1;
                }
            }//end for
        }//end while
        return;
    }//end Sort

    // Begin AddStudent
    public static void AddStudent(ArrayList<Student> Academic_Class, Student obj){
        Academic_Class.add(obj);
    }

    //delete Student
    public static void DeleteStudent (ArrayList<Student> Academic_Class, String StudentID){
        for(int i=0; i<Academic_Class.size()-1;i++){
            if(Academic_Class.get(i).getStudentID().equals(StudentID)){
                Academic_Class.remove(i);
            }//end if
        }//end for
    }//end DeleteStudent
}//end main

//make Generic class to more objects - must be outside main
class StudentClassManager <T extends Comparable<T>>{ //implemtents compareTo of Students class to T function
    protected ArrayList<T> myList =new ArrayList<T>();//Generic Array.
    protected int mcount;//for keeping track in the array
    public StudentClassManager(){
        mcount=0;
    }//end sub-class
    public int setvalue(T x){// places values in order
        myList.add(mcount++,x);
        return mcount;
    }//end setvalue
    public T getvalue(int i){ //gets values from array
        if(i<=mcount)return myList.get(i);
        else return myList.get(0);
    }//end getvalue
    public void ManageandSort(){ //similar to ArrayList logic
        T xsave, ysave, a, b;
        int isw=1, xlast=myList.size();
        while(isw==1){
            isw=0;
            for(int i=0;i<=xlast-2;i++) {
                a=myList.get(i);
                b=myList.get(i+1);
                switch(a.compareTo(b)){
                    case -1: break; //already in order
                    case 1: xsave=myList.get(i);//out of order - switching 1 and -1 will change the order of values. This is smaller to Greater
                        ysave=myList.get(i+1);
                        myList.remove(i);
                        myList.add(i, ysave);
                        myList.remove(i+1);
                        myList.add(i+1, xsave);
                        isw=1;
                        break;
                    default: //equal, no change
                }//end switch
            }//end forr
        }//end while
    }//end ManageandSort
}//end StudentClassManager - Code
