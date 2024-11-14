package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TriangleTest {
    // Triangle t = new Triangle(...)

    @Test
    public void test_Valid_Triangle() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        new Triangle(vectors);
    }

    @Test
    public void test_Invalid_Triangle() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(3, 3));
        assertThrows(ShapeException.class, () -> new Triangle(vectors));
    }

    @Test
    public void test_Triangle_Area() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        Triangle triangle = new Triangle(vectors);
        assertEquals(4.500, triangle.area(), 0.001);
    }


    @Test
    public void test_Triangle_Perimeter() {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        Triangle triangle = new Triangle(vectors);
        assertEquals(10.242, triangle.perimeter(), 0.001);
    }

    @Test
    public void test_Triangle_getVectors(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        Triangle triangle = new Triangle(vectors);
        List<TwoDimensionalVector> testVectors = triangle.getVectors();

        assertEquals(1, testVectors.get(0).getX());
        assertEquals(1, testVectors.get(0).getY());
        assertEquals(4, testVectors.get(1).getX());
        assertEquals(1, testVectors.get(1).getY());
        assertEquals(1, testVectors.get(2).getX());
        assertEquals(4, testVectors.get(2).getY());
    }
}