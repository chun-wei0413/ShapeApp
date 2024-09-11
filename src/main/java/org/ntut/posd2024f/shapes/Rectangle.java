package org.ntut.posd2024f.shapes;

public class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) throws Exception {
        if(length <= 0 || width <=0){
            throw new Exception("It's not a rectangle!");
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

    @Override
    public String toString() {
        return "Rectangle " + length + " " + width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.length, length) == 0 &&
            Double.compare(rectangle.width, width) == 0;
    }
}