package ru.itmo.markdown.blocks;

public interface MarkdownBlock {
    String toMarkdown() throws Exception;
}
