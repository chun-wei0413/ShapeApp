package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RectangleTest {
    // e.g. Rectangle r = new rectangle(...)
    @Test
    public void test_valid_rectangle() throws Exception {
        new Rectangle(5, 6);
    }

    @Test
    public void test_invalid_rectangle_when_length_zero() throws Exception {
        assertThrows(ShapeException.class, () -> new Rectangle(0, 5));
    }

    @Test
    public void test_invalid_rectangle_when_width_negative() throws Exception {
        assertThrows(Exception.class, () -> new Rectangle(6, -5));
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
    public void test_rectangle_getLength_and_getWidth(){
        Rectangle rectangle = new Rectangle(9, 10);
        assertEquals(9, rectangle.getLength());
        assertEquals(10, rectangle.getWidth());
    }
}