package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RectangleTest {
    // e.g. Rectangle r = new rectangle(...)
    @Test
    public void test_valid_rectangle() throws Exception {
        Rectangle rectangle = new Rectangle(5, 6);
        assertEquals(30, rectangle.area(), 0);
        assertEquals(22, rectangle.perimeter(), 0);
    }

    @Test(expected = Exception.class)
    public void test_invalid_rectangle_when_length_zero() throws Exception {
        new Rectangle(0, 5);
    }

    @Test(expected = Exception.class)
    public void test_invalid_rectangle_when_width_negative() throws Exception {
        new Rectangle(6, -5);
    }

    @Test
    public void test_rectangle_area() throws Exception {
        Rectangle rectangle = new Rectangle(3, 4);
        assertEquals(12, rectangle.area(), 0);
    }


    @Test
    public void test_rectangle_perimeter() throws Exception {
        Rectangle rectangle = new Rectangle(7, 8);
        assertEquals(30, rectangle.perimeter(), 0);
    }


    @Test
    public void test_toString() throws Exception {
        Rectangle rectangle = new Rectangle(8, 9);
        assertEquals("Rectangle 8.0 9.0", rectangle.toString());
    }

    @Test
    public void test_equals_same_class_with_same_values() throws Exception{
        Rectangle rectangle1 = new Rectangle(4, 5);
        Rectangle rectangle2 = new Rectangle(4, 5);
        assertTrue(rectangle1.equals(rectangle2));
    }

    @Test
    public void test_equals_different_class_comparison() throws Exception{
        Rectangle rectangle = new Rectangle(4, 5);
        Triangle triangle = new Triangle(3, 4, 5);
        assertFalse(rectangle.equals(triangle));
    }
}