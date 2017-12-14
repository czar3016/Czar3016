
package exam01;
import java.util.Scanner;
/**
 *
 * @author Zachary
 */
public class Exam01 {
    public static void main(String[] args) {
        double length;  
        double width;
        Scanner input = new Scanner(System.in);
        //enter length        
        System.out.print("Enter the length of a rectangle.");
        length=input.nextDouble();
       //enter width
        System.out.print("Enter the width of a rectangle.");
        width=input.nextDouble();
        //computing Area
        double area =length*width;
        //computing perimeter
        double perimiter=(2*length) + (2*width);
       //User Input
        System.out.print("Length:" + length+"   "); 
        System.out.print("Width:" + width+"   ");
        //Results
        System.out.print("Area:" + area+"   ");
        System.out.print("Perimeter:" + perimiter);
    }
    
}
