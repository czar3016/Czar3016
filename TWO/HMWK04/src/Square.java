/**
 * Created by Zachary on 9/26/2016.
 */
public class Square {
    private double length;
    private double width;
    public Square(double len, double wid){
        length=len;
        width=wid;
    }
    public void printRectangle(){
        System.out.printf("%f length and %f for width\n", length, width);
    }
}
