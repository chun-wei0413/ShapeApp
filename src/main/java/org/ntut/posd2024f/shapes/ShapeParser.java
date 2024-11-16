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
            shapes = builder.getResult();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    public List<Shape> getResult() {
        return shapes;
    }

    private void parseCircle(String line) {
        String[] parts = line.split(",", 2);
        double radius = Double.parseDouble(parts[0].split(" ")[1].trim());

        String color = null;
        String text = null;

        if (parts.length > 1) {
            String additionalInfo = parts[1].trim();
            if (additionalInfo.startsWith("color=")) {
                color = additionalInfo.split("=")[1].trim();
            }
            if (additionalInfo.startsWith("text=")) {
                text = additionalInfo.split("=")[1].trim();
            }
        }

        builder.buildCircle(radius, color, text);
    }

    private void parseRectangle(String line) {
        String[] parts = line.split(",", 2);
        String[] dimensions = parts[0].split(" ");
        double length = Double.parseDouble(dimensions[1].trim());
        double width = Double.parseDouble(dimensions[2].trim());

        String color = null;
        String text = null;

        if (parts.length > 1) {
            String additionalInfo = parts[1].trim();
            if (additionalInfo.startsWith("color=")) {
                color = additionalInfo.split("=")[1].trim();
            }
            if (additionalInfo.startsWith("text=")) {
                text = additionalInfo.split("=")[1].trim();
            }
        }

        builder.buildRectangle(length, width, color, text);
    }

    private void parseTriangle(String line) {
        String[] parts = line.split(",", 2);
        String vectorString = parts[0].split(" ", 2)[1].trim();
        List<TwoDimensionalVector> vectors = parseVectors(vectorString);

        String color = null;
        String text = null;

        if (parts.length > 1) {
            String additionalInfo = parts[1].trim();
            if (additionalInfo.startsWith("color=")) {
                color = additionalInfo.split("=")[1].trim();
            }
            if (additionalInfo.startsWith("text=")) {
                text = additionalInfo.split("=")[1].trim();
            }
        }

        builder.buildTriangle(vectors, color, text);
    }

    private void parseConvexPolygon(String line) {
        String[] parts = line.split(",", 2);
        String vectorString = parts[0].split(" ", 2)[1].trim();
        List<TwoDimensionalVector> vectors = parseVectors(vectorString);

        String color = null;
        String text = null;

        if (parts.length > 1) {
            String additionalInfo = parts[1].trim();
            if (additionalInfo.startsWith("color=")) {
                color = additionalInfo.split("=")[1].trim();
            }
            if (additionalInfo.startsWith("text=")) {
                text = additionalInfo.split("=")[1].trim();
            }
        }
        builder.buildConvexPolygon(vectors, color, text);
    }

    private void parseCompoundShape(Scanner sc, String line) {

        String color = null;
        String text = null;

        String[] parts = line.split(",", 2);
        if (parts.length > 1) {
            String additionalInfo = parts[1].trim();
            if (additionalInfo.startsWith("color=")) {
                color = additionalInfo.split("=")[1].trim();
            }
            if (additionalInfo.startsWith("text=")) {
                text = additionalInfo.split("=")[1].trim();
            }
        }

        if (!sc.hasNextLine() || !sc.nextLine().trim().equals("{")) {
            throw new IllegalArgumentException("Expected token '{'");
        }

        builder.beginBuildCompoundShape(color, text);

        while (sc.hasNextLine()) {
            String lineInside = sc.nextLine().trim();

            if (lineInside.equals("}")) {
                break;
            }

            if (lineInside.startsWith("Circle")) {
                parseCircle(lineInside);
            } else if (lineInside.startsWith("Rectangle")) {
                parseRectangle(lineInside);
            } else if (lineInside.startsWith("Triangle")) {
                parseTriangle(lineInside);
            } else if (lineInside.startsWith("ConvexPolygon")) {
                parseConvexPolygon(lineInside);
            } else if (lineInside.startsWith("CompoundShape")) {
                parseCompoundShape(sc, lineInside);
            }
        }

        builder.endBuildCompoundShape();
    }

    private List<TwoDimensionalVector> parseVectors(String vectorString) {
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        String[] rawVectors = vectorString.split(" ");

        for (String rawVector : rawVectors) {
            if (!rawVector.startsWith("[") || !rawVector.contains(",") || !rawVector.endsWith("]")) {
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

            rawVector = rawVector.substring(1, rawVector.length() - 1);  // Remove brackets
            String[] coordinates = rawVector.split(",");
            int x = Integer.parseInt(coordinates[0].trim());
            int y = Integer.parseInt(coordinates[1].trim());
            vectors.add(new TwoDimensionalVector(x, y));
        }

        return vectors;
    }
}
