package ru.itmo.primath.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.itmo.primath.gendata.HilbertDataGenerator;
import ru.itmo.primath.matrix.ArrayMatrix;
import ru.itmo.primath.matrix.CSRMatrix;
import ru.itmo.primath.matrix.Matrix;
import ru.itmo.primath.vector.Vector;

import java.util.stream.Stream;

public class JacobiRotationTest {

    private static Stream<Arguments> calculateTestParameters() {
        return Stream.of(
                Arguments.of(
                        new ArrayMatrix(new double[][]{
                                {4, 2, 1},
                                {2, 5, 3},
                                {1, 3, 6}}),
                        new Vector(new double[]{
                                3.706, 1.929, 9.38
                        }),
                        0.3
                ),
                Arguments.of(
                        new CSRMatrix(new double[][]{
                                {4, 2, 1},
                                {2, 5, 3},
                                {1, 3, 6}}),
                        new Vector(new double[]{
                                3.706, 1.929, 9.38
                        }),
                        0.3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("calculateTestParameters")
    public <T extends Matrix<T>> void calculateTest(T matrix, Vector eigenValues, double epsilon) {
        var result = JacobiRotation.calculate(matrix, epsilon);
        var calculatedEigenValues = result.eigenValues();
        for (int i = 0; i < eigenValues.size(); i++) {
            Assertions.assertTrue(Math.abs(eigenValues.get(i) - calculatedEigenValues.get(i)) < epsilon,
                    String.format("Expected %f, got %f", eigenValues.get(i), calculatedEigenValues.get(i)));
        }
    }

    @Test
    @Disabled
    public void PerformanceTest() {
        var data = new HilbertDataGenerator().generate(200);
        var m = new CSRMatrix(data);
        var eigenValues = JacobiRotation.calculate(m, 0.01);

        /*  Stat
            Size    Time
            100     12 s 115 ms
            200     3 min 20s
         */
    }
}
