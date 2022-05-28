package ru.itmo.primath.matrix;

public abstract class Matrix<T extends Matrix<T>> {

    public final int rows;
    public final int columns;

    protected Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public abstract T createIdentity(int size);

    public abstract double get(int row, int column);

    public abstract void set(int row, int column, double value);

    public abstract T multiply(T other);

    public void print() {
        print(2);
    }

    public void print(int precision) {
        int largest = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                var str = String.format("%." + precision + "f", get(row, column));
                if (str.length() > largest)
                    largest = str.length();
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.printf("%" + largest + "." + precision + "f ", get(row, column));
            }
            System.out.println();
        }
    }

    public boolean equals(T other, double epsilon) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (Math.abs(get(row, column) - other.get(row, column)) > epsilon) {
                    return false;
                }
            }
        }
        return true;
    }

//    public void print() {
//        print(1);
//    }
//
//    public void print(int precision) {
//        int largest = 0;
//        for (int i = 0; i < rows; ++i) {
//            for (int j = 0; j < columns; ++j) {
//                var str = String.format("%." + precision + "f", this.get(i, j));
//                if (str.length() > largest)
//                    largest = str.length();
//            }
//        }
//        for (int i = 0; i < rows; ++i) {
//            for (int j = 0; j < columns; ++j) {
//                System.out.printf("%" + largest + "." + precision + "f ", this.get(i, j));
//            }
//            System.out.println();
//        }
//    }
    }
