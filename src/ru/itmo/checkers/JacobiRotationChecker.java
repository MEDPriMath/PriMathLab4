package ru.itmo.checkers;

import ru.itmo.markdown.blocks.MarkdownBlock;
import ru.itmo.markdown.blocks.MarkdownBold;
import ru.itmo.markdown.blocks.MarkdownHeader;
import ru.itmo.markdown.blocks.MarkdownQuote;
import ru.itmo.markdown.blocks.MarkdownTable;
import ru.itmo.markdown.blocks.MarkdownText;
import ru.itmo.markdown.description.DescriptionStorage;
import ru.itmo.primath.algo.JacobiRotation;
import ru.itmo.primath.generator.SymmetryMatrixGenerator;
import ru.itmo.primath.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

public class JacobiRotationChecker implements MatrixAlgorithmChecker {

//    private List<MarkdownBlock> markdownBlocks = new ArrayList<>();
//    private List<SymmetryMatrixGenerator> matrixGenerators;
//    private double precision;
//    private int matrixSize;
//
//    public JacobiRotationChecker(List<SymmetryMatrixGenerator> matrixGenerators, double precision, int matrixSize) {
//        this.matrixGenerators = matrixGenerators;
//        this.precision = precision;
//        this.matrixSize = matrixSize;
//    }

//    @Override
//    public void check() {
//        markdownBlocks.add(new MarkdownHeader("Check Jacobi rotation method", 1, true));
//        markdownBlocks.add(new MarkdownQuote(DescriptionStorage.jacobiRotation));
//        markdownBlocks.add(new MarkdownBold("Precision: " + String.format("%f", precision), true));
//
//        matrixGenerators.forEach(matrixGenerator -> {
//            Matrix generatedMatrix = matrixGenerator.generate(matrixSize);
//
//
//            MarkdownBlock generated = new MarkdownText("generated with " + matrixGenerator.getClass().getSimpleName() + ":", true);
//            markdownBlocks.add(generated);
//            System.out.println("generated with " + matrixGenerator.getClass().getSimpleName() + ":");
//            MarkdownBlock genMatrix = new MarkdownTable(generatedMatrix, true);
//            markdownBlocks.add(genMatrix);
//
//            Vector result = JacobiRotation.calculate(generatedMatrix, precision);
//
//            MarkdownBlock solutionBlock;
//            if (result.size() <= 50) {
//                solutionBlock = new MarkdownBold("Solution: " + result.toString(), true);
//            } else {
//                solutionBlock = new MarkdownBold("Solution is too big", true);
//            }
//            markdownBlocks.add(solutionBlock);
//        });
//    }
//
//    @Override
//    public List<MarkdownBlock> getMarkdownBlocks() {
//        return markdownBlocks;
//    }

    @Override
    public <T extends Matrix<T>> List<MarkdownBlock> check(List<SymmetryMatrixGenerator<T>> matrixGenerators, double precision, int matrixSize) {

        List<MarkdownBlock> markdownBlocks = new ArrayList<>();

        markdownBlocks.add(new MarkdownHeader("Check Jacobi rotation method", 1, true));
        markdownBlocks.add(new MarkdownQuote(DescriptionStorage.jacobiRotation));
        markdownBlocks.add(new MarkdownBold("Precision: " + String.format("%f", precision), true));

        matrixGenerators.forEach(matrixGenerator -> {
            T generatedMatrix = matrixGenerator.generate(matrixSize);

            MarkdownBlock generated = new MarkdownText("generated with " + matrixGenerator.getClass().getSimpleName() + ":", true);
            markdownBlocks.add(generated);
            System.out.println("generated with " + matrixGenerator.getClass().getSimpleName() + ":");
            MarkdownBlock genMatrix = new MarkdownTable(generatedMatrix, true);
            markdownBlocks.add(genMatrix);

            var result = JacobiRotation.calculate(generatedMatrix, precision);

            MarkdownBlock solutionBlock;
            if (result.eigenValues().size() <= 50) {
                solutionBlock = new MarkdownBold("Solution: " + result.toString(), true);
            } else {
                solutionBlock = new MarkdownBold("Solution is too big", true);
            }
            markdownBlocks.add(solutionBlock);
        });

        return markdownBlocks;
    }
}
