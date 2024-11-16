package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class ShapeParserTest {

    //這個是我用來測試檔案的目錄
    @TempDir
    Path tempDir;
    
    private File createTempFile(String content) throws IOException {
        File tempFile = new File(tempDir.toFile(), "test.txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }

    @Test
    void test_Constructor_With_NonExistentFile() {
        File nonExistentFile = new File("nonexistent.txt");
        assertThrows(RuntimeException.class, () -> new ShapeParser(nonExistentFile));
    }

    @Test
    void test_ParseCircle() throws IOException {
        String content = "Circle 3.0";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Circle);
    }

    @Test
    void test_ParseRectangle() throws IOException {
        String content = "Rectangle 3.0 4.0";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Rectangle);
    }

    @Test
    void test_ParseTriangle() throws IOException {
        String content = "Triangle [4,0] [4,3] [0,3]";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof Triangle);
    }

    @Test
    void test_ParseConvexPolygon() throws IOException {
        String content = "ConvexPolygon [0,0] [0,4] [4,4] [4,0]";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof ConvexPolygon);
    }

    @Test
    void test_ParseEmptyCompoundShape() throws IOException {
        String content = "CompoundShape {}";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof CompoundShape);
    }

    @Test
    void test_ParseCompoundShape() throws IOException {
        String content = "CompoundShape {\n" +
                        "    Circle 3.0\n" +
                        "    Rectangle 3.0 4.0\n" +
                        "}";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof CompoundShape);
    }

    @Test
    void test_ParseComplexShape() throws IOException {
        String content = "CompoundShape {\n" +
                        "    Circle 3.0\n" +
                        "    CompoundShape {\n" +
                        "        Circle 3.0\n" +
                        "        Rectangle 3.0 4.0\n" +
                        "    }\n" +
                        "    Rectangle 3.0 4.0\n" +
                        "}";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof CompoundShape);
    }

    @Test
    void test_ParseColoredShape() throws IOException {
        String content = "Circle 3.0, color=RED";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof ColoredShape);
    }

    @Test
    void test_ParseTextedShape() throws IOException {
        String content = "Circle 3.0, text=This is a circle";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof TextedShape);
    }

    @Test
    void test_ParseColoredAndTextedShape() throws IOException {
        String content = "Circle 3.0, color=RED, text=This is a circle";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(1, shapes.size());
        assertTrue(shapes.get(0) instanceof TextedShape);
        assertTrue(((TextedShape)shapes.get(0)).getShape() instanceof ColoredShape);
    }

    @Test
    void test_ParseMultipleShapes() throws IOException {
        String content = "Circle 3.0\n" +
                        "ConvexPolygon [0,0] [0,4] [4,4] [4,0]\n" +
                        "CompoundShape {\n" +
                        "    Circle 3.0\n" +
                        "    Rectangle 3.0 4.0\n" +
                        "}";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();
        
        assertEquals(3, shapes.size());
        assertTrue(shapes.get(0) instanceof Circle);
        assertTrue(shapes.get(1) instanceof ConvexPolygon);
        assertTrue(shapes.get(2) instanceof CompoundShape);
    }

    @Test
    void test_InvalidVectorFormat() throws IOException {
        String content = "Triangle 4,0] [4,3] [0,3]";  // Missing '['
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        assertThrows(IllegalArgumentException.class, () -> parser.parse());
    }

    @Test
    void test_MissingClosingBrace() throws IOException {
        String content = "CompoundShape {\n" +
                        "    Circle 3.0\n" +
                        "    Rectangle 3.0 4.0\n";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        assertThrows(IllegalArgumentException.class, () -> parser.parse());
    }

    @Test
    void test_MissingOpeningBrace() throws IOException {
        String content = "CompoundShape\n" +
                        "    Circle 3.0\n" +
                        "    Rectangle 3.0 4.0\n" +
                        "}";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        assertThrows(IllegalArgumentException.class, () -> parser.parse());
    }

    @Test
    void test_UnknownShapeType() throws IOException {
        String content = "Unknown 3.0";
        File tempFile = createTempFile(content);
        
        ShapeParser parser = new ShapeParser(tempFile);
        assertThrows(IllegalArgumentException.class, () -> parser.parse());
    }
}
