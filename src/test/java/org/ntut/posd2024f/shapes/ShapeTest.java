package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Math;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ShapeTest {
    // e.g. Shape circle = new Circle(...)

    @Test
    public void test_shape_area() throws Exception{
        Shape circle = new Circle(10);
        assertEquals(10*10*Math.PI, circle.area(), 0.001);
    }

    @Test
    public void test_shape_perimeter() throws Exception{
        Shape rectangle = new Rectangle(12, 10);
        assertEquals((10+12)*2, rectangle.perimeter(), 0.001); 
    }

    @Test(expected = ShapeException.class)
    public void test_shape_add() throws Exception{
        Shape circle = new Circle(10);
        Shape rectangle = new Rectangle(4, 5);
        circle.add(rectangle);
    }

    @Test(expected = NoSuchElementException.class)
    public void test_shape_iterator() throws Exception{
        Shape circle = new Circle(7);
        Iterator<Shape> circleIterator = circle.iterator();
        
        assertFalse(circleIterator.hasNext());
        circleIterator.next();
    }
}