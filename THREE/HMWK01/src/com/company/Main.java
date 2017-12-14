/*Zachary Czarnecki
* This program creates an array of an object called Solids; which come in 5 types: Cubes, Spheres, Cones, Bricks, and
* Truncated Cones. Each has three int variables assigned to them: len for length, wid for width, and hig for height; and
 * 2 protected. calculated doubles: volume and area. Through parenting with classes Solids, point, and the abstract
 * Shapes. the functions calcarea and calcvol calculate the area and volume of each 3D shape, respectivly. These are fed
 * back to the main function and used in arrays to display each shapes individual data; as well as the collective area
 * and volume of all shapes; additionally displaying the the maximum and minimum instance of each respective calculation.
 * Finally, the Solids array MySolids is put into the ArrayList ORDER and sorted largest to smallest by the function
 *sortvolume by volume of the shape. The array is then re-printed, sorted. */
package com.company;

import java.io.*;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.NoSuchElementException;
import java.util.Collections;

public class Main {

    public static void main(String[] args)throws Exception {
	    PrintWriter outpt;
        //equate internal name to an external file via PrintWriter
        outpt=new PrintWriter(new File("JavaAbsOut.txt"));
        int i;
        double area = 0; // blank area call
        double volume = 0; //blank volume call
        //create Solids - Cubes, Spheres, Cones, Bricks, and Truncated Cones
        Cubes Qube=new Cubes(4,4,4,outpt,4,4,4);
        Cubes Kube=new Cubes(8,8,8,outpt,8,8,8);
        Spheres Cir=new Spheres(6,6,6,outpt,6,6,6);
        Spheres Ball=new Spheres(3,3,3,outpt,3,3,3);
        Cones Con=new Cones(5,6,6,outpt,5,6,6);
        Cones Kahn=new Cones(3,12,12,outpt,3,12,12);
        Bricks Bri=new Bricks(3,6,9,outpt,3,6,9);
        Bricks Hause=new Bricks(2,4,6,outpt,2,4,6);
        TCones Tee=new TCones(5,6,3,outpt,5,6,3);
        TCones Kone=new TCones(8,4,6,outpt,8,4,6);
        //Build Array
        Solids[]MySolids=new Solids[10];
        MySolids[0]=Qube;
        MySolids[1]=Kube;
        MySolids[2]=Cir;
        MySolids[3]=Ball;
        MySolids[4]=Con;
        MySolids[5]=Kahn;
        MySolids[6]=Bri;
        MySolids[7]=Hause;
        MySolids[8]=Tee;
        MySolids[9]=Kone;
        ArrayList<Solids> ORDER=new ArrayList<>();//We will use this ArrayList to sort later

        //Now calculate volume and areas
        for(i=0;i<=9;i++) {
            MySolids[i].calcarea();
            MySolids[i].calcvol();
            ORDER.add(MySolids[i]); //Fill the ArrayList
        }
        //load arrays of Areas and volumes
        double[] Suma=new double [10];
        Suma[0]=Qube.getarea();
        Suma[1]=Kube.getarea();
        Suma[2]=Cir.getarea();
        Suma[3]=Ball.getarea();
        Suma[4]=Con.getarea();
        Suma[5]=Kahn.getarea();
        Suma[6]=Bri.getarea();
        Suma[7]=Hause.getarea();
        Suma[8]=Tee.getarea();
        Suma[9]=Kone.getarea();
        double[] Sumv=new double [10];
        Sumv[0]=Qube.getvol();
        Sumv[1]=Kube.getvol();
        Sumv[2]=Cir.getvol();
        Sumv[3]=Ball.getvol();
        Sumv[4]=Con.getvol();
        Sumv[5]=Kahn.getvol();
        Sumv[6]=Bri.getvol();
        Sumv[7]=Hause.getvol();
        Sumv[8]=Tee.getvol();
        Sumv[9]=Kone.getvol();
        //sum volume and area arrays
        for (i=0;i<=9;i++){
            area+=Suma[i];
            volume+=Sumv[i];
        }
        // Print Array, calculations, Max and Mins, Sort
        for(i=0;i<=9;i++){
            MySolids[i].print(outpt);
        }
        System.out.println("Total area of solids is "+area+", and total volume is "+volume);
        System.out.printf("Max area is %f\n", maxValue(Suma));//Summon Max Area
        System.out.printf("Minimum volume is %f\n", minValue(Sumv));//Summon Min Volume
        sortvolume(ORDER);// must sort with Array List
        System.out.println("\nSorted Solids by Volume (largest to smallest):\n"); //formatting
        System.out.println(ORDER);
    }
    //find Maximum Area
    public static double maxValue(double[] maxs) {
        double current = maxs[0];
        for (int i= 0; i< maxs.length; i++) {
            if (maxs[i] > current) { // comparing entries
                current = maxs[i];
            }
        }
        return current;
    }
    //find Minimum Volume
    public static double minValue(double[] mins) {
        double current = mins[0];
        for (int i= 0; i< mins.length; i++) {
            if (mins[i] < current) { //comparing entries
                current = mins[i];
            }
        }
        return current;
    }
    //Sort class
    public static void sortvolume(ArrayList<Solids> mylist){
// this will sort Solids based on volume
        Solids xsave, ysave;
        double isw=1, xlast=mylist.size();
        while (isw==1){
            isw=0;
            for(int i=0; i<=xlast-2;i++){
                if(((mylist.get(i)).compareTo((mylist.get(i+1)))==1)){
                    xsave=mylist.get(i);
                    ysave=mylist.get(i+1);
                    mylist.remove(i); //note that it must be temp. removed
                    mylist.add(i, ysave);
                    mylist.remove(i+1);
                    mylist.add(i+1, xsave);
                    isw=1;
                }//endif
            }//end for
        }//end while
        return;
    }//end sort
}
//Define Shapes class
abstract class Shapes{
    //define abstract methods
    public abstract double getArea();
    public abstract double getVolume();
    public abstract String getName();
}//End of Shapes

