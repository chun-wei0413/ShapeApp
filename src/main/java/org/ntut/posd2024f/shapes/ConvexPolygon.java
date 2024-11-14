package org.ntut.posd2024f.shapes;

import java.util.List;

public class ConvexPolygon implements Shape {
    private List<TwoDimensionalVector> vectors;

    public ConvexPolygon(List<TwoDimensionalVector> vectors) {
        if(!isConvexPolygonValid(vectors)){
            throw new ShapeException("It's not a convex polygon!");
        }
            this.vectors = vectors;
    }

    public boolean isConvexPolygonValid(List<TwoDimensionalVector> vectors) {
        int n = vectors.size();
        if (n < 3) return false;

        boolean isPositive = false;
        boolean isNegative = false;

        for (int i = 0; i < n; i++) {
            TwoDimensionalVector current = vectors.get(i);
            TwoDimensionalVector next = vectors.get((i + 1) % n);
            TwoDimensionalVector nextNext = vectors.get((i + 2) % n);

            TwoDimensionalVector edge1 = next.subtract(current);
            TwoDimensionalVector edge2 = nextNext.subtract(next);

            double crossProduct = edge1.cross(edge2);

            if (crossProduct > 0) {
                isPositive = true;
            } else if (crossProduct < 0) {
                isNegative = true;
            }

            // 如果同時出現了正負叉積，則不是凸多邊形
            if (isPositive && isNegative) {
                return false;
            }
        }

        return true;
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

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitConvexPolygon(this);
    }

    public List<TwoDimensionalVector> getVectors() {
        return vectors;
    }
}
