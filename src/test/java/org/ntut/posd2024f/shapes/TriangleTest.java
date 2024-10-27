package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TriangleTest {
    
    @Test
    public void test_valid_triangle() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        new Triangle(vectors);

    }
    
    @Test
    public void test_invalid_triangle() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(3, 3));
        assertThrows(ShapeException.class, () -> new Triangle(vectors));

    }

    @Test
    public void test_triangle_area() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        Triangle triangle = new Triangle(vectors);
        assertEquals(4.500, triangle.area(), 0.001);

    }

    @Test
    public void test_triangle_perimeter() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        Triangle triangle = new Triangle(vectors);
        assertEquals(10.242, triangle.perimeter(), 0.001);

    }
}
