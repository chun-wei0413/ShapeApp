package org.ntut.posd2024f.shapes;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle implements Shape
{
    private List<TwoDimensionalVector> vectors;
    
    public Triangle(List<TwoDimensionalVector> vectors) throws ShapeException {
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
        // 計算向量 AB 和 AC
        TwoDimensionalVector ab = b.subtract(a);
        TwoDimensionalVector ac = c.subtract(a);
        
        // 使用 cross 方法計算 AB 和 AC 的cross product
        int crossProduct = ab.cross(ac);
        
        // 如果cross product為0，表示三點共線，無法形成三角形
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

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitTriangle(this);
    }

    //ex. Triangle [0,0] [1,0] [0,1]
    public List<TwoDimensionalVector> getVectors() {
        return vectors;
    }
}
