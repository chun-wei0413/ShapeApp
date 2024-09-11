package org.ntut.posd2024f.shapes;

public class Circle implements Shape {
    
    private double radius;

    public Circle(double radius) throws Exception {
        if(radius<=0){
            throw new Exception("It's not a circle!");
        }
        this.radius = radius;
    }

    @Override
    public double area() {
        return radius * radius * Math.PI;
    } 

    @Override
    public double perimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "Circle " + radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }
}
