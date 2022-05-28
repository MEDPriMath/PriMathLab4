package ru.itmo.primath;

import ru.itmo.checkers.JacobiRotationChecker;
import ru.itmo.markdown.MarkdownDocument;
import ru.itmo.primath.algo.JacobiRotation;
import ru.itmo.primath.generator.DiagonallyDominantArrayMatrixGenerator;
import ru.itmo.primath.generator.SymmetryMatrixGenerator;
import ru.itmo.primath.matrix.ArrayMatrix;

import java.util.ArrayList;

public class Main {

    /**
     * В ЭТОЙ ЛАБОРАТОРНОЙ РАБОТЕ МАТРИЦЫ ДОЛЖНЫ БЫТЬ СИММЕТРИЧНЫ
     */
    public static void main(String[] args) throws Exception {
        var matrix = new DiagonallyDominantArrayMatrixGenerator(5).generate(50);
        JacobiRotation.calculate(matrix, 1E-2);

//        List<DiagonallyDominantArrayMatrixGenerator> symmetryMatrixGenerators = new ArrayList<>();
//        symmetryMatrixGenerators.add(new DiagonallyDominantArrayMatrixGenerator(1));

        var generators = new ArrayList<SymmetryMatrixGenerator<ArrayMatrix>>();
        generators.add(new DiagonallyDominantArrayMatrixGenerator(1));

//        JacobiRotationChecker jacobiRotationChecker = new JacobiRotationChecker(generators, 1E-5, 10);
//        jacobiRotationChecker.check();
        var jacobiRotationCheckMD = new JacobiRotationChecker().check(
                generators,
                1E-5,
                10
        );

        MarkdownDocument markdownDocument = new MarkdownDocument(jacobiRotationCheckMD);
        markdownDocument.toMarkdownFile("report.md");

//        MarkdownDocument.toHTML("report.md", "..\\ReportLab4\\index.html");

//        commitAndPush();
    }

    private static int commitAndPush() throws Exception {
        Process p = Runtime.getRuntime().exec("..\\ReportLab4\\pushScript.cmd");
        return p.waitFor();
    }
}
