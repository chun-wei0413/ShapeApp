package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class SortTest {
    
    @Test
    public void test_area_ascending() throws Exception {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(4, 5));//20
        shapes.add(new Triangle(3,4,5));//6
        shapes.add(new Circle(5));//78.5397

        ArrayList<Shape> expectedShapes = new ArrayList<>();
        expectedShapes.add(new Triangle(3, 4, 5));//6
        expectedShapes.add(new Rectangle(4, 5));//20
        expectedShapes.add(new Circle(5));//78.5397

        shapes.sort(Sort.BY_AREA_ASCENDING);

        assertEquals(expectedShapes, shapes);
    }

    @Test
    public void test_area_descending() throws Exception {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(4, 5));//20
        shapes.add(new Triangle(3,4,5));//6
        shapes.add(new Circle(5));//78.5397

        ArrayList<Shape> expectedShapes = new ArrayList<>();
        expectedShapes.add(new Circle(5));//78.5397
        expectedShapes.add(new Rectangle(4, 5));//20 
        expectedShapes.add(new Triangle(3, 4, 5));//6

        shapes.sort(Sort.BY_AREA_DESCENDING);

        assertEquals(expectedShapes, shapes);
    }

    @Test
    public void test_perimeter_ascending() throws Exception {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(4, 5));//20
        shapes.add(new Triangle(3,4,5));//12
        shapes.add(new Circle(5));//31.4159

        ArrayList<Shape> expectedShapes = new ArrayList<>();
        expectedShapes.add(new Triangle(3,4,5));//12
        expectedShapes.add(new Rectangle(4, 5));//20
        expectedShapes.add(new Circle(5));//31.4159

        shapes.sort(Sort.BY_PERIMETER_ASCENDING);

        assertEquals(expectedShapes, shapes);
    }

    @Test
    public void test_perimeter_descending() throws Exception {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(4, 5));//20
        shapes.add(new Triangle(3,4,5));//12
        shapes.add(new Circle(5));//31.4159

        ArrayList<Shape> expectedShapes = new ArrayList<>();
        expectedShapes.add(new Circle(5));//31.4159
        expectedShapes.add(new Rectangle(4, 5));//20
        expectedShapes.add(new Triangle(3,4,5));//12

        shapes.sort(Sort.BY_PERIMETER_DESCENDING);

        assertEquals(expectedShapes, shapes);
    }
}