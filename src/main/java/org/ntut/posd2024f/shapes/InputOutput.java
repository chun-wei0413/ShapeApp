package org.ntut.posd2024f.shapes;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class InputOutput {
    public InputOutput() {
    }

    public ArrayList<Shape> handleInput(String inputFileName) throws NumberFormatException, Exception {
        ArrayList<Shape> shapes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                    Shape shape = constructShape(line);
                    if (shape != null) {
                        shapes.add(shape);
                    }
            }
        }
        return shapes;
    }

    public ArrayList<Shape> handleSort(ArrayList<Shape> shapes, String compare, String order) {
        boolean conditonHandler = false;
        boolean orderHandler = false;
        Comparator<Shape> comparator = null;
        if (compare.equals("area")) {
            if(order.equals("inc")){
                comparator = Sort.BY_AREA_ASCENDING;
                orderHandler =true;
            }
            else if(order.equals("dec")){
                comparator = Sort.BY_AREA_DESCENDING;
                orderHandler =true;
            }
            conditonHandler = true;
        } else if (compare.equals("perimeter")) {
            if(order.equals("inc")){
                comparator = Sort.BY_PERIMETER_ASCENDING;
                orderHandler =true;
            }
            else if(order.equals("dec")){
                comparator = Sort.BY_PERIMETER_DESCENDING;
                orderHandler =true;
            }
            conditonHandler = true;
        }
        if(conditonHandler && orderHandler){
            shapes.sort(comparator);
        }
        return shapes;
    }

    public void handleOutput(ArrayList<Shape> shapes, String outputFileName) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Shape shape : shapes) {
                bw.write(shape.toString());
                bw.newLine();
            }
        }
    }

    private Shape constructShape(String str) throws NumberFormatException, Exception {
        String[] parts = str.split(" ");
        switch (parts[0]) {
            case "Circle":
                return new Circle(Double.parseDouble(parts[1]));
            case "Rectangle":
                return new Rectangle(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
            case "Triangle":
                return new Triangle(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
            default:
                return null;
        }
    }
}
