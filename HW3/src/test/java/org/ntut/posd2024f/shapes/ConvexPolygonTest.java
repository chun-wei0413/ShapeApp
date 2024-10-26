package org.ntut.posd2024f.shapes;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class ConvexPolygonTest {

    @Test
    public void test_valid_ConvexPolygon() {

        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));   // A
        vectors.add(new TwoDimensionalVector(4, 0));   // B
        vectors.add(new TwoDimensionalVector(5, 3));   // C
        vectors.add(new TwoDimensionalVector(2, 5));   // D
        vectors.add(new TwoDimensionalVector(-1, 3));  // E
        new ConvexPolygon(vectors);
    }

    @Test
    public void test_Invalid_ConvexPolygon() {
    List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(3, 7));
        vectors.add(new TwoDimensionalVector(-5, 3));
        vectors.add(new TwoDimensionalVector(6, 2));
        vectors.add(new TwoDimensionalVector(4, -2));
        vectors.add(new TwoDimensionalVector(1, 1));
        assertThrows(ShapeException.class, () -> new ConvexPolygon(vectors));
    }

    @Test
    public void test_ConvexPolygon_area(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));   // A
        vectors.add(new TwoDimensionalVector(4, 0));   // B
        vectors.add(new TwoDimensionalVector(5, 3));   // C
        vectors.add(new TwoDimensionalVector(2, 5));   // D
        vectors.add(new TwoDimensionalVector(-1, 3));  // E

        ConvexPolygon convexPolygon = new ConvexPolygon(vectors);
        assertEquals(21.0, convexPolygon.area(), 0.001);
    }

    @Test
    public void test_ConvexPolygon_perimeter(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));   // A
        vectors.add(new TwoDimensionalVector(4, 0));   // B
        vectors.add(new TwoDimensionalVector(5, 3));   // C
        vectors.add(new TwoDimensionalVector(2, 5));   // D
        vectors.add(new TwoDimensionalVector(-1, 3));  // E

        ConvexPolygon convexPolygon = new ConvexPolygon(vectors);
        assertEquals(17.535, convexPolygon.perimeter(), 0.001);
    }

    @Test
    public void test_ConvexPolygon_getVectors(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));   // A
        vectors.add(new TwoDimensionalVector(4, 0));   // B
        vectors.add(new TwoDimensionalVector(5, 3));   // C
        
        ConvexPolygon convexPolygon = new ConvexPolygon(vectors);
        List<TwoDimensionalVector> testVectors = convexPolygon.getVectors();
        
        assertEquals(0 ,testVectors.get(0).getX());
        assertEquals(0 ,testVectors.get(0).getY());
        assertEquals(4 ,testVectors.get(1).getX());
        assertEquals(0 ,testVectors.get(1).getY());
        assertEquals(5 ,testVectors.get(2).getX());
        assertEquals(3 ,testVectors.get(2).getY());
    }
}
