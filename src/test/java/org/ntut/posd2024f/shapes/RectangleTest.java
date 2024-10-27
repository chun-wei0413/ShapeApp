package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RectangleTest {

    @Test
    public void test_valid_rectangle() {
        Rectangle rectangle = new Rectangle(5, 4);
    }

    @Test
    public void test_invalid_rectangle() {
        assertThrows(ShapeException.class, () -> {
            new Rectangle(0, -1);
        });
    }

    @Test
    public void test_rectangle_area() {
        Rectangle rectangle = new Rectangle(5, 4);
        assertEquals(20, rectangle.area(), 0.001);
    }

    @Test
    public void test_rectangle_perimeter() {
        Rectangle rectangle = new Rectangle(5, 4);
        assertEquals( 18, rectangle.perimeter(), 0.001);
    }


}
