package ru.itmo.checkers;

import ru.itmo.markdown.blocks.MarkdownBlock;

import java.util.List;

public interface MatrixAlgorithmChecker {
    void check();
    List<MarkdownBlock> getMarkdownBlocks();
}
