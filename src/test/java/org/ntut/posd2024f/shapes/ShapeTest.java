package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShapeTest {
    
    @Test
    public void test_shape_area() throws Exception{
        Shape circle = new Circle(5);
        assertEquals(5*5*Math.PI, circle.area(), 0.001);
    }

    @Test
    public void test_shape_perimeter() throws Exception{
        Shape trangle = new Triangle(3, 4, 5);
        assertEquals(12, trangle.perimeter(), 0.001);
    }

    @Test
    public void test_shape_toString() throws Exception{
        Shape rectangle = new Rectangle(5, 4);
        assertEquals("Rectangle 5.0 4.0", rectangle.toString());
    }
}
