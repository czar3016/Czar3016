package hmwk12;
/**
 *
 * @author Zachary
 */
import java.util.Scanner;
public class Hmwk12 {
    public static void main(String[] args) {
        double loanprinciple;
        double years;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Loan Amount: ");
        loanprinciple = keyboard.nextDouble();
        System.out.print("Number of Years: ");
        years = keyboard.nextDouble(); 
        double interest = 5;
        System.out.println("Interest Rate    Monthly Payment   Total Payment");
        while (loanprinciple >= 0 && interest <= 8){
            double monthlypayment = loanprinciple * interest / ( 1 - 1 / Math.pow(1 
                   + interest, years * 12));
            double totalPayment = monthlypayment * years * 12;
            System.out.printf("%-17.3f", interest);
            System.out.printf("%-18.2f", monthlypayment);
            System.out.printf("%1.2f\n", totalPayment);
            interest += .125;
        }  
    }
}
    