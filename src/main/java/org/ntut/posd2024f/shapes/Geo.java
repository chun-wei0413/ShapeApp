package org.ntut.posd2024f.shapes;

import java.util.ArrayList;

public final class Geo {
    /**
     * @param args The arguments of the program.
     * @throws Exception
     * @throws NumberFormatException
     */
    public static void main(String[] args) throws NumberFormatException, Exception {
        /*
        我測試用的參數
        String inputFileName = "test_data/input.txt";   // args[0]
        String outputFileName = "test_data/output.txt"; // args[1]
        String sortBy = "area";               // args[2]
        String order = "dec";                 // args[3]
        */
        InputOutput io = new InputOutput();
        ArrayList<Shape> shapes = io.handleInput(args[0]);
        shapes = io.handleSort(shapes, args[2], args[3]);
        io.handleOutput(shapes, args[1]);
    }
}
