package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CircleTest {
    
    @Test
    public void test_valid_circle() {
        Circle circle = new Circle(5);
    }

    @Test(expected = ShapeException.class)
    public void test_invalid_circle_when_radius_zero() throws Exception {
        new Circle(0);
    }


    @Test
    public void test_circle_area() {
        Circle circle = new Circle(5);
        assertEquals(5*5*Math.PI, circle.area(), 0.001);
    }

    @Test
    public void test_circle_perimeter() {
        Circle circle = new Circle(5);
        assertEquals(5*2*Math.PI, circle.perimeter(), 0.001);
    }

}
