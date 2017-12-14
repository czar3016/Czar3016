/*zachary Czarnecki - Test 1
The HR department of the We Live For You (WLFU) Medical Corporation wants a program to calulate Salary and Taxes for
empolyees at it's Boston location, Resident Boston Hospital. There are 4 types of employees; Administration, Doctors,
Nurses, and Support.

There are 3 levels of Administration: Senior, Junior, and Support. They are payed 4$00,000 with a
 20% bonus option, $175,000 with 10% bonus, and $40,000 flat, respectively.

 Doctors are payed $155,000 with a 0.25% bonus for each patient treated that year.

 There are 3 Nurse types; Clinic, Hospital Floor, and Hospital Administrative. Each are paid a base of $65,000, but have
 different bonus levels. Clinic gets 10%, Floor gets 15%, and Administrative gets 20%.

 There are two types of Support, Technical and Other; $45,000 a year and $35,000 respectively.
 All employees have an extra 15% Cost of Living Adjustment(COLA) applied to their base pay, separate of the bonus
 options. Massachusetts State Tax is 5%, and Federal is 25%

 The Name, SSN, Race, and a special value that denotes rank, or patient count in the case of the doctors, are input into
 an array and the Salary, bonuses, and taxes are calculated from this array. A Generic compareTo sort is applied to sort
 the employees by race before the list is printed.

 It is also possible to hand enter/update the the special value by hand. To test this function, Dr. I. M. Bones has his
 patient count entered by the user before running.
 */
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
        int j;
        PrintWriter output;
        output=new PrintWriter(new File("Test1Report.txt"));//create output report
        //Array of Boston Employees
        Bdoctor doc1=new Bdoctor("I.M. Bones","455657890", 100, "AA");
        Bnurse nurse1=new Bnurse("U.R. Temp","789302345", 3, "CA");
        Bdoctor doc2=new Bdoctor("D.V.M. Jordan","786456712", 120, "CA");
        Badmin boss1=new Badmin("I.M. Boss","543126787", 1, "HS");
        Boston[] staff=new Boston[4];
        staff[0]=doc1;
        staff[1]=nurse1;
        staff[2]=doc2;
        staff[3]=boss1;
        for(j=0; j<=3; j++){
            staff[j].calcSalary();
            staff[j].calculateStateTax();
            staff[j].calculateFedTax();
        }
        //UpdateSpecialInfo Tester
        System.out.println("Please enter Dr. Bones patient Count\n");
        staff[0].UpdateSpecialInfo();
        //Double Generic Class test array
        double[]doubleshp={2.34, 5.45, 1.23, 17.81, 12.91};
        //Sort Employees with Generic
        WFLUManager<Boston> GoSox = new WFLUManager <Boston>();
        for(int i=0; i<=3; i++){
            GoSox.Add(staff[i]);
        }
        System.out.println("This orders employees based on Race\n");
        GoSox.Sort();
        for(int i=0; i<=3; i++){
            System.out.println(GoSox.Get(i));
            output.println(GoSox.Get(i));
        }//end Generic Print

        //Generic Doubles Test
        WFLUManager<Double> myDoubles = new WFLUManager <Double>();
        for(int i=0; i<=4; i++){
            myDoubles.Add(doubleshp[i]);
        }
        System.out.println("This is a test of the Generic-ness of WFLUManager\n");
        myDoubles.Sort();
        for(int i=0; i<=4; i++){
            System.out.println(myDoubles.Get(i));
            output.println(myDoubles.Get(i));
        }//end Generic Print
    }//end main
}//end Main
abstract class Employees{
    //define abstract methods
    public abstract double getSalary();
    public abstract double getStateTax();
    public abstract double getFedTax();
    public abstract int getSpecialInfo();
}//End of Employees

