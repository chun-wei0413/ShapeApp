package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriangleTest {
    
    @Test
    public void test_valid_triangle() throws Exception{
        Triangle triangle = new Triangle(3,4,5);
        assertEquals("Triangle 3.0 4.0 5.0", triangle.toString());
    }
    
    @Test(expected = Exception.class)
    public void test_invalid_triangle() throws Exception{
        new Triangle(2,4,7);
    }

    @Test
    public void test_triangle_area() throws Exception{
        Triangle triangle = new Triangle(3,4,5);
        assertEquals(6, triangle.area(), 0.001);
    }

    @Test
    public void test_triangle_perimeter() throws Exception{
        Triangle triangle = new Triangle(3,4,5);
        assertEquals(12, triangle.perimeter(), 0.001);
    }

    @Test
    public void test_triangle_toStirng() throws Exception{
        Triangle triangle = new Triangle(3,4,5);
        assertEquals("Triangle 3.0 4.0 5.0", triangle.toString());
    }
}
