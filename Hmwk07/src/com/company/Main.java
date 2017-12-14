/*Zachary Czarnecki
HMWK07
create a program to calculate the factorial of any number greater than 0 using BigInteger*/
package com.company;
import java.math.BigInteger;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        int pick;
        BigInteger n;
        BigInteger nFactorial;
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("Enter an integer greater than 0: ");
            pick=input.nextInt();
        }while(pick<0);
        n=new BigInteger(new Integer(pick).toString());
        nFactorial = factorial(n);
        System.out.printf("%d! is %d\n", n, nFactorial);
    }
    public static BigInteger factorial(BigInteger n){
        BigInteger result = BigInteger.ONE;
        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return result;
    }
}
