package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class FindShapeVisitorTest {
    
    @Test
    public void test_Find_Circle_With_Radius_equals_to_3() {
        Circle circle1 = new Circle(3.0);
        Circle circle2 = new Circle(5.0);
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(circle1);
        compoundShape.add(circle2);

        Predicate<Shape> condition = shape -> (shape instanceof Circle) && ((Circle) shape).getRadius() == 3.0;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        
        compoundShape.accept(visitor);
        
        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());
        assertTrue(foundShapes.contains(circle1));
    }

    @Test
    public void test_Find_Rectangle_With_Specific_Length_and_width() {
        Rectangle rectangle1 = new Rectangle(3.0, 4.0);
        Rectangle rectangle2 = new Rectangle(5.0, 6.0);
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(rectangle1);
        compoundShape.add(rectangle2);

        Predicate<Shape> condition = shape -> (shape instanceof Rectangle) && ((Rectangle) shape).getLength() == 3.0 && ((Rectangle) shape).getWidth() == 4.0;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        
        compoundShape.accept(visitor);
        
        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());
        assertTrue(foundShapes.contains(rectangle1));
        assertFalse(foundShapes.contains(rectangle2));
    }

    @Test
    public void test_Find_Shape_In_Complex_CompoundShape() {
        Circle circle = new Circle(3.0);
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        List<TwoDimensionalVector> vectors1 = new ArrayList<>();
        vectors1.add(new TwoDimensionalVector(4, 0));
        vectors1.add(new TwoDimensionalVector(4, 3));
        vectors1.add(new TwoDimensionalVector(0, 3));
        Triangle triangle = new Triangle(vectors1);
        List<TwoDimensionalVector> vectors2 = new ArrayList<>();
        vectors2.add(new TwoDimensionalVector(0, 0));   // A
        vectors2.add(new TwoDimensionalVector(4, 0));   // B
        vectors2.add(new TwoDimensionalVector(5, 3));   // C
        vectors2.add(new TwoDimensionalVector(2, 5));   // D
        vectors2.add(new TwoDimensionalVector(-1, 3));  // E
        ConvexPolygon convexPolygon = new ConvexPolygon(vectors2);
        CompoundShape innerCompound = new CompoundShape();
        innerCompound.add(triangle);
        innerCompound.add(convexPolygon);

        CompoundShape outerCompound = new CompoundShape();
        outerCompound.add(circle);
        outerCompound.add(innerCompound);  
        outerCompound.add(rectangle);  

        Predicate<Shape> condition = shape -> shape.area() > 10.0;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        
        outerCompound.accept(visitor);
        
        List<Shape> foundShapes = visitor.getResult();
        //這3個包含outerCompound、innerCompound、triangle、circle
        assertEquals(5, foundShapes.size());
        assertFalse(foundShapes.contains(triangle));
        assertTrue(foundShapes.contains(convexPolygon));
        assertTrue(foundShapes.contains(outerCompound));
        assertTrue(foundShapes.contains(innerCompound));
        assertTrue(foundShapes.contains(circle));
        assertTrue(foundShapes.contains(rectangle));
    }

    @Test
    public void test_Find_ColoredShape() {
        Circle circle = new Circle(3.0);
        ColoredShape coloredCircle = new ColoredShape(circle, "RED");

        Predicate<Shape> condition = shape -> shape instanceof ColoredShape;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);

        coloredCircle.accept(visitor);

        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());
        assertTrue(foundShapes.contains(coloredCircle));
    }

    @Test
    public void test_Find_TextedShape() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        TextedShape textedRectangle = new TextedShape(rectangle, "This is a rectangle");

        Predicate<Shape> condition = shape -> shape instanceof TextedShape;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);

        textedRectangle.accept(visitor);

        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size()); 
        assertTrue(foundShapes.contains(textedRectangle)); 
    }

    @Test
    public void test_Find_Shape_With_Specific_Perimeter() {
        Circle circle = new Circle(10);
        Rectangle rectangle = new Rectangle(5, 5);
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        Predicate<Shape> condition = shape -> shape.perimeter() == 20;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        
        compoundShape.accept(visitor);
        
        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());
        assertFalse(foundShapes.contains(circle));
        assertTrue(foundShapes.contains(rectangle));
    }
}
