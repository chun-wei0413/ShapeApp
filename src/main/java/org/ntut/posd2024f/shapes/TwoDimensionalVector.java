package org.ntut.posd2024f.shapes;

public class TwoDimensionalVector {
    private int x,y;

    public TwoDimensionalVector(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }


    public double length() {
        return Math.sqrt( x * x + y * y);
    }

    public int dot(TwoDimensionalVector v) {
        return (int) (this.x * v.x + this.y * v.y );
    }

    public int cross(TwoDimensionalVector v) {
        return (int) (this.x * v.y - this.y * v.x );
    }

    public TwoDimensionalVector subtract(TwoDimensionalVector v) {
        return new TwoDimensionalVector(this.x - v.x, this.y - v.y);
    }

}
