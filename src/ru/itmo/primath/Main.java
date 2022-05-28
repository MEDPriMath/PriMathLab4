package ru.itmo.primath;

import ru.itmo.primath.algo.JacobiRotation;
import ru.itmo.primath.generator.HilbertArrayMatrixGenerator;

public class Main {
    public static void main(String[] args) {
        var hilbert = new HilbertArrayMatrixGenerator().generate(10);
        JacobiRotation.calculate(hilbert, 1E-6);
    }
}
