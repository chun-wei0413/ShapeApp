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
        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }
        this.file = file;
        this.builder = new ShapeBuilder();
        this.shapes = new ArrayList<>();
    }

    public void parse() {
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                parseLine(sc, line);
            }
            shapes = builder.getResult();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    public List<Shape> getResult() {
        return shapes;
    }

    private void parseLine(Scanner sc, String line) {
        if (line.startsWith("Circle")) {
            parseCircle(line);
        } else if (line.startsWith("Rectangle")) {
            parseRectangle(line);
        } else if (line.startsWith("Triangle")) {
            parseTriangle(line);
        } else if (line.startsWith("ConvexPolygon")) {
            parseConvexPolygon(line);
        } else if (line.startsWith("CompoundShape")) {
            parseCompoundShape(sc, line);
        } else {
            throw new IllegalArgumentException("Unknown shape type");
        }
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
        String[] parts = line.split(", ");
        DecoratorInfo decorInfo = parseDecoratorInfo(parts);

        String nextLine = sc.hasNextLine() ? sc.nextLine().trim() : "";
        if (!nextLine.equals("{")) {
            throw new IllegalArgumentException("Expected token '{'");
        }

        builder.beginBuildCompoundShape(decorInfo.color, decorInfo.text);

        boolean foundClosingBrace = false;
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine().trim();
            if (currentLine.equals("}")) {
                foundClosingBrace = true;
                break;
            }
            if (!currentLine.isEmpty()) {
                parseLine(sc, currentLine);
            }
        }

        if (!foundClosingBrace) {
            throw new IllegalArgumentException("Expected token '}'");
        }

        builder.endBuildCompoundShape();
    }

    private List<TwoDimensionalVector> parseVectors(String vectorString) {
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        String[] rawVectors = vectorString.split("\\] \\[");
        
        for (String rawVector : rawVectors) {
            rawVector = rawVector.replaceAll("\\[|\\]", "").trim();
            
            if (!vectorString.startsWith("[")) {
                throw new IllegalArgumentException("Expected token '['");
            }
            if (!rawVector.contains(",")) {
                throw new IllegalArgumentException("Expected token ','");
            }
            if (!vectorString.endsWith("]")) {
                throw new IllegalArgumentException("Expected token ']'");
            }
            
            String[] coordinates = rawVector.split(",");
            int x = Integer.parseInt(coordinates[0].trim());
            int y = Integer.parseInt(coordinates[1].trim());
            vectors.add(new TwoDimensionalVector(x, y));
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

    private DecoratorInfo parseDecoratorInfo(String[] parts) {
        DecoratorInfo info = new DecoratorInfo();
        
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i].trim();
            if (part.startsWith("color=")) {
                info.color = part.substring(6).trim();
            } else if (part.startsWith("text=")) {
                info.text = part.substring(5).trim();
            }
        }
        
        return info;
    }
}