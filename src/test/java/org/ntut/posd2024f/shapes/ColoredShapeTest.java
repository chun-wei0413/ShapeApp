package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class ColoredShapeTest {
    @Test
    public void test_Invalid_ColoredShape() {
        assertThrows(ShapeException.class, () ->
            new ColoredShape(new Circle(-1), "RED"));
    }

    @Test
    public void test_ColoredShape_area() {
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "RED");
        assertEquals(12, coloredShape.area(), 0);
    }

    @Test
    public void test_ColoredShape_perimeter() {
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "RED");
        assertEquals(14, coloredShape.perimeter(), 0);
    }

    @Test
    public void test_ColoredShape_add() {
        CompoundShape compoundShape = new CompoundShape();
        ColoredShape coloredShape = new ColoredShape(compoundShape, "RED");
        coloredShape.add(new Rectangle(3, 4));
        coloredShape.add(new Circle(3));

        assertEquals(2, compoundShape.size());
    }

    @Test
    public void test_ColorShape_Invalid_add() {
        Rectangle rectangle = new Rectangle(3, 4);
        ColoredShape coloredShape = new ColoredShape(rectangle, "BLUE");

        assertThrows(ShapeException.class, () ->
            coloredShape.add(new Circle(4)));
    }

    @Test
    public void test_ColoredShape_iterator() {
        Shape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(3, 4));
        compoundShape.add(new Circle(3));
        ColoredShape coloredShape = new ColoredShape(compoundShape, "RED");
        Iterator<Shape> i = coloredShape.iterator();
        assertTrue(i.hasNext());
        assertEquals(Rectangle.class, i.next().getClass());
        assertTrue(i.hasNext());
        assertEquals(Circle.class, i.next().getClass());
        assertFalse(i.hasNext());
    }

    @Test
    public void test_ColoredShape_Invalid_iterator() {
        Shape circle = new Circle(5);
        ColoredShape coloredShape = new ColoredShape(circle, "GREEN");
        Iterator<Shape> it = coloredShape.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, () -> 
            it.next());
    }

    @Test
    public void test_ColoredShape_getColor() {
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "RED");
        assertEquals("RED", coloredShape.getColor());
    }

    @Test
    public void test_ColoredShape_getShape() {
        ColoredShape coloredShape = new ColoredShape(new Rectangle(3, 4), "RED");
        assertEquals(new Rectangle(3, 4), coloredShape.getShape());
    }
}
