package hmwk07;

/**
 *
 * @author Zachary
 */
import java.util.Scanner;

public class Hmwk07 {
    public static void main(String[] args) {
        int countyCode;   //Federal FIPS code for Missouri Counties
        int employees;     //Number of employees
        int netIncome;     //Last year's net Income as shown on Federal 1040.
        Scanner keyboard = new Scanner(System.in);

        //Input data
        System.out.println("What is the county code?\n"
                + "\tAndrew County         003\n"
                + "\tAtchison County       005\n"
                + "\tBuchanan County       021\n"
                + "\tClinton County        019\n"
                + "\tDeKalb County         063\n"
                + "\tNodaway County        145\n"
                + "\tPlatte County         165\n"
                + "\tOther                000");
        countyCode = keyboard.nextInt();
        
        //Echo Print switch case. Terminate if invalid
        switch (countyCode){
            case 3: System.out.println("You entered county 003 (Andrew County)."); 
            break;
            case 5: System.out.println("You entered county 005 (Atchison County)"); 
            break;
            case 21: System.out.println("You entered county 021 (Buchanan County)."); 
            break;
            case 19: System.out.println("You entered county 019(Clinton  County)"); 
            break;
            case 63: System.out.println("You entered county 063 (DeKalb County)."); 
            break;
            case 145: System.out.println("You entered county 145 (Nodaway County)."); 
            break;
            case 165: System.out.println("You entered county 165 (Platte County)."); 
            break;
            default: System.out.println("You entered county 000 (No County)."); 
            break;
        }

        //Input Number of employees. Terminate if invalid
        System.out.print("How many employees did you have as of July 1, 2011? ");
        employees = keyboard.nextInt();
        //Echo Print
        System.out.println("You entered " + employees +".");

        
        //Input income. Terminate if invalid
        System.out.print("What was your Net Income as shown on your 2011 Federal Income Tax (line 12)?");
        netIncome = keyboard.nextInt();
        //Echo Print 
       System.out.println("You entered " + netIncome +".");
      if (countyCode == 3||countyCode == 21||countyCode == 145 ){
       if (employees >= 2 && employees <=15) {
        if (netIncome >= 50000 && netIncome <150000) {
        }
        else if (netIncome > 250000 && netIncome <=500000) {
        }
       }
      }     
      else {
      System.out.println("Sorry, you are not eligible.");
      System.exit(1);
        }
      System.out.println("You are eligible for the grant. Congratulations.");    
      
    }
}