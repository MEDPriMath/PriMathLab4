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
    public static void main(String[] args) throws Exception {
        var matrix = new DiagonallyDominantArrayMatrixGenerator(5).generate(50);
        JacobiRotation.calculate(matrix, 1E-2);

        List<SymmetryMatrixGenerator> symmetryMatrixGenerators = new ArrayList<>();
        for (int k = 0; k < 5; ++k)
            symmetryMatrixGenerators.add(new DiagonallyDominantArrayMatrixGenerator(k));
        symmetryMatrixGenerators.add(new HilbertArrayMatrixGenerator());

        JacobiRotationChecker jacobiRotationChecker = new JacobiRotationChecker(symmetryMatrixGenerators, 1E-5, 10);
        jacobiRotationChecker.check();

        MarkdownDocument markdownDocument = new MarkdownDocument(jacobiRotationChecker.getMarkdownBlocks());
        markdownDocument.toMarkdownFile("report.md");

//        MarkdownDocument.toHTML("report.md", "..\\ReportLab4\\index.html");

//        commitAndPush();
    }

    private static int commitAndPush() throws Exception {
        Process p = Runtime.getRuntime().exec("..\\ReportLab4\\pushScript.cmd");
        return p.waitFor();
    }
}
