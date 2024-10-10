package org.ntut.posd2024f.shapes;

import java.util.Iterator;

public class ColoredShape implements Shape {
    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public double area() {
        return shape.area();
    }

    @Override
    public double perimeter() {
        return shape.perimeter();
    }

    @Override
    public void add(Shape shape) {
        this.shape.add(shape);
    }

    @Override
    public Iterator<Shape> iterator() {
        return shape.iterator();
    }

    public Shape getShape() {
        return shape;
    }

    public String getColor() {
        return color;
    }

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitColoredShape(this);
    }
}
