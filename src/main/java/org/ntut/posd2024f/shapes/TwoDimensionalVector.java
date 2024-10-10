package org.ntut.posd2024f.shapes;

public class TwoDimensionalVector {
    private double x;
    private double y;

    public TwoDimensionalVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    // Calculate the length of the vector
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
 
    // Calculate the dot product of two vectors
    public int dot(TwoDimensionalVector other) {
        return (int) (this.x * other.x + this.y * other.y);
    }

    // Calculate the cross product of two vectors
    public int cross(TwoDimensionalVector other) {
        return (int) (this.x * other.y - this.y * other.x);
    }

    // Subtract two vectors and return a new vector
    public TwoDimensionalVector subtract(TwoDimensionalVector other) {
        return new TwoDimensionalVector(this.x - other.x, this.y - other.y);
    }

    @Override
    public String toString() {
        return "[" + (int)x + ", " + (int)y + "]";
    }
}
