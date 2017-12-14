package hmwk08;
import java.util.Scanner;
/**
 *
 * @author Zachary
 */
public class Hmwk08 {
    public static void main(String[] args) {
        int countyCode; // Located in Andrew, Buchanan, or Nodaway Echo Print.
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
                + "\tOther                 000");
        countyCode = keyboard.nextInt();
        //validate county boolean, 
        boolean countyEligible=false;
          switch (countyCode){
            case 3: System.out.println("You entered county 003 (Andrew County).");  
                    countyEligible=true; 
                    break;
            case 5: System.out.println("You entered county 005 (Atchison County)"); 
                    break;
            case 21: System.out.println("You entered county 021 (Buchanan County).");
                    countyEligible=true; 
                    break;
            case 19: System.out.println("You entered county 019(Clinton  County)"); 
                    break;
            case 63: System.out.println("You entered county 063 (DeKalb County)."); 
                    break;
            case 145: System.out.println("You entered county 145 (Nodaway County)."); 
                    countyEligible=true;
                    break;
            case 165: System.out.println("You entered county 165 (Platte County)."); 
                    break;
            case 000: System.out.println("You entered county 000 (No County)."); 
                    break;
            default: System.out.println("Error: Unknown County Invalid input"); 
                    break;
        }
          
        //Input Number of employees.
 System.out.print("How many employees did you have as of July 1, 2011? ");
        employees = keyboard.nextInt();
        //Echo Print 
        
 boolean employeeEligible; // Number of employees at least 2, no more than 15
        employeeEligible = (employees >= 2 && employees <=15);
            System.out.println("You entered " + employees +".");
        
        //Input income. 
        System.out.print("What was your Net Income as shown on your 2011 Federal Income Tax (line 12)?");
        netIncome = keyboard.nextInt();
        
          boolean lowIncomeEligible; // At least 50000, but not 150000 or more
         lowIncomeEligible = (netIncome >= 50000 && netIncome < 150000);
        
           boolean highIncomeEligible; // Over 250000 but not more than 500000
           highIncomeEligible = (netIncome > 250000 && netIncome <=500000);
         
           boolean incomeEligible; // lowIncomeEligible or highIncomeEligible
            incomeEligible = (lowIncomeEligible || highIncomeEligible);
            System.out.println("You entered " + netIncome);
        
        boolean allCriteriaEligible; //Meets all criteria.
      allCriteriaEligible = (incomeEligible && employeeEligible && countyEligible);
        
            if( allCriteriaEligible){
                System.out.print("You are eligible for the grant!");
            }else{ 
                System.out.println("You are not eligible for the grant"); 
        }
    }
}