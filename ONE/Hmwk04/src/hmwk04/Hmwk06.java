
package hmwk04;
import java.util.Scanner;
/**
 *
 * @author Zachary
 */
public class Hmwk06 {
    public static void main(String[] args) {
        double A;  //Ax^2
        double B; //Bx
        double C; //C
        Scanner input = new Scanner(System.in);
        //input answers
        System.out.print("What is the value of A? ");
        A=input.nextDouble();
        System.out.print("What is the value of B? ");
        B=input.nextDouble();
        System.out.print("What is the value of C? ");
        C=input.nextDouble();
        //Quadratic equation(s)
        double twodividepositive, twodividenegative;
        double discriminant = Math.pow(B,2) - 4 * A * C;
        twodividepositive = (-B+Math.sqrt(discriminant))/(2*A);
        // dividing -b+sqrt by 2a
        twodividenegative= (-B-Math.sqrt(discriminant))/(2*A);
        // dividing -b-sqrt by 2a
        
        //Report the results
        if(discriminant > 0){
        //display two solutions
        System.out.println(" The roots are ("+twodividenegative+","+twodividepositive+").");
        }else if(discriminant == 0){
        //display one solution because same answer
        System.out.println("The single root, "+twodividenegative+"."); 
        }else if(discriminant < 0){
        //display message saying no real roots
        System.out.println("There are no real roots");
        }
    }
}