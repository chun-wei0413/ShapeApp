package org.ntut.posd2024f.shapes;

import java.util.List;

public class Triangle implements Shape{
    private List<TwoDimensionalVector> vectors;
    
    public Triangle(List<TwoDimensionalVector> vectors) {
        if(!isTriangleValid(vectors)){
            throw new ShapeException("It's not a triangle!");
        }
        this.vectors = vectors;
    }

    public boolean isTriangleValid(List<TwoDimensionalVector> vectors){
        TwoDimensionalVector a,b,c;
        a = vectors.get(0);
        b = vectors.get(1);
        c = vectors.get(2);
        
        TwoDimensionalVector ab = b.subtract(a);
        TwoDimensionalVector ac = c.subtract(a);
        
        int crossProduct = ab.cross(ac);
        
        return crossProduct != 0;
    }

    @Override
    public double perimeter() {
        double perimeter = 0;

        perimeter += vectors.get(0).subtract(vectors.get(1)).length();
        perimeter += vectors.get(1).subtract(vectors.get(2)).length();
        perimeter += vectors.get(0).subtract(vectors.get(2)).length();

        return perimeter;
    }

    @Override
    public double area() {
        double s = perimeter()/2;
        double a = vectors.get(0).subtract(vectors.get(1)).length();
        double b = vectors.get(1).subtract(vectors.get(2)).length();
        double c = vectors.get(0).subtract(vectors.get(2)).length();
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

}
