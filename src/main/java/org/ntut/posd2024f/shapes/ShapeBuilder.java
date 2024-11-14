package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShapeBuilder {

    private List<Shape> shapes;
    private Stack<CompoundShape> compoundShapeStack;

    public ShapeBuilder() {
        this.shapes = new ArrayList<>();
        this.compoundShapeStack = new Stack<>();
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
        if (!compoundShapeStack.isEmpty()) {
            compoundShapeStack.peek().add(decoratedCompoundShape);
        }
        compoundShapeStack.push((CompoundShape) decoratedCompoundShape);
    }

    public void endBuildCompoundShape() {
        if (!compoundShapeStack.isEmpty()) {
            CompoundShape finishedCompoundShape = compoundShapeStack.pop();
            if (compoundShapeStack.isEmpty()) {
                shapes.add(finishedCompoundShape);
            }
        } else {
            throw new IllegalStateException("No compound shape to end.");
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

    // 根據當前的構建上下文添加形狀
    private void addShapeToCurrentContext(Shape shape) {
        if (compoundShapeStack.isEmpty()) {
            shapes.add(shape);
        } else {
            compoundShapeStack.peek().add(shape);
        }
    }
}
