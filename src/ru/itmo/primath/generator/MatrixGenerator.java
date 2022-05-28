package ru.itmo.primath.generator;

import ru.itmo.primath.matrix.Matrix;

public interface MatrixGenerator<T extends Matrix<T>> {
    T generate(int size);
}
