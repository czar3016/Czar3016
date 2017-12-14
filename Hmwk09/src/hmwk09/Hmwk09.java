
package hmwk09;
import java.util.Scanner;
/**
 *
 * @author Zachary
 */
public class Hmwk09 {

    public static void main(String[] args) {
        //establish inputs
        double number;
        
        Scanner keyboard = new Scanner(System.in);
       //do while
        do{
           System.out.print("Enter a number (sytem ends if number is negative): ");
           number = keyboard.nextDouble();
           
           System.out.printf("The square root is " + Math.sqrt(number)+"\n");
        } while (number >= 0); 
    }  
}
