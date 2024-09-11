package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TriangleTest {
    // Triangle t = new Triangle(...)

    @Test
    public void test_Valid_Triangle() throws Exception {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals(6, triangle.area(), 0);
        assertEquals(12, triangle.perimeter(), 0);
    }

    @Test(expected = Exception.class)
    public void test_Invalid_Triangle() throws Exception {
        new Triangle(3, 5, 9);
    }

    @Test
    public void test_Triangle_Area() throws Exception {
        Triangle triangle = new Triangle(7, 8, 9);
        assertEquals(26.832, triangle.area(), 0.001);
    }


    @Test
    public void test_Triangle_Perimeter() throws Exception {
        Triangle triangle = new Triangle(7, 8, 9);
        assertEquals(24, triangle.perimeter(), 0);
    }


    @Test
    public void test_ToString() throws Exception {
        Triangle triangle = new Triangle(7, 8, 9);
        assertEquals("Triangle 7.0 8.0 9.0", triangle.toString());
    }

    @Test
    public void test_equals_same_class_with_same_values() throws Exception{
        Triangle triangle1 = new Triangle(3,4,5);
        Triangle triangle2 = new Triangle(3,4,5);
        assertTrue(triangle1.equals(triangle2));
    }

    @Test
    public void test_equals_different_class_comparison() throws Exception{
        Triangle triangle = new Triangle(3, 4, 5);
        Circle circle = new Circle(5);
        assertFalse(triangle.equals(circle));
    }
}