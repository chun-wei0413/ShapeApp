package org.ntut.posd2024f.shapes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShapeParser {
    private File file;
    private ShapeBuilder builder;
    private List<Shape> shapes;
    
    public ShapeParser(File file) {
        validateFile(file);
        this.file = file;
        this.builder = new ShapeBuilder();
        this.shapes = new ArrayList<>();
    }

    private void validateFile(File file) {
        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }
    }

    public void parse() {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    parseLine(sc, line);
                }
            }
            shapes = builder.getResult();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    public List<Shape> getResult() {
        return shapes;
    }

    private void parseLine(Scanner sc, String line) {
        ShapeType shapeType = determineShapeType(line);
        switch (shapeType) {
            case CIRCLE:
                parseCircle(line);
                break;
            case RECTANGLE:
                parseRectangle(line);
                break;
            case TRIANGLE:
                parseTriangle(line);
                break;
            case CONVEX_POLYGON:
                parseConvexPolygon(line);
                break;
            case COMPOUND_SHAPE:
                parseCompoundShape(sc, line);
                break;
            default:
                throw new IllegalArgumentException("Unknown shape type");
        }
    }

    private ShapeType determineShapeType(String line) {
        if (line.startsWith("Circle")) return ShapeType.CIRCLE;
        if (line.startsWith("Rectangle")) return ShapeType.RECTANGLE;
        if (line.startsWith("Triangle")) return ShapeType.TRIANGLE;
        if (line.startsWith("ConvexPolygon")) return ShapeType.CONVEX_POLYGON;
        if (line.startsWith("CompoundShape")) return ShapeType.COMPOUND_SHAPE;
        //這個我用來debugㄉ
        throw new IllegalArgumentException("Unknown shape type");
    }

    private void parseCircle(String line) {
        String[] parts = line.split(", ");
        double radius = Double.parseDouble(parts[0].split(" ")[1].trim());
        
        DecoratorInfo decorInfo = parseDecoratorInfo(parts);
        builder.buildCircle(radius, decorInfo.color, decorInfo.text);
    }

    private void parseRectangle(String line) {
        String[] parts = line.split(", ");
        String[] dimensions = parts[0].split(" ");
        double length = Double.parseDouble(dimensions[1].trim());
        double width = Double.parseDouble(dimensions[2].trim());
        
        DecoratorInfo decorInfo = parseDecoratorInfo(parts);
        builder.buildRectangle(length, width, decorInfo.color, decorInfo.text);
    }

    private void parseTriangle(String line) {
        String[] parts = line.split(", ");
        String vectorString = parts[0].split(" ", 2)[1].trim();
        List<TwoDimensionalVector> vectors = parseVectors(vectorString);
        
        DecoratorInfo decorInfo = parseDecoratorInfo(parts);
        builder.buildTriangle(vectors, decorInfo.color, decorInfo.text);
    }

    private void parseConvexPolygon(String line) {
        String[] parts = line.split(", ");
        String vectorString = parts[0].split(" ", 2)[1].trim();
        List<TwoDimensionalVector> vectors = parseVectors(vectorString);
        
        DecoratorInfo decorInfo = parseDecoratorInfo(parts);
        builder.buildConvexPolygon(vectors, decorInfo.color, decorInfo.text);
    }

    private void parseCompoundShape(Scanner sc, String line) {
        String[] parts;
        boolean hasOpenBrace = line.contains("{");
        String remainingLine = "";
        
        if (hasOpenBrace) {
            String[] splitAtBrace = line.split("\\{", 2);
            parts = splitAtBrace[0].trim().split(", ");
            remainingLine = splitAtBrace.length > 1 ? splitAtBrace[1].trim() : "";
        } else {
            parts = line.split(", ");
        }
    
        DecoratorInfo decorInfo = parseDecoratorInfo(parts);
        builder.beginBuildCompoundShape(decorInfo.color, decorInfo.text);
    
        if (!hasOpenBrace) {
            if (!sc.hasNextLine()) {
                throw new IllegalArgumentException("Expected token '{'");
            }
            String nextLine = sc.nextLine().trim();
            if (!nextLine.equals("{")) {
                throw new IllegalArgumentException("Expected token '{'");
            }
        }
    
        if (!remainingLine.isEmpty()) {
            if (remainingLine.equals("}")) {
                builder.endBuildCompoundShape();
                return;
            } else if (!remainingLine.isEmpty()) {
                parseLine(sc, remainingLine);
            }
        }
    
        boolean foundClosingBrace = false;
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine().trim();
            if (currentLine.isEmpty()) {
                continue;
            }
            
            if (currentLine.equals("}")) {
                foundClosingBrace = true;
                break;
            }
            parseLine(sc, currentLine);
        }
    
        if (!foundClosingBrace) {
            throw new IllegalArgumentException("Expected token '}'");
        }
        
        builder.endBuildCompoundShape();
    }

    private List<TwoDimensionalVector> parseVectors(String vectorString) {
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        String[] rawVectors = vectorString.trim().split("\\s+");
        
        for (String rawVector : rawVectors) {
            validateVectorFormat(rawVector);
            vectors.add(convertToVector(rawVector));
        }
        
        return vectors;
    }

    private static class DecoratorInfo {
        String color;
        String text;
        
        DecoratorInfo() {
            this.color = null;
            this.text = null;
        }
    }

    private void validateVectorFormat(String rawVector) {
        if (!rawVector.startsWith("[")) {
            throw new IllegalArgumentException("Expected token '['");
        }
        if (!rawVector.contains(",")) {
            throw new IllegalArgumentException("Expected token ','");
        }
        if (!rawVector.endsWith("]")) {
            throw new IllegalArgumentException("Expected token ']'");
        }
    }

    private TwoDimensionalVector convertToVector(String rawVector) {
        String vectorContent = rawVector.substring(1, rawVector.length() - 1);
        String[] coordinates = vectorContent.split(",");
        int x = Integer.parseInt(coordinates[0].trim());
        int y = Integer.parseInt(coordinates[1].trim());

        return new TwoDimensionalVector(x, y);
    }

    private DecoratorInfo parseDecoratorInfo(String[] parts) {
        DecoratorInfo info = new DecoratorInfo();
        
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();
            if (part.startsWith("color=")) {
                String color = part.substring(6).trim().replace("{", "").trim();
                info.color = color;
            } else if (part.startsWith("text=")) {
                String text = part.substring(5).trim().replace("{", "").trim();
                info.text = text;
            }
        }
        
        return info;
    }

    private enum ShapeType {
        CIRCLE, RECTANGLE, TRIANGLE, CONVEX_POLYGON, COMPOUND_SHAPE
    }
}