package org.ntut.posd2024f.shapes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                if (line.startsWith("Circle")) {
                    parseCircle(line);
                } else if (line.startsWith("Rectangle")) {
                    parseRectangle(line);
                } else if (line.startsWith("Triangle")) {
                    parseTriangle(line);
                } else if (line.startsWith("ConvexPolygon")) {
                    parseConvexPolygon(line);
                } else if (line.startsWith("CompoundShape")) {
                    parseCompoundShape(scanner, line);
                } else {
                    //debug用
                    throw new IllegalArgumentException("Unknown shape type");
                }
            }
            shapes = builder.getResult();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    public List<Shape> getResult() {
        return shapes;
    }

    private void parseCircle(String line) {
        Pattern pattern = Pattern.compile("Circle (\\d+\\.\\d+)(, color=(\\w+))?(, text=(.*))?");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            double radius = Double.parseDouble(matcher.group(1));
            String color = matcher.group(3);
            String text = matcher.group(5);
            builder.buildCircle(radius, color, text);
        } else {
            throw new IllegalArgumentException("Invalid Circle format");
        }
    }

    private void parseRectangle(String line) {
        Pattern pattern = Pattern.compile("Rectangle (\\d+\\.\\d+) (\\d+\\.\\d+)(, color=(\\w+))?(, text=(.*))?");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            double length = Double.parseDouble(matcher.group(1));
            double width = Double.parseDouble(matcher.group(2));
            String color = matcher.group(4);
            String text = matcher.group(6);
            builder.buildRectangle(length, width, color, text);
        } else {
            throw new IllegalArgumentException("Invalid Rectangle format");
        }
    }

    private void parseTriangle(String line) {
        Pattern pattern = Pattern.compile("Triangle ((\\[\\d+,\\d+\\] ?){3})(, color=(\\w+))?(, text=(.*))?");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            List<TwoDimensionalVector> vectors = parseVectors(matcher.group(1));
            String color = matcher.group(4);
            String text = matcher.group(6);
            builder.buildTriangle(vectors, color, text);
        } else {
            throw new IllegalArgumentException("Invalid Triangle format");
        }
    }

    private void parseConvexPolygon(String line) {
        Pattern pattern = Pattern.compile("ConvexPolygon ((\\[\\d+,\\d+\\] ?)+)(, color=(\\w+))?(, text=(.*))?");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String vectorsPart = matcher.group(1);
            String color = matcher.group(4);
            String text = matcher.group(6);
    
            try {
                List<TwoDimensionalVector> vectors = parseVectors(vectorsPart);
                builder.buildConvexPolygon(vectors, color, text);
            } catch (IllegalArgumentException e) {
                throw e;
            }
        } else {
            throw new IllegalArgumentException("Invalid ConvexPolygon format");
        }
    }

    private void parseCompoundShape(Scanner scanner, String line) {
        Pattern pattern = Pattern.compile("CompoundShape(, color=(\\w+))?(, text=(.*))? \\{");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String color = matcher.group(2); 
            String text = matcher.group(4);  
            builder.beginBuildCompoundShape(color, text);
    
            boolean foundRightBrace = false;
    
            while (scanner.hasNextLine()) {
                String subLine = scanner.nextLine().trim();
                if (subLine.equals("}")) {
                    foundRightBrace = true;
                    builder.endBuildCompoundShape();
                    break;
                } else if (subLine.isEmpty()) {
                    continue;
                } else if (subLine.startsWith("CompoundShape")) {
                    parseCompoundShape(scanner, subLine);
                } else if (subLine.startsWith("Circle")) {
                    parseCircle(subLine);
                } else if (subLine.startsWith("Rectangle")) {
                    parseRectangle(subLine);
                } else if (subLine.startsWith("Triangle")) {
                    parseTriangle(subLine);
                } else if (subLine.startsWith("ConvexPolygon")) {
                    parseConvexPolygon(subLine);
                } else {
                    throw new IllegalArgumentException("Unknown shape type in compound shape");
                }
            }
    
            if (!foundRightBrace) {
                throw new IllegalArgumentException("Expected token '}'");
            }
        } else {
            throw new IllegalArgumentException("Expected token '{'");
        }
    }
    

    private List<TwoDimensionalVector> parseVectors(String vectorString) {
        List<TwoDimensionalVector> vectors = new ArrayList<>();

        String[] rawVectors = vectorString.trim().split(" ");
        
        for (String rawVector : rawVectors) {
            if (!rawVector.startsWith("[")) {
                throw new IllegalArgumentException("Expected token '['");
            }
            if (!rawVector.contains(",")) {
                throw new IllegalArgumentException("Expected token ','");
            }
            if (!rawVector.endsWith("]")) {
                throw new IllegalArgumentException("Expected token ']'");
            }
    
            Pattern vectorPattern = Pattern.compile("\\[(\\d+),(\\d+)\\]");
            Matcher matcher = vectorPattern.matcher(rawVector);
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                vectors.add(new TwoDimensionalVector(x, y));
            } else {
                throw new IllegalArgumentException("Invalid vector format");
            }
        }
    
        if (vectors.isEmpty()) {
            throw new IllegalArgumentException("Invalid vector format");
        }
    
        return vectors;
    }
    
}
