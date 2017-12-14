package hmwk17;
/**
 *
 * @author Zachary
 */
public class Hmwk17 {
    public static void main(String[] args) { 
        int twentyFour = 0;    
        int[] deck = new int [52];
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        //initialize the deck
        for(int i=0; i < deck.length; i++) deck[i] = i;

        for(int i = 0; i <= 99; i++){
            shuffle(deck);
            int sumOfFour = 0;
            //pick 4
            for (int j = 0; j < 4; j++){
                int card = deck[j];
                String rank = ranks[card % 13];
                sumOfFour += getCardValue(rank);
            }            
            //check the sum
            if(sumOfFour == 24){
                for (int j = 0; j < 4; j++){
                    String rank = ranks[deck[j] % 13];
                    System.out.println("Card number: " + rank + ".");
                }  
                System.out.println(" ");
                twentyFour++;
            }
        }
        System.out.println("Total times 24 reached:" + twentyFour); 
    }
    public static void shuffle(int[] deck){  
        for(int i=0; i < deck.length; i++){
            int index = (int)(Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
    }     
    public static int getCardValue(String rankIn){
        int cv = 0;
        switch(rankIn){
            case "Ace": cv = 1; break;
            case "2": cv = 2; break;
            case "3": cv = 3; break;
            case "4": cv = 4; break;
            case "5": cv = 5; break;
            case "6": cv = 6; break;
            case "7": cv = 7; break;
            case "8": cv = 8; break;
            case "9": cv = 9; break;
            case "10": cv = 10; break;
            case "Jack": cv = 11; break;
            case "Queen": cv = 12; break;
            case "King": cv = 13; break;
        }
        return cv;
    }
}
