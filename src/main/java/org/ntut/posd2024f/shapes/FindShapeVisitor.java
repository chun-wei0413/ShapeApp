package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindShapeVisitor implements Visitor<List<Shape>> {
    private final Predicate<Shape> condition;
    private final List<Shape> result = new ArrayList<>();

    public FindShapeVisitor(Predicate<Shape> condition) {   
        this.condition = condition;
    }

    @Override
    public void visitCircle(Circle circle) {
        if(condition.test(circle)) {
            result.add(circle);
        }
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        if(condition.test(rectangle)){
            result.add(rectangle);
        }
    }

    @Override
    public void visitTriangle(Triangle triangle) {
        if(condition.test(triangle)){
            result.add(triangle);
        }
    }

    @Override
    public void visitConvexPolygon(ConvexPolygon convexPolygon) {
        if(condition.test(convexPolygon)){
            result.add(convexPolygon);
        }
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        if(condition.test(compoundShape)){
            result.add(compoundShape);
        }
    }

    @Override
    public void visitTextedShape(TextedShape textedShape) {
        if(condition.test(textedShape)){
            result.add(textedShape);
        }
    }

    @Override
    public void visitColoredShape(ColoredShape coloredShape) {
        if(condition.test(coloredShape)){
            result.add(coloredShape);
        }
    }

    public List<Shape> getResult() {
        return result;
    }
}
