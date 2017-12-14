/*Zachary Czarnecki HMWK05 Part 2
*
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

    public static void main(String[] args) {
        int i, num, ivalu; //for keeping count
        //array for operand letter call
        char[] varia = {'A', 'B', 'C', 'D', 'E', 'F', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        //array for operand integer values
        int[] operand = {8, 12, 2, 3, 15, 4, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        //array of operations - arranged by precedence
        char[] operators = {'@', '%', '/', '*', '+', '-', ')', '(', '#'}; //@=^ and #=exit
        //array for order of operations calculation
        int[] precedence = {3, 2, 2, 2, 1, 1, 99, -99, 100};
        //Operands array - int
        StackManager<Integer> opnd = new StackManager<Integer>();
        //Operations stack - custom type
        StackManager<Opertoobj> oper = new StackManager<Opertoobj>();

        System.out.println("Pushing Operator with priority - 100\n");
        //test operator
        Opertoobj pnode1 = new Opertoobj('#', -100);
        oper.pushnode(pnode1);
        int oprior, exvalue;

        //actual expressions to parse
        char[] expre1 = {'A', '@', '(', '2', '*', '(', 'A', '-', 'C', '*', 'D', ')', '+', '(', '9', '*', 'B', '/', '(', '2', '*', 'C', '+', '1',
                ')', '-', 'B', '*', '3', ')', '+', 'E', '%', 'F', '-', 'A', ')', '#'}; //don't forget the #
        i = 0;
        while (expre1[i] != '#') {//while loop will be written twice, once for each expression
            System.out.println("Parsing " + expre1[i]);
            //operand logic
            if (((expre1[i] >= '0') && (expre1[i] <= '9')) || ((expre1[i] >= 'A') && (expre1[i] <= 'Z'))) {
                System.out.println("This is Operand " + expre1[i]);
                //create node for stack
                ivalu = findval(expre1[i], varia, operand, 14);
                if (ivalu == -99) {
                    System.out.println("No value in table for " + expre1[i]);
                }
                //place value in stack
                System.out.println("Pushing to operand stack: " + ivalu);
                opnd.pushnode(ivalu);
            }//end variable stack
            else {//operator logic
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
                    oprior = findval(expre1[i], operators, precedence, 5);
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
    public static int IntEval(int oper1, char oper, int oper2) {
        int result;
        switch (oper) {
            case '+':
                result = oper1 + oper2;
                System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                return result;
            case '-':
                result = oper1 - oper2;
                System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                return result;
            case '*':
                result = oper1 * oper2;
                System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                return result;
            case '/':
                if (oper2 != 0) {
                    result = oper1 / oper2;
                    System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                    return result;
                }//end if
                else {//cannot devide by 0
                    System.out.println("Divide by zero not allowed");
                    return -99;
                }
            case '@':
                if (oper2 == 0) {
                    result = 1;
                    System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                    return result;
                }//end if
                else {//cannot devide by 0
                    result = (int) Math.pow((double) oper1, (double) oper2); //cast as int to function properly
                    System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                    return result;
                }
            case '%':
                if (oper2 != 0) {
                    result = oper1 % oper2;
                    System.out.println("\n Eval: " + oper1 + oper + oper2 + " = " + result + "\n");
                    return result;
                }//end if
                else {//cannot devide by 0
                    System.out.println("Divide by zero not allowed");
                    return -99;
                }
            default:
                System.out.println("Invalid Operator " + oper);
                return -99;
        }//end switch
    }//end IntEval

    public static int findval(char x, char[] vtab, int[] valtb, int last) {
        int i, vreturn = -99;
        for (i = 0; i <= last; i++)
            if (vtab[i] == x)
                vreturn = valtb[i];
        System.out.println("Found this char " + x + ": value is " + vreturn);

        return vreturn;
    }//end findval

    public static void popevalpush(StackManager<Opertoobj> x, StackManager<Integer> y) {
        //start pop and push
        int a, b, c;
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

//THE PARCER
class StackManager<T> {
    protected ArrayList<T> myStack;
    protected int number;

    public StackManager() {
        //constructor class
        number = 0;//next value in array
        myStack = new ArrayList<T>(100); //Intial ArrayList. Should be big enough. Should be.
    }//end ArrayList

    public int getnumber() {
        return number;
    }

    public int pushnode(T x) {
        //The Brain
        System.out.println(number + " in pushnode is x: " + x);
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