package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class TextedShapeTest {

    @Test
    public void test_TextedShape_area(){
        TextedShape textedShape = new TextedShape(new Rectangle(3, 4), "test");
        assertEquals(12, textedShape.area(), 0);
    }

    @Test
    public void test_TextedShape_perimeter(){
        TextedShape textedShape = new TextedShape(new Rectangle(3, 4), "test");
        assertEquals(14, textedShape.perimeter(), 0);
    }

    @Test
    public void test_TextedShape_add(){
        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(3, 4));
        ColoredShape coloredShape = new ColoredShape(compoundShape, "test");
        coloredShape.add(new Circle(3));

        assertEquals(2, compoundShape.size());
    }

    @Test
    public void test_TextedShape_iterator(){
        Shape compoundShape = new CompoundShape();
        compoundShape.add(new Rectangle(3, 4));
        compoundShape.add(new Circle(3));
        ColoredShape coloredShape = new ColoredShape(compoundShape, "test");
        Iterator<Shape> i = coloredShape.iterator();
        assertTrue(i.hasNext());
        assertEquals(Rectangle.class, i.next().getClass());
        assertTrue(i.hasNext());
        assertEquals(Circle.class, i.next().getClass());
        assertFalse(i.hasNext());
    }

    @Test
    public void test_TextedShape_getText(){
        TextedShape textedShape = new TextedShape(new Rectangle(3, 4), "test");
        assertEquals("test", textedShape.getText());
    }

    @Test
    public void test_TextedShape_getShape(){
        TextedShape textedShape = new TextedShape(new Rectangle(3, 4), "test");
        assertEquals(new Rectangle(3, 4), textedShape.getShape());
    }

}
