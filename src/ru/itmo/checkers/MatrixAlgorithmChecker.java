package ru.itmo.checkers;

import ru.itmo.markdown.blocks.MarkdownBlock;
import ru.itmo.primath.generator.SymmetryMatrixGenerator;
import ru.itmo.primath.matrix.Matrix;

import java.util.List;

public interface MatrixAlgorithmChecker {
    <T extends Matrix<T>> List<MarkdownBlock> check(List<SymmetryMatrixGenerator<T>> matrixGenerators, double precision, int matrixSize);
}
