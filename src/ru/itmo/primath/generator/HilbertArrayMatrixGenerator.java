package ru.itmo.primath.generator;

import ru.itmo.primath.matrix.ArrayMatrix;

public class HilbertArrayMatrixGenerator implements MatrixGenerator<ArrayMatrix> {

    @Override
    public ArrayMatrix generate(int size) {
        double[][] data = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                data[row][column] = 1d / (row + column + 1);
            }
        }
        return new ArrayMatrix(data);
    }
}
