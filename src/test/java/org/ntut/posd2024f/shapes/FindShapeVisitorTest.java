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
    public void test_Find_Circle_With_Radius3() {
        Circle circle1 = new Circle(3.0);
        Circle circle2 = new Circle(5.0);
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(circle1);
        compoundShape.add(circle2);

        // Predicate to find Circle with radius 3.0
        Predicate<Shape> condition = shape -> (shape instanceof Circle) && ((Circle) shape).getRadius() == 3.0;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        
        compoundShape.accept(visitor);
        
        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());  // Should find only one circle with radius 3
        assertTrue(foundShapes.contains(circle1));  // Make sure the found shape is circle1
    }

    @Test
    public void test_Find_Rectangle_With_Specific_Length_and_width() {
        Rectangle rectangle1 = new Rectangle(3.0, 4.0);
        Rectangle rectangle2 = new Rectangle(5.0, 6.0);
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(rectangle1);
        compoundShape.add(rectangle2);

        // Predicate to find Rectangle with length 3.0 and width 4.0
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
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(4, 0));
        vectors.add(new TwoDimensionalVector(4, 3));
        vectors.add(new TwoDimensionalVector(0, 3));

        Triangle triangle = new Triangle(vectors);
        
        CompoundShape innerCompound = new CompoundShape();
        innerCompound.add(triangle);
        
        CompoundShape outerCompound = new CompoundShape();
        outerCompound.add(circle);  
        outerCompound.add(innerCompound);  
        outerCompound.add(rectangle);  

        Predicate<Shape> condition = shape -> shape.area() > 10.0;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        
        outerCompound.accept(visitor);
        
        List<Shape> foundShapes = visitor.getResult();
        assertEquals(3, foundShapes.size());
        assertFalse(foundShapes.contains(triangle));
        assertTrue(foundShapes.contains(circle));
        assertTrue(foundShapes.contains(rectangle));
    }

    @Test
    public void test_Find_ColoredShape() {
        Circle circle = new Circle(3.0);
        ColoredShape coloredCircle = new ColoredShape(circle, "Red");

        // Predicate to find colored shapes
        Predicate<Shape> condition = shape -> shape instanceof ColoredShape;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);

        coloredCircle.accept(visitor);

        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());  // Should find one colored shape
        assertTrue(foundShapes.contains(coloredCircle));  // Make sure it found the colored circle
    }

    @Test
    public void test_Find_TextedShape() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        TextedShape textedRectangle = new TextedShape(rectangle, "This is a rectangle");

        // Predicate to find texted shapes
        Predicate<Shape> condition = shape -> shape instanceof TextedShape;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);

        textedRectangle.accept(visitor);

        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());  // Should find one texted shape
        assertTrue(foundShapes.contains(textedRectangle));  // Make sure it found the texted rectangle
    }

    @Test
    public void test_Find_Shape_With_Specific_Perimeter() {
        Circle circle = new Circle(10);  // Circle with radius 0 has perimeter 0
        Rectangle rectangle = new Rectangle(5, 5);  // Rectangle with 0 dimensions has perimeter 0
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        // Predicate to find shapes with perimeter equal to 0
        Predicate<Shape> condition = shape -> shape.perimeter() == 20;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        
        compoundShape.accept(visitor);
        
        List<Shape> foundShapes = visitor.getResult();
        assertEquals(1, foundShapes.size());  // Should find both circle and rectangle
        assertFalse(foundShapes.contains(circle));
        assertTrue(foundShapes.contains(rectangle));
    }
}