class The_WFLU_Team extends Employees{
    protected String Name;
    protected String SSN;
    protected int specialval;
    protected String Race;
    public The_WFLU_Team (String Name, String SSN, int specialval, String Race){
        this.Name=Name;
        this.SSN=SSN;
        this.specialval=specialval;
        this.Race=Race;
    }//end constructor
    //seeters and getters
    public void setName(String Name){return;}
    public void setSSN(String SSN){return;}
    public void setspeciaval(int speciaval){return;}
    public void setRace(String Race){return;}
    public String getName(){return Name;}
    public String getSSN(){return SSN;}
    public Integer getSpecial(){return specialval;}
    public String getRace(){return Race;}
    //compareTo function for The_WFLU_Team
    public int compareTo(The_WFLU_Team o){
       int result=Race.compareTo(o.Race);
        return result;
    }//end compareTo
    public double getSalary(){return 0.0;}
    public double getStateTax(){return 0.0;}
    public double getFedTax(){return 0.0;}
    public int getSpecialInfo(){return 0;}
}//End of The_WFLU_Team
//Boston
class Boston extends The_WFLU_Team implements Comparable <Boston>{
    protected double stateTax;
    protected double fedTax;
    protected double salary;
    public Boston(String Name, String SSN, int specialval, String Race){
        super(Name,SSN,specialval,Race);
    }
    public double getstateTax(){return stateTax;}//calls lower stateTax
    public void calculateStateTax(){return;}
    public double getfedTax(){return fedTax;}//calls lower fedTax
    public void calculateFedTax(){return;}
    public double getSalary(){return salary;}//calls lower Salary
    public void calcSalary(){return;}
    public int getSpecialInfo(){return specialval;}//calls for update Special info
    public void UpdateSpecialInfo(){return;}
    //compareTo for Boston
    public int compareTo(Boston o){
        int result=Race.compareTo(o.Race);
        return result;
    }//end compareTo
}//end Solids - //

class Bdoctor extends Boston{
    protected double stateTax;
    protected double fedTax;
    protected double salary;
    protected double total;
    protected double COLA; //Cost of living expense
    protected double Adjustment;//special value changer
    protected String fullRace;
    protected String title;//not used here but kept for code constistancy
    Scanner input = new Scanner(System.in);//Input update specialval

    public Bdoctor(String Name, String SSN, int specialval, String Race){
        super(Name,SSN,specialval,Race);
        salary=155000; //doctors only have one base salary
        COLA=salary*.15;
        Adjustment=salary*.0025*specialval;
       //translate Race code to full output
        if (Race == "AA"){
            fullRace="African American";
        }
        else if (Race == "CA"){
            fullRace="Caucasain";
        }
        else if (Race == "AS"){
            fullRace="Asian";
        }
        else if (Race == "HS"){
            fullRace="Hispanic";
        }
        else if (Race == "OT"){
            fullRace="Other";
        }
    }//end constructor

    public void calcSalary(){total=salary+COLA+Adjustment;}
    public void calculateFedTax(){fedTax=total*.25;}
    public void calculateStateTax(){stateTax=total*.05;}
    //getters
    public double getstateTax(){return stateTax;}
    public double getfedTax(){return fedTax;}
    public double getSalary(){return total;}
    public void UpdateSpecialInfo(){specialval=input.nextInt();}
    public int getSpecialInfo(){return specialval;}//calls for update Special info
    @Override
    public String toString() {
        return "Dr. "+ Name+"\nResident Boston Hospital\nRace " + fullRace+"\nSSN "+SSN+"\nPatient Count "+specialval+
                "\nSalary Base $"+salary+"\nCOLA (15%) $"+COLA+"\nPatient Adj $"+Adjustment+"\nTotal $"+total+
                "\nEstimated Taxes\nFederal $"+fedTax+"\nMass State $"+stateTax+"\n";
    }
}
class Bnurse extends Boston{
    protected double stateTax;
    protected double fedTax;
    protected double salary;
    protected double total;
    protected double COLA; //Cost of living expense
    protected double Adjustment;//special value changer
    protected String fullRace;
    protected String title;//job title e.g. Clinic Nurse
    Scanner input = new Scanner(System.in);//Input update specialval

