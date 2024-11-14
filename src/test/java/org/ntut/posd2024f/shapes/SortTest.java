package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SortTest {
    
    @Test
    public void test_area_ascending() {
        ArrayList<Shape> shapes = new ArrayList<>();
        ArrayList<Double> expectedArea = new ArrayList<>();

        expectedArea.add(4.5);
        expectedArea.add(20.0);
        expectedArea.add(78.5397);

        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        Triangle triangle = new Triangle(vectors);

        shapes.add(triangle);//4.5
        shapes.add(new Rectangle(4, 5));//20
        shapes.add(new Circle(5));//78.5397

        shapes.sort(Sort.BY_AREA_ASCENDING);

        int i=0;
        for(Shape shape : shapes){
            assertEquals(shape.area(), expectedArea.get(i), 0.001);
            i++;
        }
    }

    @Test
    public void test_area_descending() {
        ArrayList<Shape> shapes = new ArrayList<>();
        ArrayList<Double> expectedArea = new ArrayList<>();

        expectedArea.add(78.5397);
        expectedArea.add(20.0);
        expectedArea.add(4.5);
        
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        Triangle triangle = new Triangle(vectors);

        shapes.add(triangle);//4.5
        shapes.add(new Rectangle(4, 5));//20
        shapes.add(new Circle(5));//78.5397

        shapes.sort(Sort.BY_AREA_DESCENDING);

        int i=0;
        for(Shape shape : shapes){
            assertEquals(expectedArea.get(i), shape.area(), 0.001);
            i++;
        }
    }

    @Test
    public void test_perimeter_ascending() {
        ArrayList<Shape> shapes = new ArrayList<>();
        ArrayList<Double> expectedArea = new ArrayList<>();

        expectedArea.add(10.242);
        expectedArea.add(18.0);
        expectedArea.add(31.4159);

        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        Triangle triangle = new Triangle(vectors);

        shapes.add(triangle);
        shapes.add(new Rectangle(4, 5));//18
        shapes.add(new Circle(5));//31.4159

        shapes.sort(Sort.BY_PERIMETER_ASCENDING);

        int i=0;
        for(Shape shape : shapes){
            assertEquals(expectedArea.get(i), shape.perimeter(), 0.001);
            i++;
        }
    }

    @Test
    public void test_perimeter_descending() {
        ArrayList<Shape> shapes = new ArrayList<>();
        ArrayList<Double> expectedArea = new ArrayList<>();

        expectedArea.add(31.4159);
        expectedArea.add(18.0);
        expectedArea.add(10.242);

        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        Triangle triangle = new Triangle(vectors);

        shapes.add(triangle);
        shapes.add(new Rectangle(4, 5));//18
        shapes.add(new Circle(5));//31.4159

        shapes.sort(Sort.BY_PERIMETER_DESCENDING);

        int i=0;
        for(Shape shape : shapes){
            assertEquals(expectedArea.get(i), shape.perimeter(), 0.001);
            i++;
        }
    }
}