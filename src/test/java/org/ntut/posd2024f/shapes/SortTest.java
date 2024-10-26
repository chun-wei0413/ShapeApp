package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SortTest {
    @Test
    public void test_area_ascending() throws Exception{
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));//25
        shapes.add(new Rectangle(5, 4));//20
        shapes.add(new Triangle(3, 4, 5));//6

        shapes.sort(Sort.BY_AREA_ASCENDING);

        assertEquals("Triangle 3.0 4.0 5.0", shapes.get(0).toString());
        assertEquals("Rectangle 5.0 4.0", shapes.get(1).toString());
        assertEquals("Circle 5.0", shapes.get(2).toString());
    }

    @Test
    public void test_area_descending() throws Exception{
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));//25
        shapes.add(new Rectangle(5, 4));//20
        shapes.add(new Triangle(3, 4, 5));//6

        shapes.sort(Sort.BY_AREA_DESCENDING);

        assertEquals("Circle 5.0", shapes.get(0).toString());
        assertEquals("Rectangle 5.0 4.0", shapes.get(1).toString());
        assertEquals("Triangle 3.0 4.0 5.0", shapes.get(2).toString());
    }

    @Test
    public void test_perimeter_ascending() throws Exception{
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));//31.4
        shapes.add(new Rectangle(5, 4));//18
        shapes.add(new Triangle(3, 4, 5));//12

        shapes.sort(Sort.BY_PERIMETER_ASCENDING);

        assertEquals("Triangle 3.0 4.0 5.0", shapes.get(0).toString());
        assertEquals("Rectangle 5.0 4.0", shapes.get(1).toString());
        assertEquals("Circle 5.0", shapes.get(2).toString());
    }
    
    @Test
    public void test_perimeter_descending() throws Exception{
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));//31.4
        shapes.add(new Rectangle(5, 4));//18
        shapes.add(new Triangle(3, 4, 5));//12

        shapes.sort(Sort.BY_PERIMETER_DESCENDING);

        assertEquals("Circle 5.0", shapes.get(0).toString());
        assertEquals("Rectangle 5.0 4.0", shapes.get(1).toString());
        assertEquals("Triangle 3.0 4.0 5.0", shapes.get(2).toString());
    }
}
