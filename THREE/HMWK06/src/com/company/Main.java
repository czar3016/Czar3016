/*Zachary Czarnecki HMWK05 Part 2
* Parse a Matrix equation using chars and custom class arrays. Class 'Operand' takes in individual matrices, denoted by
* letters, as well as converts individual numbers in matrices for easier calculation with a second constructor. IntEval
* is now set up to separate the matrices as they are fed in by expre1, and then re-compiled as result is made a new
* instance of the Operand class. Popping and pushing work on the same logic in the StackManager class that they did in
* Part 1*/
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
        int i = 0; //for keeping count
        Operand ivalu, exvalue; //return values
        //make new classes to read in operands as arrays
        Operand A = new Operand(1, 2, 3, 4);
        Operand B = new Operand(6, 6, 8, 8);
        Operand C = new Operand(1, 2, 2, 1);
        Operand zero = new Operand(0);
        Operand one = new Operand(1);
        Operand two = new Operand(2);
        Operand three = new Operand(3);
        Operand four = new Operand(4);
        Operand five = new Operand(5);
        Operand six = new Operand(6);
        Operand seven = new Operand(7);
        Operand eight = new Operand(8);
        Operand nine = new Operand(9);
        //array for operand letter call
        char[] varia = {'A', 'B', 'C', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        //array for operand integer values
        Operand[] operand = {A, B, C, zero, one, two, three, four, five, six, seven, eight, nine};
        //array of operations - arranged by precedence
        char[] operators = {'/', '*', '+', '-', ')', '(', '#'}; //#=exit
        //array for order of operations calculation
        int[] precedence = {2, 2, 1, 1, 99, -99, 100};
        //Operands array - int
        StackManager<Operand> opnd = new StackManager<Operand>();
        //Operations stack - custom type
        StackManager<Opertoobj> oper = new StackManager<Opertoobj>();

        //test operator
        System.out.println("Pushing Operator with priority - 100\n");
        Opertoobj pnode1 = new Opertoobj('#', -100);
        oper.pushnode(pnode1);
        int oprior;

        //actual expressions to parse
        char[] expre1 = {'2', '*', 'A', '-', '3', '*', '(', '(', 'B', '-', '2', '*', 'C', ')', '/', '(', 'A', '+', '3',
                ')', '-', 'B', '*', '3', ')', '#'}; //don't forget the #

        while (expre1[i] != '#') {
            System.out.println("Parsing " + expre1[i]);
            //operand logic
            if (((expre1[i] >= '0') && (expre1[i] <= '9')) || ((expre1[i] >= 'A') && (expre1[i] <= 'Z'))) {
                System.out.println("This is Operand " + expre1[i]);
                //create node for stack
                ivalu = findval(expre1[i], varia, operand, 12);
                if (ivalu == null) {
                    System.out.println("No value in table for " + expre1[i]);
                }
                //place value in stack
                System.out.println("Pushing to operand stack: " + ivalu);
                opnd.pushnode(ivalu);
            }//end variable stack
            //operator logic
            else {
                System.out.println("This is an operator " + expre1[i]);
                if (expre1[i] == '(') {
                    System.out.println("Pushing to operator Stack " + expre1[i]);
                    //create node for stack - start pushing
                    Opertoobj pnodeo = new Opertoobj(expre1[i], -99);
                    oper.pushnode(pnodeo);
                } else if (expre1[i] == ')') {//start popping till partner
                    while ((oper.peeknode()).operators != '(') {
                        //The time has come to MATH!
                        popevalpush(oper, opnd);
                    }//now pop!
                    oper.popnode();
                } else {//everything else
                    oprior = findvalop (expre1[i], operators, precedence, 5);
                    System.out.println("Peeking at top of stack " + (oper.peeknode()).precedence);
                    while (oprior <= (oper.peeknode()).precedence) popevalpush(oper, opnd);
                    System.out.println("Pushing Operator " + expre1[i] + " with priority " + oprior);
                    Opertoobj pnodeo = new Opertoobj(expre1[i], oprior);
                    oper.pushnode(pnodeo);
                }//end else
            }//end operator logic
            i++;
        }//end of expre1 loop. - Now do it again for expre2
        while ((oper.peeknode()).operators != '#') {
            popevalpush(oper, opnd);
        }
        exvalue = opnd.popnode();
        System.out.println("The value of the Expresseion is " + exvalue);
        // Now do it again for expre2
    }//end of main

    //Evaluator for binary operations
    public static Operand IntEval(Operand oper1, char oper, Operand oper2) {
        //sent in
        int A1 = oper1.get11();
        int B1 = oper1.get12();
        int C1 = oper1.get21();
        int D1 = oper1.get22();
        int A2 = oper2.get11();
        int B2 = oper2.get12();
        int C2 = oper2.get21();
        int D2 = oper2.get22();
        //result ints
        int A3;
        int B3;
        int C3;
        int D3;
        Operand result;
        switch (oper) {
            case '+':
                A3=A1+A2;
                B3=B1+B2;
                C3=C1+C2;
                D3=D1+D2;
                result= new Operand(A3, B3, C3, D3);
                System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                return result;
            case '-':
                A3=A1-A2;
                B3=B1-B2;
                C3=C1-C2;
                D3=D1-D2;
                result= new Operand(A3, B3, C3, D3);
                System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                return result;
            case '*':
                A3=A1*A2;
                B3=B1*B2;
                C3=C1*C2;
                D3=D1*D2;
                result= new Operand(A3, B3, C3, D3);
                System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                return result;
            case '/':
                if (A2 != 0 && B2 != 0 && C2 != 0 && D2 != 0 ) {
                    A3=A1/A2;
                    B3=B1/B2;
                    C3=C1/C2;
                    D3=D1/D2;
                    result= new Operand(A3, B3, C3, D3);
                    System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                    return result;
                }//end if
                else {//cannot devide by 0
                    System.out.println("Divide by zero not allowed");
                    return null;
                }
            default:
                System.out.println("Invalid Operator " + oper);
                return null;
        }//end switch
    }//end IntEval

    //find value of Operand number
    public static Operand findval(char x, char[] vtab, Operand[] valtb, int last) {
       //x is  the expression character, vtab is the variable equivalent, valtb is the matrix, last is the size of array
        int i; //counter
        Operand vreturn = new Operand(0); //default matrix value
        for (i = 0; i <= last; i++)
            if (vtab[i] == x)
                vreturn = valtb[i];
        System.out.println("Found this char " + x + ": value is " + vreturn);

        return vreturn;
    }//end findval

    //find value of order of operations
    public static int findvalop (char x, char[] vtab, int[] valtb, int last) {
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
    int[][] delta;//return array
    protected int A11;
    protected int A12;
    protected int A21;
    protected int A22;
    char varia;
    protected boolean constant;

    public Operand(int A, int B, int C, int D) {
        A11 = A;
        A12 = B;
        A21 = C;
        A21 = D;
    }//end array constructor

    public Operand(int A) {
        A11 = A;
        A12 = A;
        A21 = A;
        A22 = A;
    }
    //call for calculation in IntEval
    public int get11() {
        return A11;
    }

    public int get12() {
        return A12;
    }

    public int get21() {
        return A21;
    }

    public int get22() {
        return A21;
    }

    @Override
    public String toString() {
        String s = "\n| " + A11 + " " + A12 + " |\n| " + A21 + " " + A22 + " |";
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
        //poping off node
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


