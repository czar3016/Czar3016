package com.company;

/**
 * Created by Zachary on 9/28/2016.
 */
public class Nams {
    String First;
    String Last;

    public Nams (String First, String Last){
        setFirst(First);
        setLast(Last);
    }
    public void setFirst(String First){
        this.First=(First.length()> 0) ? First: "John";
    }
    public void setLast (String Last){
        this.Last=(Last.length()> 0) ? Last: "Doe";
    }
    public String getFirst(){
        return this.First;
    }
    public String getLast(){
        return this.Last;
    }
    @Override
    public String toString(){
        String result=String.format("First Name: %s  Last Name: %s", First, Last);
        return result;
    }
}
