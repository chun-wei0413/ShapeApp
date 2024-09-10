package org.ntut.posd2024f.shapes;

public class Circle implements Shape {
    
    private double radius;

    public Circle(double radius) throws Exception {
        if(radius<=0){
            throw new Exception("It's not a circle!");
        }
        this.radius = radius;
    }

    public double area() {
        return radius * radius * Math.PI;
    } 

    public double perimeter() {
        return 2 * radius * Math.PI;
    }

    public String toString() {
        return "Circle " + radius;
    }
}
