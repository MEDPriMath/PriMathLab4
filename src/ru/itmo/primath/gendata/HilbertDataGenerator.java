package ru.itmo.primath.gendata;

public class HilbertDataGenerator implements DataGenerator {

    @Override
    public double[][] generate(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }

        double[][] data = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                data[row][column] = 1d / (row + column + 1);
            }
        }

        return data;
    }
}
