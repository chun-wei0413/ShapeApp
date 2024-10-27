package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

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

    @Test(expected = ShapeException.class)
    public void test_shape_invalid_add() {
        Shape rectangle = new Rectangle(12, 10);
        Shape circle = new Circle(5);
        rectangle.add(circle);
    }

    @Test(expected = NoSuchElementException.class)
    public void test_shape_iterator() {
        Shape circle = new Circle(7);
        Iterator<Shape> circleIterator = circle.iterator();
        
        assertFalse(circleIterator.hasNext());
        circleIterator.next();
    }

}
