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
        assertEquals(78.539, circle.area(), 0.001);
        assertEquals(31.4159, circle.perimeter(), 0.001);
    }

    @Test(expected = Exception.class)
    public void test_Invalid_Circle_When_Radius_Zero() throws Exception {
        new Circle(0);
    }

    @Test(expected = Exception.class)
    public void test_Invalid_Circle_When_Radius_Negative() throws Exception {
        new Circle(-1);
    }

    @Test
    public void test_Circle_Area() throws Exception {
        Circle circle = new Circle(2.0);
        assertEquals(12.5664, circle.area(), 0.001);
    }


    @Test
    public void test_Circle_Perimeter() throws Exception {
        Circle circle = new Circle(3.0);
        assertEquals(18.8495, circle.perimeter(), 0.001);
    }


    @Test
    public void test_ToString() throws Exception {
        Circle circle = new Circle(4.0);
        assertEquals("Circle 4.0", circle.toString());
    }
}