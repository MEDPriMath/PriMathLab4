package ru.itmo.primath.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;
import static ru.itmo.utils.BinarySearch.binarySearch;

public class CSRMatrix extends Matrix<CSRMatrix> {

    private static final double epsilon = 1E-12;

    private final List<Double> data;
    private final List<Integer> indices;
    private final int[] elementsBeforeRow;

    public CSRMatrix(int size) {
        this(size, size);
    }

    public CSRMatrix(int rows, int columns) {
        super(rows, columns);
        data = new ArrayList<>();
        indices = new ArrayList<>();
        elementsBeforeRow = new int[rows + 1];
    }

    public CSRMatrix(double[][] data) {
        this(data.length, data[0].length);
        int counter = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (abs(data[row][column]) > epsilon) {
                    this.data.add(data[row][column]);
                    indices.add(column);
                    counter++;
                }
            }
            elementsBeforeRow[row + 1] = counter;
        }
    }

    private CSRMatrix(int rows, int columns, List<Double> data, List<Integer> indices, int[] elementsBeforeRow) {
        super(rows, columns);
        this.data = data;
        this.indices = indices;
        this.elementsBeforeRow = elementsBeforeRow;
    }

    @Override
    public CSRMatrix createIdentity(int size) {
        var data = new ArrayList<Double>(size);
        var indices = new ArrayList<Integer>(size);
        var elementsBeforeRow = new int[size + 1];

        for (int i = 0; i < size; i++) {
            data.add(1d);
            indices.add(i);
            elementsBeforeRow[i + 1] = i + 1;
        }

        return new CSRMatrix(size, size, data, indices, elementsBeforeRow);
    }

    @Override
    public double get(int row, int column) {
        int firstIndex = elementsBeforeRow[row];
        int lastIndex = elementsBeforeRow[row + 1];

        int index = binarySearch(indices::get, column, firstIndex, lastIndex);

        if (index == -1)
            return 0;
        else
            return data.get(index);
    }

    @Override
    public void set(int row, int column, double value) {
        int firstIndex = elementsBeforeRow[row];
        int lastIndex = elementsBeforeRow[row + 1];

        if (indices.get(lastIndex - 1) < column) { // Last element in row stay before new value
            if (abs(value) < epsilon)
                return;
            data.add(lastIndex, value);
            indices.add(lastIndex, column);
            for (int k = row + 1; k < elementsBeforeRow.length; k++) {
                elementsBeforeRow[k]++;
            }

            return;
        }

        for (int i = firstIndex; i < lastIndex; i++) {
            if (indices.get(i) == column) { // There is another value
                if (abs(value) < epsilon) {
                    data.remove(i);
                    indices.remove(i);
                    for (int k = row + 1; k < elementsBeforeRow.length; k++) {
                        elementsBeforeRow[k]--;
                    }
                } else {
                    data.set(i, value);
                }
                break;
            }
            if (indices.get(i) > column) {  // Skipped column, insert end go out
                if (abs(value) < epsilon)
                    break;
                data.add(i, value);
                indices.add(i, column);
                for (int k = row + 1; k < elementsBeforeRow.length; k++) {
                    elementsBeforeRow[k]++;
                }
                break;
            }
        }

        /*ArrayMatrix matrix = new ArrayMatrix<>(this);
        matrix.set(elem, row, col);
        List<Double> data = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        List<Integer> indPtr = new ArrayList<>();
        indPtr.add(1);
        for (int i = 0; i < matrix.getMatrixDimensionM(); ++i) {
            int notNull = 0;
            for (int j = 0; j < matrix.getMatrixDimensionN(); ++j) {
                double element = matrix.get(i, j);
                if (!element.equals(zero)) {
                    data.add(element);
                    indices.add(j);
                    ++notNull;
                }
            }
            indPtr.add(indPtr.get(indPtr.size() - 1) + notNull);
        }
        this.data = data;
        this.indices = indices;
        elementsBeforeRow = indPtr;
    }

    @Override
    public CSRMatrix multiply(CSRMatrix other) {
        return null;
    }

    @Override
    public void print() {

    }

//    public CSRMatrix(Matrix matrix) {
//        super(matrix.getRows(), matrix.getColumns());
//
//        this.data = new ArrayList<>();
//        this.indices = new ArrayList<>();
//        elementsBeforeRow = new ArrayList<>();
//
//        int zeros = 0;
//        indPtr.add(zeros);
//        for (int row = 0; row < matrix.getRows(); ++row) {
//            for (int col = 0; col < matrix.getColumns(); ++col) {
//                double element = matrix.get(row, col);
//                if (element != 0) {
//                    data.add(element);
//                    indices.add(col);
//                }
//            }
//            indPtr.add(zeros);
//        }
//    }
//
//    private void validateIndex(int row, int col) {
//        if (row < 0 || row >= rows)
//            throw new ArrayIndexOutOfBoundsException();
//        if (col < 0 || col >= columns)
//            throw new ArrayIndexOutOfBoundsException();
//    }
//
//    @Override
//    public void set(double elem, int row, int col) {
//        SET_CALLS++;
//        validateIndex(row, col);
//
//        int elementsBefore = elementsBeforeRow.get(row) - elementsBeforeRow.get(0);
//        int elementsInRow = countElementsInRow(row);
//
//        for (int i = elementsBefore; i < elementsBefore + elementsInRow; ++i) {
//            if (this.indices.get(i) == col) {
//                if (elem == 0d){
//                    this.data.remove(i);
//                    this.indices.remove(i);
//                    for (int k = row + 1; k < indPtr.size(); ++k){
//                        elementsBeforeRow.set(k, elementsBeforeRow.get(k) - 1);
//                    }
//                    return;
//                }
//                this.data.set(i, elem);
//                return;
//            }
//            if (this.indices.get(i) > col) {
//                if (elem == 0d)
//                    return;
//                for (int k = row + 1; k < indPtr.size(); ++k)
//                    indPtr.set(k, indPtr.get(k) + 1);
//                this.data.add(i, elem);
//                this.indices.add(i, col);
//                return;
//            }
//        }
//        if (elem == 0d)
//            return;
//        for (int k = row + 1; k < indPtr.size(); ++k)
//            indPtr.set(k, indPtr.get(k) + 1);
//        this.data.add(elementsBefore + elementsInRow, elem);
//        this.indices.add(elementsBefore + elementsInRow, col);
//
//
//
//        /*ArrayMatrix matrix = new ArrayMatrix<>(this);
//        matrix.set(elem, row, col);
//        List<Double> data = new ArrayList<>();
//        List<Integer> indices = new ArrayList<>();
//        List<Integer> indPtr = new ArrayList<>();
//        indPtr.add(1);
//        for (int i = 0; i < matrix.getMatrixDimensionM(); ++i) {
//            int notNull = 0;
//            for (int j = 0; j < matrix.getMatrixDimensionN(); ++j) {
//                double element = matrix.get(i, j);
//                if (!element.equals(zero)) {
//                    data.add(element);
//                    indices.add(j);
//                    ++notNull;
//                }
//            }
//            indPtr.add(indPtr.get(indPtr.size() - 1) + notNull);
//        }
//        this.data = data;
//        this.indices = indices;
//        elementsBeforeRow = indPtr;*/
    }

    @Override
    public CSRMatrix sum(CSRMatrix other) {
        if (rows != other.rows || columns != other.columns)
            throw new IllegalArgumentException();

        int newRows = rows;
        int newColumns = columns;

        var newData = new ArrayList<Double>();
        var newIndices = new ArrayList<Integer>();
        var newElementsBeforeRow = new int[newColumns + 1];

        int counter = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                double value = get(row, column) + other.get(row, column);
                if (abs(value) > epsilon) {
                    newData.add(value);
                    newIndices.add(column);
                    counter++;
                }
            }
            newElementsBeforeRow[row + 1] = counter;
        }

        return new CSRMatrix(newRows, newColumns, newData, newIndices, newElementsBeforeRow);
    }

    @Override
    public CSRMatrix multiply(CSRMatrix other) {
        if (columns != other.rows)
            throw new IllegalArgumentException();

        int newRows = rows;
        int newColumns = other.columns;

        var newData = new ArrayList<Double>();
        var newIndices = new ArrayList<Integer>();
        var newElementsBeforeRow = new int[newColumns + 1];

        int counter = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < other.columns; column++) {
                double value = 0d;
                for (int k = elementsBeforeRow[row]; k < elementsBeforeRow[row + 1]; k++) {
                    int currentRow = indices.get(k);
                    value += data.get(k) * other.get(currentRow, column);
                }
                if (abs(value) > epsilon) {
                    newData.add(value);
                    newIndices.add(column);
                    counter++;
                }
            }
            newElementsBeforeRow[row + 1] = counter;
        }

        return new CSRMatrix(newRows, newColumns, newData, newIndices, newElementsBeforeRow);
    }

    @Override
    public CSRMatrix multiply(double scalar) {
        if (abs(scalar) < epsilon) {
            return new CSRMatrix(rows, columns);
        }

        List<Double> newData = new ArrayList<>(data.size());
        List<Integer> newIndices = new ArrayList<>();
        int[] newElementsBeforeRow = new int[rows + 1];
        System.arraycopy(elementsBeforeRow, 0, newElementsBeforeRow, 0, rows + 1);

        for (Double value : data) {
            newData.add(value * scalar);
        }

        return new CSRMatrix(rows, columns, newData, newIndices, newElementsBeforeRow);
    }

    @Override
    public CSRMatrix clone() {
        return this.multiply(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CSRMatrix csrMatrix = (CSRMatrix) o;
        return Objects.equals(data, csrMatrix.data) && Objects.equals(indices, csrMatrix.indices) && Arrays.equals(elementsBeforeRow, csrMatrix.elementsBeforeRow);
    }
}
