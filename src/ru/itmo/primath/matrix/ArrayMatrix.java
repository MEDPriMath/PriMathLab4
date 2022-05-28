package ru.itmo.primath.matrix;

import java.util.Arrays;

public class ArrayMatrix extends Matrix<ArrayMatrix> {
    double[][] data;

    public ArrayMatrix(int size) {
        this(size, size);
    }

    public ArrayMatrix(int rows, int columns) {
        super(rows, columns);
        data = new double[rows][columns];
    }

    @Override
    public ArrayMatrix createIdentity(int size) {
        var data = new double[size][size];
        for (int i = 0; i < size; i++) {
            data[i][i] = 1;
        }

        return new ArrayMatrix(data);
    }

    public double[][] getData() {
        return data;
    }

    @Override
    public double get(int row, int column) {
        return data[row][column];
    }

    @Override
    public void set(int row, int column, double value) {
        data[row][column] = value;
    }

    @Override
    public ArrayMatrix sum(ArrayMatrix other) {
        if (rows != other.rows || columns != other.columns)
            throw new IllegalArgumentException();

        double[][] resultData = new double[rows][columns];

        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                resultData[row][column] = data[row][column] + other.data[row][column];
            }
        }

        return new ArrayMatrix(resultData);
    }

    @Override
    public ArrayMatrix multiply(ArrayMatrix other) {
        if (columns != other.rows)
            throw new IllegalArgumentException();

        double[][] resultData = new double[rows][other.columns];

        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < other.columns; ++column) {
                double value = 0d;
                for (int k = 0; k < columns; ++k) {
                    value += get(row, k) * other.get(k, column);
                }
                resultData[row][column] = value;
            }
        }

        return new ArrayMatrix(resultData);
    }

    @Override
    public ArrayMatrix multiply(double scalar) {
        double[][] resultData = new double[rows][columns];

        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                resultData[row][column] = data[row][column] * scalar;
            }
        }

        return new ArrayMatrix(resultData);
    }

    @Override
    public ArrayMatrix clone() {
        double[][] newData = new double[rows][columns];
        for (int i = 0; i < rows; ++i){
            System.arraycopy(data[i], 0, newData[i], 0, columns);
        }

        return new ArrayMatrix(newData);
    }

    public ArrayMatrix(double[][] data) {
        super(data.length, data[0].length);
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayMatrix that = (ArrayMatrix) o;
        return Arrays.deepEquals(data, that.data);
    }
}
