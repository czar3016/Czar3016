/*Test 2 Zachary Czarnecki
* NASA needs a Stack Parcer that will calculate Thrust and Direction of their rockets. Both are calculated using Ordered
* Pairs, that are calculated as custom generics. % effects Thrust, * effects Direction, and + effects both. Luckily, all
* NASA engineers write their equations in such a way no variable will be teamed up with a operator that won't work with
* it. Direction ad Thrust are displayed separately via a get function*/

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
        int i = 0;
        int oprior;//for keeping count
        Operand ivalu, exvalue; //return values
        //make new classes to read in operands as arrays
        Operand A = new Operand(30000, 75.2);
        Operand a = new Operand(0, 12.8);
        Operand b = new Operand(15000, 0);
        Operand c = new Operand(-2000, 5);
        Operand d = new Operand(0, -14.5);
        //array for operand letter call
        char[] varia = {'A', 'a', 'b', 'c', 'd'};
        //array for operand integer values
        Operand[] operand = {A, a, b, c, d};
        //array of operations - arranged by precedence
        char[] operators = {'%', '*', '+', ')', '(', '#'}; //#=exit
        //array for order of operations calculation
        int[] precedence = {3, 2, 1, 99, -99, 100};
        //Operands array - int
        StackManager<Operand> opnd = new StackManager<Operand>();
        //Operations stack - custom type
        StackManager<Opertoobj> oper = new StackManager<Opertoobj>();

        Opertoobj pnode1 = new Opertoobj('#', -100);
        oper.pushnode(pnode1);// this is the ignition key
        //actual expressions to parse
        char[] expre = {'a', '+', '(', 'd', '%', '(', 'c', '+', 'A', '*', 'b', ')', ')', '#'}; //don't forget the #

        while (expre[i] != '#') {
            System.out.println("Parsing " + expre[i]);
            //operand logic
            if (((expre[i] >= 'a') && (expre[i] <= 'z')) || ((expre[i] >= 'A') && (expre[i] <= 'Z'))) {
                System.out.println("This is Operand " + expre[i]);
                //create node for stack
                ivalu = findval(expre[i], varia, operand, 4);
                if (ivalu == null) {
                    System.out.println("No value in table for " + expre[i]);
                }
                //place value in stack
                System.out.println("Pushing to operand stack: " + ivalu);
                opnd.pushnode(ivalu);
            }//end variable stack
            //operator logic
            else {
                System.out.println("This is an operator " + expre[i]);
                if (expre[i] == '(') {
                    System.out.println("Pushing to operator Stack " + expre[i]);
                    //create node for stack - start pushing
                    Opertoobj pnodeo = new Opertoobj(expre[i], -99);
                    oper.pushnode(pnodeo);
                } else if (expre[i] == ')') {//start popping till partner
                    while ((oper.peeknode()).operators != '(') {
                        //The time has come to MATH!
                        popevalpush(oper, opnd);
                    }//now pop!
                    oper.popnode();
                } else {//everything else
                    oprior = findvalop(expre[i], operators, precedence, 4);
                    System.out.println("Peeking at top of stack " + (oper.peeknode()).precedence);
                    while (oprior <= (oper.peeknode()).precedence) popevalpush(oper, opnd);
                    System.out.println("Pushing Operator " + expre[i] + " with priority " + oprior);
                    Opertoobj pnodeo = new Opertoobj(expre[i], oprior);
                    oper.pushnode(pnodeo);
                }//end else
            }//end operator logic
            i++;
        }//end of expre1 loop. - Now do it again for expre2
        while ((oper.peeknode()).operators != '#') {
            popevalpush(oper, opnd);
        }
        exvalue = opnd.popnode();
        System.out.println("The Thrust is:" + exvalue.getB() + " and direction is: " + exvalue.getC());
        // Now do it again for expre2
    }//end of main

    //Evaluator for binary operations
    public static Operand IntEval(Operand oper1, char oper, Operand oper2) {
        //sent in
        double B1 = oper1.getB();
        double C1 = oper1.getC();
        double B2 = oper2.getB();
        double C2 = oper2.getC();
        //result ints
        double B3;
        double C3;
        Operand result;
        B3 = B1 + B2;
        C3 = C1 + C2;
        result = new Operand(B3, C3);
        System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
        return result;
}//end IntEval

    //find value of Operand number
    public static Operand findval(char x, char[] vtab, Operand[] valtb, int last) {
        //x is  the expression character, vtab is the variable equivalent, valtb is the matrix, last is the size of array
        int i; //counter
        Operand vreturn = new Operand(0,0); //default matrix value
        for (i = 0; i <= last; i++)
            if (vtab[i] == x)
                vreturn = valtb[i];
        System.out.println("Found this char " + x + ": value is " + vreturn);
        return vreturn;
    }//end findval
    //find value of order of operations
    public static int findvalop(char x, char[] vtab, int[] valtb, int last) {
        int i;
        int vreturn = -99;
        for (i = 0; i <= last; i++)
            if (vtab[i] == x)
                vreturn = valtb[i];
        System.out.println("Found this char " + x + ": value is " + vreturn);

        return vreturn;
    }//end findval
    public static void popevalpush(StackManager<Opertoobj> x, StackManager<Operand> y) {
        //start pop and push
        Operand a, b, c;
        char operandx;
        operandx = (x.popnode()).getopert();
        a = y.popnode();
        b = y.popnode();
        System.out.println("In Pop Evaluation " + b + operandx + a);
        c = IntEval(b, operandx, a);
        //push back
        y.pushnode(c);
        return;
    }//end popevalpush
}//End of Main Class

class Opertoobj {
    //hold operator class for character and priotity read in.
    protected char operators;
    protected int precedence;

    public Opertoobj(char opert, int pri) {
        operators = opert;
        precedence = pri;
    }//end constructor

    @Override
    public String toString() {
        String s = " " + operators;
        return s;
    }

    public int getprior() {
        return precedence;
    }

    public char getopert() {
        return operators;
    }
}

class Operand {
    protected double B;
    protected double C;

    public Operand(double B, double C) {
        this.B = B;
        this.C = C;
    }//end array constructor

    //call for calculation in IntEval

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    @Override
    public String toString() {
        String s = "\n( " + B + ", " + C + " )";
        return s;
    }
}

//THE PARCER
class StackManager<T> {
    protected ArrayList<T> myStack;
    protected int number;

    public StackManager() {
        //constructor class
        number = 0;//next value in array
        myStack = new ArrayList<T>(100); //Intial ArrayList. Should be big enough. Should be.
    }//end Constructor

    public int getnumber() {
        return number;
    }

    public int pushnode(T x) {
        //The Brain
        System.out.println(number + " in pushnode is: " + x);
        //push node on stack
        myStack.add(number, x);
        number++;
        System.out.println("Leaving Pushnode\n");
        return number;
    }//end pushnode

    public T popnode() {
        T nodeval;
        nodeval = myStack.get(number - 1);
        myStack.remove(number - 1);
        number--;
        return nodeval;
    }//end popnode

    public T peeknode() {
        //let the user see what's going on
        T nodeval;
        nodeval = myStack.get(number - 1);
        return nodeval;
    }//end peek

    boolean stackempty() {//if null
        if (number == 0) return true;
        else return false;
    }
}//end StackManager
