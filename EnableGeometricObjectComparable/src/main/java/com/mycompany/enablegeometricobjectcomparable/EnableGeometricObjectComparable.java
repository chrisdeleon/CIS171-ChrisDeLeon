
package com.mycompany.enablegeometricobjectcomparable;

/**
 *
 * @author deleo
 */
public class EnableGeometricObjectComparable {

    public static void main(String[] args) {
        // declare and initialize two geometric objects
        
        GeometricObject geoObject1 = new Circle(5);
        GeometricObject geoObject2 = new Rectangle(5,3);
        
        System.out.println("The two object have the same area?");
        
        // display circle
        displayGeometricObject(geoObject1);
        
        // display rectangle
        displayGeometricObject(geoObject2);
    }
    
    // this method compares the area of two geometric objects
    public static boolean equalArea(GeometricObject object1, GeometricObject object2){
        return object1.getArea() == object2.getArea();
    }
    
    
    // this method displays the geometric object
    public static void displayGeometricObject(GeometricObject object){
        System.out.println();
        System.out.println("The area is " + object.getArea());
        System.out.println("The perimeter is " + object.getPerimeter());
    }
}