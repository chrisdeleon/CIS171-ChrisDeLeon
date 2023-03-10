/*  
*    Chris de Leon
*    1/31/2023
*    Geometric Objects Comparable 
*/

package com.mycompany.exercise13_5;

public abstract class GeometricObject implements Comparable<GeometricObject> {
    
   
    private String color="white";
    private boolean filled;
    private java.util.Date dateCreated;
    
    protected GeometricObject(){
        dateCreated = new java.util.Date();
    }
    
    protected GeometricObject(String color, boolean filled){
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public boolean isFilled(){
        return filled;
    }
    
    public void setFilled(boolean filled){
        this.filled = filled;
    }
    
    public java.util.Date getDateCreated(){
        return dateCreated;
    }
    
    public String toString(){
         return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;
    }
   
    
    // the two methods below force subclasses to define
    public abstract double getArea();
    
    public abstract double getPerimeter();
    
    // implements the comparable interface
    
    @Override public int compareTo(GeometricObject o){
        return Double.compare(this.getArea(), o.getArea());
    }
    
    // static max method that returns the larger of two GeometricObject objects
    
    public static double max(GeometricObject o1, GeometricObject o2){
        return Math.max(o1.getArea(), o2.getArea());
    }
}
