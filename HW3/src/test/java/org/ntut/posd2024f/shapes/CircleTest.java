package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CircleTest {
    // e.g. Circle c = new Circle(...)

    @Test
    public void test_valid_circle() {
        new Circle(5.0);
    }

    @Test
    public void test_invalid_circle_when_radius_zero() {
        assertThrows(ShapeException.class, () -> new Circle(0));
    }

    @Test
    public void test_invalid_circle_when_radius_negative() {
        assertThrows(ShapeException.class, () -> new Circle(-1));
    }

    @Test
    public void test_circle_area() {
        Circle circle = new Circle(2.0);
        assertEquals(2*2*Math.PI, circle.area(), 0.001);
    }

    @Test
    public void test_circle_perimeter() {
        Circle circle = new Circle(3.0);
        assertEquals(3*2*Math.PI, circle.perimeter(), 0.001);
    }

    @Test
    public void test_circle_getRadius(){
        Circle circle = new Circle(5);
        assertEquals(5, circle.getRadius());
    }
}