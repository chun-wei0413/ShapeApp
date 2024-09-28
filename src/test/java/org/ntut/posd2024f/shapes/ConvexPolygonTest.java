package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

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

    @Test(expected = ShapeException.class)
    public void test_Invalid_ConvexPolygon() throws Exception {
    List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(3, 7));
        vectors.add(new TwoDimensionalVector(-5, 3));
        vectors.add(new TwoDimensionalVector(6, 2));
        vectors.add(new TwoDimensionalVector(4, -2));
        vectors.add(new TwoDimensionalVector(1, 1));
        new ConvexPolygon(vectors);
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
}
