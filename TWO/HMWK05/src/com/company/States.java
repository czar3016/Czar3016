package com.company;
import java.io.*;
import java.util.Scanner;
/**
 * Created by Zachary on 10/17/2016.
 If the record has 7 fields, put the record into a class.

 If the state matches the command line, and fewer than the maximum number of items have been printed, then print the file name.*/
public class States {
    String name;
    String city;
    String state;
    String Latitude;
    String Longitude;
    public States ( String name, String city, String state, String Latitude, String Longitude){
        this.name=name;
        this.city=city;
        this.state=state;
        this.Latitude=Latitude;
        this.Longitude=Longitude;
    }
    @Override
    public String toString(){
        String result = String.format("%s, %s, %s, %s, %s", name, city, state, Latitude, Longitude);
        return result;
    }
}
