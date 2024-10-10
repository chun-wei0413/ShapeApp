package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundShape implements Shape {
    private List<Shape> shapes;

    public CompoundShape() {
        this.shapes = new ArrayList<>();
    }
    
    @Override
    public double area() {
        double area = 0;

        for(Shape shape : shapes){
            area += shape.area();
        }

        return area;
    }
    
    @Override
    public double perimeter() {
        double perimeter = 0;

        for(Shape shape : shapes){
            perimeter += shape.perimeter();
        }

        return perimeter;
    }

    @Override
    public void add(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitCompoundShape(this);
    }

    public int size(){
        return shapes.size();
    }
}
