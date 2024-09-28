package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RectangleTest {
    // e.g. Rectangle r = new rectangle(...)
    @Test
    public void test_valid_rectangle() throws Exception {
        new Rectangle(5, 6);
    }

    @Test(expected = ShapeException.class)
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
}