//Create Point
class Point extends Shapes implements Comparable{
    protected int x;
    protected int y;
    protected int z;
    //Point is parent of Solids. Derived classes are compared based on area. It must exist above Solids.
    //Point constructer
    public Point (int xp, int yp, int zp, PrintWriter outf){
        x=xp;
        y=yp;
        z=zp;
    }//end constructor
    //compareTo function for Point
    public int compareTo(Object o){
        if(getVolume()<((Point)o).getVolume()) return 1;
        else if(getVolume()>((Point)o).getVolume())return -1;
        else return 0;
    }//end compareTo
    public void setX(int x){return;}
    public void setY(int y){return;}
    public void setZ(int Z){return;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getZ(){return z;}
    public String getName(){return "Point";}
    public double getArea(){return 0.0;}
    public double getVolume(){return 0.0;}
    public void print(PrintWriter outf){
        outf.println("Point X: "+x+", Y: "+y+", Z: "+z);
        return;
    }
}//End of Point

class Solids extends Point implements Comparable{//Solid is the parent of all volumes. They are compared based on area and this is only done at this level
    //Solids constructor
    protected double area;
    protected double volume;
    public Solids(int x, int y, int z, PrintWriter outf){
        super(x,y,z,outf);
    }
    public double getarea(){return area;}//calls lower getarea
    public void calcarea(){return;}
    public double getvol(){return volume;}//calls lower getvol
    public void calcvol(){return;}
    //compareTo for Solids
    public int compareTo(Object o){
        if(getvol()<((Solids)o).getvol()) return 1;
        else if(getvol()>((Solids)o).getvol())return -1;
        else return 0;
    }//end compareTo
}//end Solids - //

// Begin Cubes
class Cubes extends Solids{
    protected int len;
    protected int wid;
    protected int hig;
    protected double area;
    protected double volume;
    //constructor for solid
    public Cubes(int x, int y, int z, PrintWriter outf, int len, int wid, int hig){
        super (x, y, z, outf);
        this.len=len;
        this.wid=wid;
        this.hig=hig;
    }
    //calculate Area and Volume
    public void calcvol(){volume=len*wid*hig;}
    public void calcarea(){area=6*len*wid;}
    public double getvol(){return volume;}
    public double getarea(){return area;}
    public String getName(){return "Cube";}

    //Print
    public void print (PrintWriter outf){
        outf.println("Cube with dimmensions ("+x+","+y+","+z+")");
        System.out.println("Cube with dimmensions ("+x+","+y+","+z+")");
        outf.println("Area: "+area);
        System.out.println("Area: "+area);
        outf.println("Volume: "+volume);
        System.out.println("Volume: "+volume+"\n");
    }// toString to format Array List
    @Override
    public String toString(){
        return "Cube with dimmensions ("+x+","+y+","+z+")\n Area: "+area+"\nVolume: "+volume+"\n\n";
    }
}//end of Cubes

// Begin Spheres
class Spheres extends Solids{
    protected int len;
    protected int wid;
    protected int hig;
    protected double area;
    protected double volume;
    //constructor for solid
    public Spheres(int x, int y, int z, PrintWriter outf, int len, int wid, int hig){
        super (x, y, z, outf);
        this.len=len;
        this.wid=wid;
        this.hig=hig;
    }
    //calculate Area and Volume
    public void calcvol(){volume=1.33*3.14159*len*wid*hig;}
    public void calcarea(){area=4*3.14159*len*wid;}
    public double getvol(){return volume;}
    public double getarea(){return area;}
    public String getName(){return "Sphere";}

