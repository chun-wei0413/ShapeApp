package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class ColoredShapeTest {
    @Test
    public void test_ColoredShape_area(){
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "red");
        assertEquals(12, coloredShape.area(), 0);
    }

    @Test
    public void test_ColoredShape_perimeter(){
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "red");
        assertEquals(14, coloredShape.perimeter(), 0);
    }

    @Test
    public void test_ColoredShape_add(){
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(3, 4));
        ColoredShape coloredShape = new ColoredShape(compoundShape, "red");
        coloredShape.add(new Circle(3));

        assertEquals(2, compoundShape.size());
    }

    @Test
    public void test_ColoredShape_iterator(){
        Shape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(3, 4));
        compoundShape.add(new Circle(3));
        ColoredShape coloredShape = new ColoredShape(compoundShape, "red");
        Iterator<Shape> i = coloredShape.iterator();
        assertTrue(i.hasNext());
        assertEquals(Rectangle.class, i.next().getClass());
        assertTrue(i.hasNext());
        assertEquals(Circle.class, i.next().getClass());
        assertFalse(i.hasNext());
    }

    @Test
    public void test_ColoredShape_getColor(){
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "red");
        assertEquals("red", coloredShape.getColor());
    }

    @Test
    public void test_ColoredShape_getShape(){
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "red");
        assertEquals(new Rectangle(3, 4), coloredShape.getShape());
    }
}
