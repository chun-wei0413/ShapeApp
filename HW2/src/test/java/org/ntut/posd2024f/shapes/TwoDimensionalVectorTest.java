package org.ntut.posd2024f.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoDimensionalVectorTest {
    @Test
    public void test_twoDimensionalVector(){
        TwoDimensionalVector twoD = new TwoDimensionalVector(4, 5);
        assertEquals(4, twoD.getX(), 0.001);
        assertEquals(5, twoD.getY(), 0.001);
    }

    @Test
    public void test_twoDimensionalVector_getX(){
        TwoDimensionalVector twoD = new TwoDimensionalVector(4, 5);
        assertEquals(4, twoD.getX(), 0.001);
    }

    @Test
    public void test_twoDimensionalVector_getY(){
        TwoDimensionalVector twoD = new TwoDimensionalVector(4, 5);
        assertEquals(5, twoD.getY(), 0.001);
    }

    @Test
    public void test_twoDimensionalVector_length(){
        TwoDimensionalVector twoD = new TwoDimensionalVector(3, 5);
        assertEquals(5.830, twoD.length(), 0.001);
    }

    @Test
    public void test_twoDimensionalVector_dot(){
        TwoDimensionalVector twoDA = new TwoDimensionalVector(3, 5);
        TwoDimensionalVector twoDB = new TwoDimensionalVector(6, 5);
        
        assertEquals(43, twoDA.dot(twoDB));
    }

    @Test
    public void test_twoDimensionalVector_cross(){
        TwoDimensionalVector twoDA = new TwoDimensionalVector(3, 5);
        TwoDimensionalVector twoDB = new TwoDimensionalVector(6, 5);
        
        assertEquals(-15, twoDA.cross(twoDB));
    }

    @Test
    public void test_twoDimensionalVector_substract(){
        TwoDimensionalVector twoDA = new TwoDimensionalVector(3, 5);
        TwoDimensionalVector twoDB = new TwoDimensionalVector(6, 5);
        TwoDimensionalVector twoDC = twoDB.subtract(twoDA);

        assertEquals(3, twoDC.getX(), 0.001);
        assertEquals(0, twoDC.getY(), 0.001);
    }

}
