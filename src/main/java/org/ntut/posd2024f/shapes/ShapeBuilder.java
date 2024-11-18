package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShapeBuilder {

    private List<Shape> shapes;
    private Stack<Shape> stack;

    public ShapeBuilder() {
        this.shapes = new ArrayList<>();
        this.stack = new Stack<>();
    }

    public void buildCircle(double radius, String color, String text) {
        Shape circle = new Circle(radius);
        circle = decorateShape(circle, color, text);
        addShapeToCurrentContext(circle);
    }

    public void buildRectangle(double length, double width, String color, String text) {
        Shape rectangle = new Rectangle(length, width);
        rectangle = decorateShape(rectangle, color, text);
        addShapeToCurrentContext(rectangle);
    }

    public void buildTriangle(List<TwoDimensionalVector> vectors, String color, String text) {
        Shape triangle = new Triangle(vectors);
        triangle = decorateShape(triangle, color, text);
        addShapeToCurrentContext(triangle);
    }

    public void buildConvexPolygon(List<TwoDimensionalVector> vectors, String color, String text) {
        Shape polygon = new ConvexPolygon(vectors);
        polygon = decorateShape(polygon, color, text);
        addShapeToCurrentContext(polygon);
    }

    public void beginBuildCompoundShape(String color, String text) {
        CompoundShape compoundShape = new CompoundShape(); 
        Shape decoratedCompoundShape = decorateShape(compoundShape, color, text);
        stack.push(decoratedCompoundShape);
    }

    public void endBuildCompoundShape() {
        Shape finishedCompoundShape = stack.pop();
        if (stack.isEmpty()) {
            shapes.add(finishedCompoundShape);
        } else {
            stack.peek().add(finishedCompoundShape);
        }
    }

    public List<Shape> getResult() {
        return shapes;
    }

    private Shape decorateShape(Shape shape, String color, String text) {
        if (color != null) {
            shape = new ColoredShape(shape, color);
        }
        if (text != null) {
            shape = new TextedShape(shape, text);
        }
        return shape;
    }

    private void addShapeToCurrentContext(Shape shape) {
        if (stack.isEmpty()) {
            shapes.add(shape);
        } else {
            stack.peek().add(shape);
        }
    }
}
