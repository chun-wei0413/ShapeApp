package org.ntut.posd2024f.shapes;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class Triangle implements Shape
{
    private double a,b,c;
    
    Triangle(double a, double b, double c) throws Exception {
        if(a + b <= c || a + c <= b || b + c <= a){
            throw new Exception("It's not a triangle!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public double area() {
        double s = perimeter()/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    @Override
    public String toString() {
        return "Triangle "+ a + " " + b + " " +c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.a, a) == 0 &&
            Double.compare(triangle.b, b) == 0 &&
            Double.compare(triangle.c, c) == 0;
    }
}
