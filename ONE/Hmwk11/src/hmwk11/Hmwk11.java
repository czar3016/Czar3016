package hmwk11;
/**
 *
 * @author zczarnecki
 */
import java.util.Scanner;
public class Hmwk11 {
    public static void main(String[] args) {
      
        Scanner input=new Scanner(System.in);
        int rows;
        boolean valid = false;
        do{
            System.out.print("Enter a number from one to nine:");
            rows = input.nextInt();
            if(rows > 0 && rows <= 9) valid=true;
            /*{} not needed in one line ifs*/
            
        }while(!valid); /*!falseified*/
        System.out.println("You entered a : " + rows +".");
        
        System.out.println("\nPattern 1:"); 
            for(int i = 1; i <= rows; i++){
                for(int j = 1; j <= rows; j++){
                    System.out.printf("%2d",j); /*makes a box of numbers*/
                }
                System.out.println();
            }
        
        System.out.println("\nPattern 2:"); 
            for(int i = 1; i <= rows; i++){
                for(int j = 1; j <= rows; j++){
                    System.out.printf("%2d",i); 
                }
                System.out.println();
            }
        
        System.out.println("\nPattern 3:");
            for(int i = 1; i <= rows; i++){
                for(int j = i; j <= rows; j++){ /*stairs*/
                    System.out.printf("%2d",j); 
                }
                System.out.println();
            }
        
        System.out.println("\nPattern 4:");
            for(int i = 1; i <= rows; i++){
                for(int j = 1; j <= i; j++){ /*flips stairs*/
                    System.out.printf("%2d",j); 
                }
                System.out.println();
            }
        
        System.out.println("\nPattern 5:");
            for(int i = 0; i <= rows; i++){ /*C*/
                for(int j = rows-i; j > 0; j--){ /*C*/
                    System.out.printf("%2d",j); 
                }
                System.out.println();
            }

    }
}

