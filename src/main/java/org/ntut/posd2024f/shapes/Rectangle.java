package org.ntut.posd2024f.shapes;

public class Rectangle implements Shape{
    private double length, width;

    public Rectangle(double length, double width) {
        if(!isValid(length, width)){
            throw new ShapeException("It's not a rectangle!");
        }
        this.length = length;
        this.width = width;
    }

    public boolean isValid(double length, double width) {
        if(length <= 0 || width <= 0){
            return false;
        }
        return true;
    }

    public double area() {
        return this.length * this.width;
    }

    public double perimeter() {
        return (this.length + this.width) * 2;
    }

}
