package ru.itmo.primath;

import ru.itmo.primath.algo.JacobiRotation;
import ru.itmo.primath.generator.DiagonallyDominantArrayMatrixGenerator;

public class Main {

    /**
     *  В ЭТОЙ ЛАБОРАТОРНОЙ РАБОТЕ МАТРИЦУ ДОЛЖНЫ БЫТЬ СИММЕТРИЧНЫ
     */
    public static void main(String[] args) {
        var matrix = new DiagonallyDominantArrayMatrixGenerator(5).generate(50);
        JacobiRotation.calculate(matrix, 1E-2);
    }
}
