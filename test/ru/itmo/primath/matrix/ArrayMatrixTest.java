package ru.itmo.primath.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayMatrixTest {

    @Test
    public void equalsTest() {
        var a = new ArrayMatrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        var b = new ArrayMatrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Assertions.assertNotSame(a, b);
        Assertions.assertEquals(a, b);
    }

    @Test
    public void multiplyTest() {
        var a = new ArrayMatrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        var b = new ArrayMatrix(new double[][]{
                {2, 3, 4},
                {5, 6, 7},
                {8, 9, 10}});

        var c = new ArrayMatrix(new double[][]{
                {36, 42, 48},
                {81, 96, 111},
                {126, 150, 174}});

        Assertions.assertEquals(c, a.multiply(b));
    }

}
