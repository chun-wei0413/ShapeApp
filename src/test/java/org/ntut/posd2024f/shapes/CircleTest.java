package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CircleTest {
    
    @Test
    public void test_valid_circle() {
        Circle circle = new Circle(5);
        assertEquals("Circle 5.0", circle.toString());
    }

    @Test
    public void test_invalid_circle() {
        assertThrows(ShapeException.class, () -> {
            new Circle(-1);
        });
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
