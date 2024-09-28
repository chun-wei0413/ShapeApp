package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class CompoundShapeTest {
    
    @Test
    public void test_compoundShape(){
        new CompoundShape();
    }

    @Test
    public void test_compoundShape_area() throws Exception {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Circle(5));
        compoundShape.add(new Rectangle(4, 5));

        assertEquals((5*5*Math.PI)+(4*5) ,compoundShape.area(), 0.001);
    }

    @Test
    public void test_compoundShape_perimeter() throws Exception {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Circle(5));
        compoundShape.add(new Rectangle(4, 5));

        assertEquals((5*2*Math.PI)+((4+5)*2) ,compoundShape.perimeter(), 0.001);
    }

    @Test
    public void test_compoundShape_add() throws Exception {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(4, 5));
        compoundShape.add(new Circle(5));
        compoundShape.add(new Circle(3));
        
        assertEquals(3, compoundShape.size()); 
    }

    @Test
    public void test_compoundShape_iterator() throws Exception {
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(4, 5));
        compoundShape.add(new Circle(5));
        compoundShape.add(new Circle(3));

        Iterator<Shape> iteratorShape = compoundShape.iterator();
        assertTrue(iteratorShape.hasNext());
        assertEquals(iteratorShape.next().getClass(), Rectangle.class);
    }
}