   //Print
    public void print (PrintWriter outf){
        outf.println("Sphere with radius "+x);
        System.out.println("Sphere with radius "+x);
        outf.println("Area: "+area);
        System.out.println("Area: "+area);
        outf.println("Volume: "+volume);
        System.out.println("Volume: "+volume+"\n");
    }// toString to format Array List
    @Override
    public String toString(){
        return "Sphere with radius "+x+"\n Area: "+area+"\nVolume: "+volume+"\n\n";
    }
}//end of Spheres

// Begin Cones
class Cones extends Solids{
    protected int len;
    protected int wid;
    protected int hig;
    protected double area;
    protected double volume;
    //constructor for solid
    public Cones(int x, int y, int z, PrintWriter outf, int len, int wid, int hig){
        super (x, y, z, outf);
        this.len=len;
        this.wid=wid;
        this.hig=hig;
    }
    //calculate Area and Volume
    public void calcvol(){volume=3.14159*len*len*(hig/3);}
    public void calcarea(){area=4*3.14159*len*(len+Math.sqrt((hig*hig)+(len*len)));}
    public double getvol(){return volume;}
    public double getarea(){return area;}
    public String getName(){return "Cone";}

    //Print
    public void print (PrintWriter outf){
        outf.println("Cone with radius "+x+" and height "+y);
        System.out.println("Cone with radius "+x+" and height "+y);
        outf.println("Area: "+area);
        System.out.println("Area: "+area);
        outf.println("Volume: "+volume);
        System.out.println("Volume: "+volume+"\n");
    }// toString to format Array List
    @Override
    public String toString(){
        return "Cone with radius "+x+" and height "+y+"\n Area: "+area+"\nVolume: "+volume+"\n\n";
    }
}//end of Cones

// begin Bricks
class Bricks extends Solids{
    protected int len;
    protected int wid;
    protected int hig;
    protected double area;
    protected double volume;
    //constructor for solid
    public Bricks(int x, int y, int z, PrintWriter outf, int len, int wid, int hig){
        super (x, y, z, outf);
        this.len=len;
        this.wid=wid;
        this.hig=hig;
    }
    //calculate Area and Volume
    public void calcvol(){volume=hig*len*wid;}
    public void calcarea(){area=2*(len*wid+len*hig+wid*hig);}
    public double getvol(){return volume;}
    public double getarea(){return area;}
    public String getName(){return "Parallelepipeds";}

   //Print
    public void print (PrintWriter outf){
        outf.println("Parallelepipeds with dimmensions ("+x+","+y+","+z+")");
        System.out.println("Parallelepipeds with dimmensions ("+x+","+y+","+z+")");
        outf.println("Area: "+area);
        System.out.println("Area: "+area);
        outf.println("Volume: "+volume);
        System.out.println("Volume: "+volume+"\n");
    }// toString to format Array List
    @Override
    public String toString(){
        return "Parallelepipeds with dimmensions ("+x+","+y+","+z+")\n Area: "+area+"\nVolume: "+volume+"\n\n";
    }
}//end of Bricks

// begin TCones
class TCones extends Solids{
    protected int len;
    protected int wid;
    protected int hig;
    protected double area;
    protected double volume;
    //constructor for solid
    public TCones(int x, int y, int z, PrintWriter outf, int len, int wid, int hig){
        super (x, y, z, outf);
        this.len=len;
        this.wid=wid;
        this.hig=hig;
    }
    //calculate Area and Volume
    public void calcvol(){volume=wid*3.14159/3*(len*len+len*hig+hig*hig);}
    public void calcarea(){area=(((len+hig)*3.14159*Math.sqrt((len-hig)*(len*hig)+wid*wid))+3.14159*hig*hig+3.14159*len*len);}
    public double getvol(){return volume;}
    public double getarea(){return area;}
    public String getName(){return "Truncated Cone";}

    //Print
    public void print (PrintWriter outf){
        outf.println("Truncated Cone with bottom radius, "+x+",top radius "+z+", and height of"+y+")");
        System.out.println("Truncated Cone with bottom radius, "+x+",top radius "+z+", and height of"+y+")");
        outf.println("Area: "+area);
        System.out.println("Area: "+area);
        outf.println("Volume: "+volume);
        System.out.println("Volume: "+volume+"\n");
    }// toString to format Array List
    @Override
    public String toString(){
        return "Truncated Cone with bottom radius, "+x+",top radius "+z+", and height of"+y+")"+"\n Area: "+area+"\nVolume: "+volume+"\n\n";
    }
}//end of Code