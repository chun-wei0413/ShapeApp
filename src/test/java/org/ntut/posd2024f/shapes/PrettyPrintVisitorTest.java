package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PrettyPrintVisitorTest {

    @Test
    public void test_Visit_Circle() {
        Circle circle = new Circle(5.0);
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        circle.accept(visitor);

        String expectedOutput = "Circle 5.0";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void test_Visit_Rectangle() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        rectangle.accept(visitor);

        String expectedOutput = "Rectangle 3.0 4.0";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void test_Visit_Triangle() {
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
    public void test_Visit_CompoundShape() {
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
    public void test_Visit_TextedShape() {
        Circle circle = new Circle(1.0);
        TextedShape textedShape = new TextedShape(circle, "Hello");

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        textedShape.accept(visitor);

        String expectedOutput = "Circle 1.0, text: Hello";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void test_Visit_ColoredShape() {
        Circle circle = new Circle(2.0);
        ColoredShape coloredShape = new ColoredShape(circle, "RED");

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        coloredShape.accept(visitor);

        // 因為控制台顏色代碼無法顯示，測試不檢查顏色代碼，只檢查形狀輸出
        String expectedOutput = "\033[0;31mCircle 2.0\033[0m";
        assertEquals(expectedOutput, visitor.getResult());
    }

    @Test
    public void test_Visit_Complex_CompoundShape() {
        List<TwoDimensionalVector> vectors = new ArrayList();
        vectors.add(new TwoDimensionalVector(4, 0));
        vectors.add(new TwoDimensionalVector(4, 3));
        vectors.add(new TwoDimensionalVector(0, 3));

        CompoundShape innerinner = new CompoundShape();
        innerinner.add(new Triangle(vectors));

        CompoundShape inner = new CompoundShape();
        inner.add(innerinner);
        inner.add(new TextedShape(new Rectangle(3, 4), "this is a rectangle with blue color"));

        CompoundShape outer = new CompoundShape();
        outer.add(new Circle(3));
        outer.add(inner);

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        outer.accept(visitor);

        String expectedOutput = "CompoundShape {\n  Circle 3.0\n  CompoundShape {\n    CompoundShape {\n      Triangle [4,0] [4,3] [0,3]\n    }\n    Rectangle 3.0 4.0, text: this is a rectangle with blue color\n  }\n}";
        assertEquals(expectedOutput, visitor.getResult());
    }
}
