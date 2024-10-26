package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RectangleTest {

    @Test
    public void test_valid_rectangle() throws Exception{
        Rectangle rectangle = new Rectangle(5, 4);
        assertEquals("Rectangle 5.0, 4.0", rectangle.toString());
    }

    @Test(expected = Exception.class)
    public void test_invalid_rectangle() throws Exception{
        new Rectangle(0, -1);
    }

    @Test
    public void test_rectangle_area() throws Exception{
        Rectangle rectangle = new Rectangle(5, 4);
        assertEquals(20, rectangle.area(), 0.001);
    }

    @Test
    public void test_rectangle_perimeter() throws Exception{
        Rectangle rectangle = new Rectangle(5, 4);
        assertEquals( 18, rectangle.perimeter(), 0.001);
    }

    @Test
    public void test_valid_toString() throws Exception{
        Rectangle rectangle = new Rectangle(5, 4);
        assertEquals("Rectangle 5.0, 4.0", rectangle.toString());
    }
}
