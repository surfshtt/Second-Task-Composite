package by.innowise.task.service;

import by.innowise.task.entity.TextComponent;

import java.util.List;

public interface TextService {
    int findMaxCountOfSentencesWithSimilarWords(TextComponent text);
    List<TextComponent> findAndSortAllSentences(TextComponent text);
    TextComponent changeFirstAndLastLexemes(TextComponent text);
}
