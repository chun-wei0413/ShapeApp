package org.ntut.posd2024f.shapes;

public class Circle implements Shape{
    private double radius;

    public Circle(double radius) throws Exception {
        if(!isValid(radius)){
            throw new Exception("It's not a circle!");
        }
        this.radius = radius;
    }
    
    public boolean isValid(double radius) {
        if(radius <= 0) {
            return false;
        }
        return true;
    }

    public double area() {
        return this.radius * this.radius * Math.PI;
    }

    public double perimeter() {
        return this.radius * 2 * Math.PI;
    }

    @Override
    public String toString(){
        return "Circle " + this.radius;
    }
}
