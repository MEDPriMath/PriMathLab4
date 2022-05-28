package ru.itmo.primath.generator;

import ru.itmo.primath.matrix.Matrix;

/**
 * В данной лабораторной работе требуется генерировать симметричные матрицы
 * @param <T> класс матрицы, которую создаёт генератор
 */
public interface SymmetryMatrixGenerator<T extends Matrix<T>> {
    T generate(int size);
}
