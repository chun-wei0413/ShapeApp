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

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitRectangle(this);
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Rectangle rec = (Rectangle) o;
        return Double.compare(this.length, rec.length) == 0 && Double.compare(this.width, rec.width) == 0;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}