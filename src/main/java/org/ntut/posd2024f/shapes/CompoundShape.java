package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundShape implements Shape{
    private List<Shape> shapes;    

    public CompoundShape() {
        this.shapes = new ArrayList<>();
    }
    
    public double area() {
        double area = 0;

        for(Shape shape: shapes) {
            area += shape.area();
        }

        return area;
    }

    public double perimeter() {
        double perimeter = 0;

        for(Shape shape: shapes) {
            perimeter += shape.perimeter();
        }

        return perimeter;
    }

    public void add(Shape shape) {
        this.shapes.add(shape);
    }

    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public int size() {
        return shapes.size();
    }
}
