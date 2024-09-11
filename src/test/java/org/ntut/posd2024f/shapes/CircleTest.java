package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CircleTest {
    // e.g. Circle c = new Circle(...)

    @Test
    public void test_Valid_Circle() throws Exception {
        Circle circle = new Circle(5.0);
        assertEquals(5*5*Math.PI , circle.area(), 0.001);
        assertEquals(5*2*Math.PI, circle.perimeter(), 0.001);
    }

    @Test(expected = Exception.class)
    public void test_invalid_circle_when_radius_zero() throws Exception {
        new Circle(0);
    }

    @Test(expected = Exception.class)
    public void test_invalid_circle_when_radius_negative() throws Exception {
        new Circle(-1);
    }

    @Test
    public void test_circle_area() throws Exception {
        Circle circle = new Circle(2.0);
        assertEquals(2*2*Math.PI, circle.area(), 0.001);
    }


    @Test
    public void test_circle_perimeter() throws Exception {
        Circle circle = new Circle(3.0);
        assertEquals(3*2*Math.PI, circle.perimeter(), 0.001);
    }


    @Test
    public void test_toString() throws Exception {
        Circle circle = new Circle(4.0);
        assertEquals("Circle 4.0", circle.toString());
    }

    @Test
    public void test_equals_same_class_with_same_values() throws Exception{
        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(5);
        assertTrue(circle1.equals(circle2));
    }

    @Test
    public void test_equals_different_class_comparison() throws Exception{
        Circle circle = new Circle(5);
        Triangle triangle = new Triangle(3, 4, 5);
        assertFalse(circle.equals(triangle));
    }
}