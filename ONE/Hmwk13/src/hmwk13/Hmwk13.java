package hmwk13;
import java.util.Scanner;
/**
 *
 * @author zczarnecki
 */
public class Hmwk13 {
    public static Scanner input = new Scanner (System.in);
    public static void main(String[] args) {
        double length = getSide("length");
        double width = getSide("width");//%s
        double area = calcArea(length, width);
        printRectangleInfo(length, width, area); //this method goes in this order
    }
    public static double getSide(String sideIn){         
        double rv; //rv = return value
        do{
            System.out.printf("How long is the %s of the rectangle? ", sideIn);       
            rv = input.nextDouble();
        }while(rv <= 0);
        return rv;
    }
    public static double calcArea(double lengthIn, double widthIn){ 
        double rv = lengthIn * widthIn;
        return rv;
    }
    public static void printRectangleInfo(double lengthIn, double widthIn, 
    double areaIn){ 
        System.out.printf("A rectangle with a length of %2.2f and a width of "
                + "%2.2f would have an area of %2.2f square units.\n", 
                lengthIn, widthIn, areaIn);
    }   

}
