/*CSC-254 HMWK 1
 Zachary Czarnecki
Reads three real numbers from a line and prints out what is the smallest, middle, largest, and the sum of those
numbers
*/

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double a, b, c;
            Scanner input = new Scanner(System.in);

            System.out.print("Enter three integers and hit Enter ");
            a = input.nextDouble();
            b = input.nextDouble();
            c = input.nextDouble();

            //echo print
            System.out.printf("Checking values %f, %f, and %f\n", a, b, c);

            System.out.printf("The smallest value is %f\n", findSmallest(a,b,c));
            System.out.printf("The largest value is %f\n",findLargest(a,b,c));
            System.out.printf("The middle value is %f\n",findMiddle(a,b,c));
            System.out.printf("The sum of %f, %f, and %f is %f.", a, b, c, calculateSum(a,b,c));
    }

    public static double findSmallest (double a, double b, double c){
        double result = 0;

        if (a <= b && a <= c) {
            result = a;
        }
        else if (b <= c && b <= a){
            result = b;
        }
        else{
            result = c;
        }
        return result;
    }
    public static double findLargest(double a, double b, double c){
        double result = 0;

        if (a >= b && a >= c) {
            result = a;
        }
        else if (b >= c && b >= a){
            result = b;
        }
        else{
            result = c;
        }
        return result;
    }
   public static double findMiddle(double a, double b, double c){
       double result = 0;

       if (a <= b && a >= c || a >= b && a <= c){
           result = a;
       }
       if (b <= a && b >= c || b >= a && b <= c) {
           result = b;
       }
       else{
           result = c;
       }
       return result;
   }
    public static double calculateSum(double a, double b, double c){
        double result = a + b + c;
        return result;
    }

}
