package org.ntut.posd2024f.shapes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TriangleTest {
    // Triangle t = new Triangle(...)

    @Test
    public void test_Valid_Triangle() throws Exception {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        new Triangle(vectors);
    }

    @Test(expected = ShapeException.class)
    public void test_Invalid_Triangle() throws Exception {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(3, 3));
        new Triangle(vectors);
    }

    @Test
    public void test_Triangle_Area() throws Exception {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        Triangle triangle = new Triangle(vectors);
        assertEquals(4.500, triangle.area(), 0.001);
    }


    @Test
    public void test_Triangle_Perimeter() throws Exception {
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        Triangle triangle = new Triangle(vectors);
        assertEquals(10.242, triangle.perimeter(), 0.001);
    }
}