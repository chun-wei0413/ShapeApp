package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.Math;

public class ShapeTest {
    // e.g. Shape circle = new Circle(...)

    @Test
    public void test_shape_area() {
        Shape circle = new Circle(10);
        assertEquals(10*10*Math.PI, circle.area(), 0.001);
    }

    @Test
    public void test_shape_perimeter() {
        Shape rectangle = new Rectangle(12, 10);
        assertEquals((10+12)*2, rectangle.perimeter(), 0.001); 
    }

    @Test
    public void test_shape_add() {
        Shape circle = new Circle(10);
        Shape rectangle = new Rectangle(4, 5);
        assertThrows(ShapeException.class, () -> circle.add(rectangle));
    }

    @Test
    public void test_shape_iterator() {
        Shape circle = new Circle(7);
        Iterator<Shape> circleIterator = circle.iterator();
        
        assertFalse(circleIterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> circleIterator.next());
    }
}