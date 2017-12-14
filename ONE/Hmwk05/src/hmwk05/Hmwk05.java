package hmwk05;

/**
 *
 * @author Zachary
 */
public class Hmwk05 {
    public static void main(String[] args) {
        // Declare letters
        char letter1 = 'd';
        char letter2 = (char)101;
        char letter3 = (char)114;
        char letter4 = (char)112;
        char letter5 = (char)123;
        String word ="";
        char lower, upper;
        int conver;
        //[
        lower = letter5;
        conver = (int) lower;
        conver-=32;
        upper = (char) conver;
        word+=upper; 
       //D
        lower = letter1;
        conver = (int) lower;
        conver-=32;
        upper = (char) conver;
        word+=upper;
        //E
        lower = letter2;
        conver = (int) lower;
        conver-=32;
        upper = (char) conver;
        word+=upper; 
        //R
        lower = letter3;
        conver = (int) lower;
        conver-=32;
        upper = (char) conver;
        word+=upper; 
        //P
        lower = letter4;
        conver = (int) lower;
        conver-=32;
        upper = (char) conver;
        word+=upper; 
        System.out.println("The word is "+word);
    }
}