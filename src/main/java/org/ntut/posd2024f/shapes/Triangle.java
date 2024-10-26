package org.ntut.posd2024f.shapes;

public class Triangle implements Shape{
    private double a,b,c;   

    public Triangle(double a, double b, double c) throws Exception {
        if(!isValid(a,b,c)){
            throw new Exception("It's not a triangle!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isValid(double a, double b, double c) {
        if(a + b > c && a + c > b && b + c > a){
            return true;
        }
        return false;
    }

    public double area() {
        double s = perimeter()/2;
        return Math.sqrt( s * (s - a) * (s - b) * (s - c));
    }

    public double perimeter() {
        return a+b+c;
    }

    @Override
    public String toString() {
        return "Triangle "+ this.a + ", " + this.b + ", " + this.c;
    }
}
