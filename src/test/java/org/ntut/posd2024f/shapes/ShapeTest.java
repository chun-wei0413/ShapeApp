package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class ShapeTest {
    
    @Test
    public void test_shape_area() {
        Shape circle = new Circle(5);
        assertEquals(5*5*Math.PI, circle.area(), 0.001);
    }

    @Test
    public void test_shape_perimeter() {
        Shape rectangle = new Rectangle(12, 10);
        assertEquals((10+12)*2, rectangle.perimeter(), 0.001); 
    }

    @Test
    public void test_shape_invalid_add() {
        Shape rectangle = new Rectangle(12, 10);
        Shape circle = new Circle(5);
        assertThrows(ShapeException.class, () -> { 
            rectangle.add(circle);
        });
    }

    @Test
    public void test_shape_iterator() {
        Shape circle = new Circle(7);
        Iterator<Shape> circleIterator = circle.iterator();
        
        assertFalse(circleIterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> circleIterator.next());
    }

}