    public Bnurse(String Name, String SSN, int specialval, String Race){
        super(Name,SSN,specialval,Race);
        salary=65000; //nurses only have one base salary
        COLA=salary*.15;
        //Adjustment is determined by type for Nurses
        if (specialval==1) {
            Adjustment = salary * .1;
            title="Clinic Nurse";
        }
        else if (specialval==2) {
            Adjustment = salary * .15;
            title="Hospital Floor Nurse";
        }
        else if (specialval==3) {
            Adjustment = salary * .2;
            title="Hospital Administrative Nurse";
        }
        //translate Race code to full output
        if (Race == "AA"){
            fullRace="African American";
        }
        else if (Race == "CA"){
            fullRace="Caucasain";
        }
        else if (Race == "AS"){
            fullRace="Asian";
        }
        else if (Race == "HS"){
            fullRace="Hispanic";
        }
        else if (Race == "OT"){
            fullRace="Other";
        }
    }//end Constructor

    public void calcSalary(){total=salary+COLA+Adjustment;}
    public void calculateFedTax(){fedTax=total*.25;}
    public void calculateStateTax(){stateTax=total*.05;}
    //getters
    public double getstateTax(){return stateTax;}
    public double getfedTax(){return fedTax;}
    public double getSalary(){return total;}
    public void UpdateSpecialInfo(){specialval=input.nextInt();}
    public int getSpecialInfo(){return specialval;}//calls for update Special info
    @Override
    public String toString() {
        return "Nurse "+ Name+"\nResident Boston Hospital\nRace " + fullRace+"\nSSN "+SSN+"\nTitle"+title+
                "\nSalary Base $"+salary+"\nCOLA (15%) $"+COLA+"\nPosition Bonus $"+Adjustment+"\nTotal $"+total+
                "\nEstimated Taxes\nFederal $"+fedTax+"\nMass State $"+stateTax+"\n";
    }//end toString
}// end Bnurse

class Badmin extends Boston{
    protected double stateTax;
    protected double fedTax;
    protected double salary;
    protected double total;
    protected double COLA; //Cost of living expense
    protected double Adjustment;//special value changer
    protected String fullRace;
    protected String title;//job title e.g. Senior
    Scanner input = new Scanner(System.in);//Input update specialval

    public Badmin(String Name, String SSN, int specialval, String Race){
        super(Name,SSN,specialval,Race);
        //Adjustment and salaries are determined by type for Administration
        if (specialval==1) {
            salary=400000;
            title="Senior Executive";
        }
        else if (specialval==2) {
            salary=175000;
            Adjustment = salary * .1;
            title="Junior Executive";
        }
        else{
            salary=40000;
            Adjustment = 0;
            title="Support Executive";
        }
        //translate Race code to full output
        if (Race == "AA"){
            fullRace="African American";
        }
        else if (Race == "CA"){
            fullRace="Caucasain";
        }
        else if (Race == "AS"){
            fullRace="Asian";
        }
        else if (Race == "HS"){
            fullRace="Hispanic";
        }
        else if (Race == "OT"){
            fullRace="Other";
        }
        COLA=salary*.15;
    }//end Constructor

    public void calcSalary(){total=salary+COLA+Adjustment;}
    public void calculateFedTax(){fedTax=total*.25;}
    public void calculateStateTax(){stateTax=total*.05;}
    //getters
    public double getstateTax(){return stateTax;}
    public double getfedTax(){return fedTax;}
    public double getSalary(){return total;}
    public void UpdateSpecialInfo(){specialval=input.nextInt();}
    public int getSpecialInfo(){return specialval;}//calls for update Special info
    @Override
    public String toString() {
        return "Ex. "+ Name+"\nResident Boston Hospital\nRace " + fullRace+"\nSSN "+SSN+"\nTitle"+title+
                "\nSalary Base $"+salary+"\nCOLA (15%) $"+COLA+"\nBonus Option $"+Adjustment+"\nTotal $"+total+
                "\nEstimated Taxes\nFederal $"+fedTax+"\nMass State $"+stateTax+"\n";
    }//end ToString
}//end Badmin

