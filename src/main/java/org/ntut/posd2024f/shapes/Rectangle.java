package org.ntut.posd2024f.shapes;

public class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) throws ShapeException {
        if(length <= 0 || width <=0){
            throw new ShapeException("It's not a rectangle!");
        }
        this.length = length;
        this.width = width;    
    }

    @Override
	public double area() {
        return length * width;
    }
    
    @Override
    public double perimeter() {
        return (length + width) * 2;
    }
}