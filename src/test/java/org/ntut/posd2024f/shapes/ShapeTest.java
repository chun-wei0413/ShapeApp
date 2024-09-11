package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

    @Test
    public void test_shape_toString() throws Exception{
        Shape triangle = new Triangle(5, 12, 13);
        assertEquals("Triangle 5.0 12.0 13.0", triangle.toString());
    }

    @Test
    public void test_shape_equals() throws Exception{
        Shape circle = new Circle(7);
        Shape triangle = new Triangle(5, 12, 13);
        assertFalse(circle.equals(triangle));
    }
}