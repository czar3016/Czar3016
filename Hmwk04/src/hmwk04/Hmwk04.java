
package hmwk04;
import java.util.Scanner;
/**
 *
 * @author Zachary
 */
public class Hmwk04 {
    public static void main(String[] args) {
        double A;  //x^A
        double B; 
        double C;  
        Scanner input = new Scanner(System.in);
        System.out.print("What is the value of A? ");
        A=input.nextDouble();
        System.out.print("What is the value of B? ");
        B=input.nextDouble();
        System.out.print("What is the value of C? ");
        C=input.nextDouble();
        double twodividepositive, twodividenegative;//Quadratic equation(s)
        double discriminant = Math.pow(B,2) - 4 * A * C;
        twodividepositive = (-B+Math.sqrt(discriminant))/(2*A);
        // dividing -b+sqrt by 2a
        twodividenegative= (-B-Math.sqrt(discriminant))/(2*A);
        // dividing -b-sqrt by 2a
        //Report the results
        if(discriminant > 0){
        //display two solutions
        System.out.println(A+"x^2 +"+B+"x +"+C+"= ("+twodividenegative+","+twodividepositive+").");
        }else if(discriminant == 0){
        //display one solution because same answer
        System.out.println(A+"x^2 +"+B+"x +"+C+"="+twodividenegative+"."); 
        }else if(discriminant < 0){
        //display message saying no real roots
        System.out.println("There are no real roots");
        }
    }
}