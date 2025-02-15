package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class CompoundShapeTest {
    
    @Test
    public void test_compoundshape_area() {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Circle(5));
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));
        compoundShape.add(new Triangle(vectors));
        compoundShape.add(new Rectangle(5, 4));

        assertEquals(103.039, compoundShape.area(), 0.001);
    }

    @Test
    public void test_compoundShape_perimeter() {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Circle(5));
        compoundShape.add(new Rectangle(4, 5));

        assertEquals((5*2*Math.PI)+((4+5)*2) ,compoundShape.perimeter(), 0.001);
    }

    @Test
    public void test_compoundShape_add() {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(4, 5));
        compoundShape.add(new Circle(5));
        compoundShape.add(new Circle(3));
        
        assertEquals(3, compoundShape.size());
    }

    @Test
    public void test_compoundShape_iterator() {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(4, 5));
        compoundShape.add(new Circle(5));
        compoundShape.add(new Circle(3));

        Iterator<Shape> iteratorShape = compoundShape.iterator();
        assertTrue(iteratorShape.hasNext());
        assertEquals(Rectangle.class, iteratorShape.next().getClass());
        assertTrue(iteratorShape.hasNext());
        assertEquals(Circle.class, iteratorShape.next().getClass());
        assertTrue(iteratorShape.hasNext());
        assertEquals(Circle.class, iteratorShape.next().getClass());
        assertFalse(iteratorShape.hasNext());
    }

}
