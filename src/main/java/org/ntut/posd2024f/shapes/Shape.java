package org.ntut.posd2024f.shapes;

public interface Shape {
    public double area();
    public double perimeter();
    public String toString();
    //this method was overridden for my ArrayList assertion
    public boolean equals(Object o);
}
