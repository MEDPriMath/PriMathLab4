package ru.itmo.primath.generator;

import ru.itmo.primath.matrix.ArrayMatrix;

import java.util.Random;

public class DiagonallyDominantArrayMatrixGenerator implements SymmetryMatrixGenerator<ArrayMatrix> {

    private final int k;

    /**
     * @param k диагональное преобладание
     */
    public DiagonallyDominantArrayMatrixGenerator(int k) {
        this.k = k;
    }

    @Override
    public ArrayMatrix generate(int size) {
        ArrayMatrix matrix = new ArrayMatrix(size);
        Random r = new Random();
        for (int row = 0; row < size; ++row) {
            for (int column = row + 1; column < size; ++column) {
                double value = r.nextInt(-4, 1);
                matrix.set(row, column, value);
                matrix.set(column, row, value);
            }
        }
        for (int row = 0; row < size; ++row) {
            double temp = 0;
            for (int column = 0; column < size; ++column) {
                if (column == row)
                    continue;
                temp += matrix.get(row, column) + matrix.get(column, row);
            }
            matrix.set(row, row, -temp + Math.pow(10, -k));
        }

        return matrix;
    }
}
