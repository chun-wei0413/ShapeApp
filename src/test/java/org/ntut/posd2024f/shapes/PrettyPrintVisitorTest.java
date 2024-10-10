package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PrettyPrintVisitorTest {

    @Test
    public void testVisitCircle() {
        Circle circle = new Circle(5.0);
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        circle.accept(visitor);

        String expectedOutput = "Circle 5.0";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void testVisitRectangle() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        rectangle.accept(visitor);

        String expectedOutput = "Rectangle 3.0 4.0";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void testVisitTriangle() {
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(3, 0));
        vectors.add(new TwoDimensionalVector(0, 4));
        Triangle triangle = new Triangle(vectors);
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        triangle.accept(visitor);

        String expectedOutput = "Triangle [0,0] [3,0] [0,4]";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void testVisitCompoundShape() {
        Circle circle = new Circle(3.0);
        Rectangle rectangle = new Rectangle(3.0, 4.0);

        CompoundShape compoundShape = new CompoundShape();
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        compoundShape.accept(visitor);

        String expectedOutput = "CompoundShape {\n  Circle 3.0\n  Rectangle 3.0 4.0\n}";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void testVisitTextedShape() {
        Circle circle = new Circle(1.0);
        TextedShape textedShape = new TextedShape(circle, "Hello");

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        textedShape.accept(visitor);

        String expectedOutput = "Circle 1.0, text: Hello";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void testVisitColoredShape() {
        Circle circle = new Circle(2.0);
        ColoredShape coloredShape = new ColoredShape(circle, "Red");

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        coloredShape.accept(visitor);

        // 因為控制台顏色代碼無法顯示，測試不檢查顏色代碼，只檢查形狀輸出
        String expectedOutput = "\033[0;31mCircle 2.0\033[0m";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void testVisitComplexCompoundShape() {
        Circle circle = new Circle(3.0);
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(4, 0));
        vectors.add(new TwoDimensionalVector(0, 3));

        Triangle triangle = new Triangle(vectors);

        CompoundShape innerCompound = new CompoundShape();
        innerCompound.add(triangle);

        CompoundShape outerCompound = new CompoundShape();
        outerCompound.add(circle);
        outerCompound.add(innerCompound);
        outerCompound.add(rectangle);

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        outerCompound.accept(visitor);

        String expectedOutput = "CompoundShape {\n  Circle 3.0\n  CompoundShape {\n    Triangle [0,0] [4,0] [0,3]\n  }\n  Rectangle 3.0 4.0\n}";
        assertEquals(expectedOutput, visitor.getResult());
    }
}
