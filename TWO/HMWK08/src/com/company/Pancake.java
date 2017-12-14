package com.company;

/**
 * Created by Zachary on 11/14/2016.
 */
public class Pancake implements Comparable <Pancake> {
    private String flavor;
    private int calories;
    public Pancake (String flavor, int calories){
        setFlavor(flavor);
        setCalories(calories);
    }
    public Pancake (String flavor){
        this(flavor, 100);
    }
    public Pancake (){
        this("unknown", 100);
    }
    public void setFlavor(String flavor){
        this.flavor=flavor;
    }

    public String getFlavor(){
        return flavor;
    }

    public void setCalories(int calories){
        this.calories=calories;
    }

    public int getCalories(){
        return calories;
    }

    @Override
    public String toString(){
        String result = String.format( "Flavor:%s   Calories:%d", flavor, calories);
        return result;
    }
    @Override
    public int compareTo(Pancake other){
        int result=0;
        result=flavor.compareTo(other.flavor);
        return result;
    }
}