class Bsupport extends Boston{
    protected double stateTax;
    protected double fedTax;
    protected double salary;
    protected double total;
    protected double COLA; //Cost of living expense
    protected double Adjustment;//not used here, but kept for code constistancy
    protected String fullRace;
    protected String title;//job title e.g. Technician
    Scanner input = new Scanner(System.in);//Input update specialval

    public Bsupport(String Name, String SSN, int specialval, String Race){
        super(Name,SSN,specialval,Race);
        //Salaries are determined by type for Administration
        if (specialval==1) {
            salary=45000;
            title="Technician Support";
        }
        else if (specialval==2) {
            salary=35000;
            title="Other Support";
        }
        COLA=salary*.15;
        Adjustment=salary*.0025*specialval;
        //translate Race code to full output
        if (Race == "AA"){
            fullRace="African American";
        }
        else if (Race == "CA"){
            fullRace="Caucasain";
        }
        else if (Race == "AS"){
            fullRace="Asian";
        }
        else if (Race == "HS"){
            fullRace="Hispanic";
        }
        else if (Race == "OT"){
            fullRace="Other";
        }
    }//end Constructor

    public void calcSalary(){total=salary+COLA+Adjustment;}
    public void calculateFedTax(){fedTax=total*.25;}
    public void calculateStateTax(){stateTax=total*.05;}
    //getters
    public double getstateTax(){return stateTax;}
    public double getfedTax(){return fedTax;}
    public double getSalary(){return total;}
    public void UpdateSpecialInfo(){specialval=input.nextInt();}
    public int getSpecialInfo(){return specialval;}//calls for update Special info
    @Override
    public String toString() {
        return "Medical Support"+ Name+"\nResident Boston Hospital\nRace " + fullRace+"\nSSN "+SSN+"\n"
                +title+ "\nSalary Base $"+salary+"\nCOLA (15%) "+COLA+"\nNo Bonus Option\nTotal "+total+
                "\nEstimated Taxes\nFederal $"+fedTax+"\nMass State $"+stateTax+"\n";
    }//End toString
}//End Support

//make Generic class to more objects - must be outside main
class WFLUManager <T extends Comparable<T>>{ //implemtents compareTo of Students class to T function
    protected ArrayList<T> myStaff =new ArrayList<T>();//Generic Array.
    protected int mcount;//for keeping track in the array
    public WFLUManager(){
        mcount=0;
    }//end sub-class
    public int Add (T x){// places values in order
        myStaff.add(mcount++,x);
        return mcount;
    }//end setvalue
    public T Get(int x){ //gets values from array
        if(x<=mcount)return myStaff.get(x);
        else return myStaff.get(0);
    }//end getvalue
    public void Sort(){ //similar to ArrayList logic
        T xsave, ysave, a, b;
        int isw=1, xlast=myStaff.size();
        while(isw==1){
            isw=0;
            for(int i=0;i<=xlast-2;i++) {
                a=myStaff.get(i);
                b=myStaff.get(i+1);
                switch(a.compareTo(b)){
                    case -1: break; //already in order
                    case 1: xsave=myStaff.get(i);//out of order - switching 1 and -1 will change the order of values. This is smaller to Greater
                        ysave=myStaff.get(i+1);
                        myStaff.remove(i);
                        myStaff.add(i, ysave);
                        myStaff.remove(i+1);
                        myStaff.add(i+1, xsave);
                        isw=1;
                        break;
                    default: //equal, no change
                }//end switch
            }//end forr
        }//end while
    }//end Sort
}//end WFLUManager - Code