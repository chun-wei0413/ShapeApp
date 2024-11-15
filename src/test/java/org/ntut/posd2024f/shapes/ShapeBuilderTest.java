package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ShapeBuilderTest {

    @Test
    public void build_Circle_test() {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        shapeBuilder.buildCircle(5, null, null);
        assertEquals(new Circle(5).area(), shapeBuilder.getResult().get(0).area());
    }

    @Test
    public void build_Rectangle_test() {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        shapeBuilder.buildRectangle(5, 4, null, null);
        assertEquals(new Rectangle(5, 4).area(), shapeBuilder.getResult().get(0).area());
    }

    @Test
    public void build_Triangle_test() {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        List<TwoDimensionalVector>vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(1, 1));
        vectors.add(new TwoDimensionalVector(4, 1));
        vectors.add(new TwoDimensionalVector(1, 4));

        shapeBuilder.buildTriangle(vectors, null, null);
        assertEquals(new Triangle(vectors).area(), shapeBuilder.getResult().get(0).area(), 0.001);
    }

    @Test
    public void build_ConvexPolygon_test() {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));   // A
        vectors.add(new TwoDimensionalVector(4, 0));   // B
        vectors.add(new TwoDimensionalVector(5, 3));   // C
        vectors.add(new TwoDimensionalVector(2, 5));   // D
        vectors.add(new TwoDimensionalVector(-1, 3));  // E

        shapeBuilder.buildConvexPolygon(vectors, null, null);
        assertEquals(new ConvexPolygon(vectors).area(), shapeBuilder.getResult().get(0).area(), 0.001);

    }

    //我用prettyPrintVisitor去確認build出來的是不是textShape
    @Test
    public void build_textShape_test() {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        PrettyPrintVisitor expectVisitor = new PrettyPrintVisitor();
        PrettyPrintVisitor answerVisitor = new PrettyPrintVisitor();

        TextedShape expectShape = new TextedShape(new Circle(5), "test");

        shapeBuilder.buildCircle(5, null, "test");
        shapeBuilder.getResult().get(0).accept(answerVisitor);

        expectShape.accept(expectVisitor);
        

        assertEquals(expectVisitor.getResult(), answerVisitor.getResult());
    }

    //我用prettyPrintVisitor去確認build出來的是不是coloredShape
    @Test
    public void build_colorShape_test() {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        PrettyPrintVisitor expectVisitor = new PrettyPrintVisitor();
        PrettyPrintVisitor answerVisitor = new PrettyPrintVisitor();

        ColoredShape expectShape = new ColoredShape(new Circle(5), "RED");
        shapeBuilder.buildCircle(5, "RED", null);
        shapeBuilder.getResult().get(0).accept(answerVisitor);

        expectShape.accept(expectVisitor);
        

        assertEquals(expectVisitor.getResult(), answerVisitor.getResult());
    }
    
    @Test
    public void build_CompoundShape_test() {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        shapeBuilder.beginBuildCompoundShape(null, null);
        shapeBuilder.buildCircle(5, null, null);
        shapeBuilder.buildRectangle(4, 5, null, null);
        shapeBuilder.endBuildCompoundShape();

        Iterator<Shape> it = shapeBuilder.getResult().get(0).iterator();

        assertTrue(it.hasNext());
        assertEquals(new Circle(5).area(), it.next().area());
        assertTrue(it.hasNext());
        assertEquals(new Rectangle(4, 5).area(), it.next().area());
        assertFalse(it.hasNext());

    }
}
