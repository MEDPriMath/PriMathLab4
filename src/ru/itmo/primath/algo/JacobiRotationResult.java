package ru.itmo.primath.algo;

import ru.itmo.primath.matrix.Matrix;
import ru.itmo.primath.vector.Vector;

public record JacobiRotationResult<TMatrix extends Matrix<TMatrix>>(TMatrix eigenVectors, Vector eigenValues) {
}