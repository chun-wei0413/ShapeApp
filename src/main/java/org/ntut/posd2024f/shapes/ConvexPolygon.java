package org.ntut.posd2024f.shapes;

import java.util.List;

public class ConvexPolygon implements Shape{
    private List<TwoDimensionalVector> vectors;

    public ConvexPolygon(List<TwoDimensionalVector> vectors) {
        if(!isConvexPolygonValid(vectors)){
            throw new ShapeException("It's not a convex polygon!");
        }
            this.vectors = vectors;
    }

    /*The vectors which used to create the ConvexPolygon need to be sorted in the 
    clockwise direction or counterclockwise direction, the unsorted vectors are 
    unavailable and needs to throw the ShapeException.*/
    public boolean isConvexPolygonValid(List<TwoDimensionalVector> vectors) {
        int n = vectors.size();
        if (n < 3) return false;
    
        boolean isConvex = true;
        
        for (int i = 0; i < n; i++) {
            TwoDimensionalVector current = vectors.get(i);
            TwoDimensionalVector next = vectors.get((i + 1) % n);
            TwoDimensionalVector nextNext = vectors.get((i + 2) % n);
            
            // 向量相減得到邊
            TwoDimensionalVector vector1 = next.subtract(current);
            TwoDimensionalVector vector2 = nextNext.subtract(next);
            
            // 計算兩個向量的夾角餘弦值
            double dotProduct = vector1.dot(vector2);
            double magnitudes = vector1.length() * vector2.length();
            double cosTheta = dotProduct / magnitudes;

            // 如果夾角大於 180 度，cosTheta 會是負數
            if (cosTheta < 0) {
                isConvex = false;
                break;
            }
        }
    
        return isConvex;
    }
    

    @Override
    public double area() {
        double area = 0;
        for(int i=0; i < vectors.size() ; i++) {
            TwoDimensionalVector vector = vectors.get(i);
            TwoDimensionalVector nextVector = vectors.get((i+1) % vectors.size());
            area += vector.cross(nextVector);
        }

        return Math.abs(area)/2;
    }

    @Override
    public double perimeter() {
        double perimeter = 0;
        for(int i =0 ; i < vectors.size(); i++) {
            TwoDimensionalVector vector = vectors.get(i);
            TwoDimensionalVector nextVector = vectors.get((i+1) % vectors.size());
            
            double length = vector.subtract(nextVector).length();
            perimeter += length;
        }

        return perimeter;
    }
}
