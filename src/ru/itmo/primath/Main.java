package ru.itmo.primath;

import ru.itmo.checkers.JacobiRotationChecker;
import ru.itmo.markdown.MarkdownDocument;
import ru.itmo.primath.algo.JacobiRotation;
import ru.itmo.primath.generator.DiagonallyDominantArrayMatrixGenerator;
import ru.itmo.primath.generator.HilbertArrayMatrixGenerator;
import ru.itmo.primath.generator.SymmetryMatrixGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     *  В ЭТОЙ ЛАБОРАТОРНОЙ РАБОТЕ МАТРИЦУ ДОЛЖНЫ БЫТЬ СИММЕТРИЧНЫ
     */
    public static void main(String[] args) throws IOException {
        var matrix = new HilbertArrayMatrixGenerator().generate(3);
        JacobiRotation.calculate(matrix, 1E-2);

//        List<SymmetryMatrixGenerator> symmetryMatrixGenerators = new ArrayList<>();
//        symmetryMatrixGenerators.add(new DiagonallyDominantArrayMatrixGenerator(1));
//
//        JacobiRotationChecker jacobiRotationChecker = new JacobiRotationChecker(symmetryMatrixGenerators, 1E-5, 10);
//        jacobiRotationChecker.check();
//
//        MarkdownDocument markdownDocument = new MarkdownDocument(jacobiRotationChecker.getMarkdownBlocks());
//        markdownDocument.toMarkdownFile("report.md");
    }
}
