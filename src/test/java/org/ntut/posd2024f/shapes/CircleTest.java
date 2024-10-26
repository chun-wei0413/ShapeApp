package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CircleTest {
    
    @Test
    public void test_valid_circle() throws Exception{
        Circle circle = new Circle(5);
        assertEquals("Circle 5.0", circle.toString());
    }

    @Test(expected = Exception.class)
    public void test_invalid_circle() throws Exception{
        new Circle(-1);
    }

    @Test
    public void test_circle_area() throws Exception{
        Circle circle = new Circle(5);
        assertEquals(5*5*Math.PI, circle.area(), 0.001);
    }

    @Test
    public void test_circle_perimeter() throws Exception{
        Circle circle = new Circle(5);
        assertEquals(5*2*Math.PI, circle.perimeter(), 0.001);
    }

    @Test
    public void test_circle_toString() throws Exception{
        Circle circle = new Circle(5);
        assertEquals("Circle 5.0", circle.toString());
    }
}
