package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RectangleTest {
    // e.g. Rectangle r = new rectangle(...)
    @Test
    public void test_Valid_Rectangle() throws Exception {
        Rectangle rectangle = new Rectangle(5, 6);
        assertEquals(30, rectangle.area(), 0);
        assertEquals(22, rectangle.perimeter(), 0);
    }

    @Test(expected = Exception.class)
    public void test_Invalid_Rectangle_When_Length_Zero() throws Exception {
        new Rectangle(0, 5);
    }

    @Test(expected = Exception.class)
    public void test_Invalid_Rectangle_When_Width_Negative() throws Exception {
        new Rectangle(6, -5);
    }

    @Test
    public void test_Rectangle_Area() throws Exception {
        Rectangle rectangle = new Rectangle(3, 4);
        assertEquals(12, rectangle.area(), 0);
    }


    @Test
    public void test_Rectangle_Perimeter() throws Exception {
        Rectangle rectangle = new Rectangle(7, 8);
        assertEquals(30, rectangle.perimeter(), 0);
    }


    @Test
    public void test_ToString() throws Exception {
        Rectangle rectangle = new Rectangle(8, 9);
        assertEquals("Rectangle 8.0 9.0", rectangle.toString());
    }
}