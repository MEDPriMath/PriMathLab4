package ru.itmo.primath.algo;

import ru.itmo.primath.matrix.Matrix;
import ru.itmo.primath.vector.Vector;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Реализация решения задачи нахождения собственных значений симметричной матрицы
 * методом вращения Якоби.
 */
public class JacobiRotation {

    private record MatrixJacobiStat(double nonDiagonalSum, int maxRow, int maxColumn) {
    }

    public static <TMatrix extends Matrix<TMatrix>> JacobiRotationResult<TMatrix> calculate(TMatrix matrix, double epsilon) {
        MatrixJacobiStat stat;

        AtomicReference<TMatrix> eigenVectors = new AtomicReference<>(matrix.createIdentity(matrix.rows));

        while ((stat = analyzeMatrix(matrix)).nonDiagonalSum > epsilon) {
            var phi = phiForRotation(matrix, stat);
            matrix = jakobiRotate(matrix, stat.maxRow, stat.maxColumn, phi, eigenVectors);
        }

        Vector eigenValues = new Vector(matrix.rows);
        for (int i = 0; i < matrix.rows; i++) {
            eigenValues.set(i, matrix.get(i, i));
        }

        return new JacobiRotationResult<>(eigenVectors.get(), eigenValues);
    }

    /**
     * Проходимся по матрице:
     * считаем корень суммы квадратов недиагональных элементов - нужен для оценки состояния матрицы
     * находим самый большой по модулю недиагональный элемент - для дальнейшего удаления
     */
    private static MatrixJacobiStat analyzeMatrix(Matrix<?> matrix) {
        double maxValue = 0;
        int maxRow = 0;
        int maxColumn = 0;
        double sum = 0;
        for (int row = 0; row < matrix.rows; row++) {
            for (int column = row + 1; column < matrix.columns; column++) {
                var realValue = matrix.get(row, column);
                var value = abs(realValue);

                if (maxValue < value) {
                    maxValue = value;
                    maxRow = row;
                    maxColumn = column;
                }

                sum += Math.pow(realValue, 2);
            }
        }

        return new MatrixJacobiStat(
                Math.sqrt(sum),
                maxRow,
                maxColumn);
    }

    /**
     * Противная формула вычисления угла, на который нужно повернуть матрицу
     */
    private static double phiForRotation(Matrix<?> m, MatrixJacobiStat stat) {
        var iiEl = m.get(stat.maxRow, stat.maxRow);
        var jjEl = m.get(stat.maxColumn, stat.maxColumn);

        if (iiEl == jjEl)
            return PI / 4;

        return 0.5d *
                Math.atan(
                        2 * m.get(stat.maxRow, stat.maxColumn) / (iiEl - jjEl)
                );
    }

    /**
     * Осуществляем вращение матрицы вокруг определённой точки туда и обратно
     *
     * @param m      исходная матрица
     * @param row    строка с наибольшим недиагнональныым элементом
     * @param column столбец с наибольшим недиагнональныым элементом
     * @param phi    угол поворота
     * @return возвращает новый объект - полученную матрицу, где не будет того самого большого элемента
     */
    private static <TMatrix extends Matrix<TMatrix>> TMatrix jakobiRotate(TMatrix m, int row, int column, double phi,
                                                                          AtomicReference<TMatrix> eigenVectors) {
        var u = m.createIdentity(m.rows);
        u.set(row, row, cos(phi));
        u.set(row, column, -sin(phi));
        u.set(column, row, sin(phi));
        u.set(column, column, cos(phi));

        eigenVectors.set(eigenVectors.get().multiply(u));

        var tmp = m.multiply(u);

        u.set(row, column, sin(phi));
        u.set(column, row, -sin(phi));

        return u.multiply(tmp);
    }
}
