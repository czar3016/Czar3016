package hmwk16;
/**
 *
 * @author Zachary
 */
import java.util.Scanner;
public class Hmwk16 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int lines;
        boolean valid = false;
        do{
            System.out.print("Enter a number of linesfrom one to fifteen:");
            lines = input.nextInt();
            if(lines > 0 && lines <= 15) valid=true;
        }while(!valid); /*!falseified*/
        
        for (int i = 1; i <= lines; i++) {
            for (int j = lines; j > i; j--) {
               System.out.print("  ");
            }
            for (int j = i; j > 1; j--) {
                System.out.printf("%2d",j);
            }
            for (int j = 1; j <= i; j++) {
                System.out.printf("%2d",j);
            }
            System.out.println();
        }
        
    }
    public static void drawPyramide(int lines, char symbol, boolean startDown) {
    //TRIANGLE

    if(startDown) {
                //The triangle up side down should be here. 
            }

    else {
        int c = 1;
        for (int i = 0; i < lines; i++) {
            for (int j = i; j < lines; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= c; k++) {
                if (k%2==0) System.out.print(" ");

                else System.out.print(symbol);
            }

        System.out.print("\n");
        c += 2;
        }
    }

}
}
