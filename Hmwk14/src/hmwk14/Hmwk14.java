package hmwk14;
/**
 *
 * @author Zachary
 */ 
import java.io.*;
import java.util.Scanner;
public class Hmwk14 {
    public static void main(String[] args) {
        double[] scores=new double[20];
        int n=readScores(scores);
        double min=getMinScores(scores, n);
        double max=getMaxScores(scores, n);
        double avg=getAverageScore(scores, n, max, min);
        printArray(scores, n);
        System.out.printf("Number of entries read is: %d\n", n);
        System.out.printf("Max score read is: %3.3f\n", max);
        System.out.printf("Min score read is: %3.3f\n", min);
        printstatistics(min, max, avg);
    }
    public static void printArray (double[] scoresIn, int nIn){
        for(int i=0; i< nIn; i++){
        System.out.printf("scores%2d=%3.3fn");
        }
    }
    public static int readScores(double[]scoresIn){
        int rv=0;
        try{
            Scanner input=new Scanner (new FileReader ("src/hmwk14/scores.txt"));
            for(int i=0; i < scoresIn.length; i++){
                scoresIn[i]=0;
                if(input.hasNext()){
                    rv++;
                    scoresIn[i]=input.nextDouble();
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.toString());
        }
        return rv;
    }
    public static double getMinScores(double[] scoresIn, int nIn){
        double rv = scoresIn[0];
        for(int i=1; i < nIn; i++){
            if(rv > scoresIn[i])rv=scoresIn[i];
        }
        return rv;
    }
    public static double getMaxScores(double[] scoresIn, int nIn){
        double rv = scoresIn[0];
        for(int i=1; i < nIn; i++){
            if(rv < scoresIn[i])rv=scoresIn[i];
        }
        return rv;
    }
    public static double getAverageScore (double[]scoresIn, int nIn, double lowScoreIn, double highScoreIn){
       double rv; 
       double sum=0;
       for(int i=0; i<nIn; i++){
           sum += scoresIn[i];   
       }
       sum-=(lowScoreIn + highScoreIn);
       rv = sum/(nIn-2);
       return rv;
    }
    public static void printstatistics (double minIn, double maxIn, double avgIn){
        System.out.printf("Min Score is %3.3f\n", minIn);
        System.out.printf("Max Score is %3.3f\n", maxIn);
        System.out.printf("Average Score is %3.3f\n", avgIn);
    }
}
