package org.ntut.posd2024f.shapes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.text.spi.NumberFormatProvider;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InputOutput {
    
    public ArrayList<Shape> handleInput(String inputFile) throws FileNotFoundException, Exception{
        ArrayList<Shape> shapes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;
        while((line = br.readLine()) != null){
            Shape shape = constructShape(line);
            if(shape != null){
                shapes.add(shape);
            }
        }
        return shapes;
    }

    public Shape constructShape(String line) throws NumberFormatException, Exception {
        String[] str = line.split(" ");
        
        switch (str[0]) {
            case "Circle":
                return new Circle(Double.parseDouble(str[1]));
            case "Rectangle":
                return new Rectangle(Double.parseDouble(str[1]), Double.parseDouble(str[2]));
            case "Triangle":
                return new Triangle(Double.parseDouble(str[1]),Double.parseDouble(str[2]),Double.parseDouble(str[3]));
            default:
                return null;
        }
    }

    public ArrayList<Shape> handleSort(ArrayList<Shape> shapes, String condition, String order){
        Comparator<Shape> comparator = null;
        boolean conditionHandler = false;
        boolean orderHandler = false;

        if(condition.equals("area")){
            conditionHandler = true;
            if(order.equals("inc")){
                orderHandler = true;
                comparator = Sort.BY_AREA_ASCENDING;
            }
            else if(order.equals("dec")){
                orderHandler = true;
                comparator = Sort.BY_AREA_DESCENDING;
            }
        } else if(condition.equals("perimeter")){
            conditionHandler = true;
            if(order.equals("inc")){
                orderHandler = true;
                comparator = Sort.BY_PERIMETER_ASCENDING;
            }
            else if(order.equals("dec")){
                orderHandler = true;
                comparator = Sort.BY_PERIMETER_ASCENDING;
            }
        }

        if(conditionHandler && orderHandler) {
            shapes.sort(comparator);
        }
        
        return shapes;
    }

    public void handleOutput(ArrayList<Shape> shapes, String outputFileName) throws Exception{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Shape shape : shapes) {
                bw.write(shape.toString());
                bw.newLine();
            }
        }
    }
}
