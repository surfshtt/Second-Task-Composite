package by.innowise.task.service.impl;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;
import by.innowise.task.service.TextService;

import java.util.*;

public class TextServiceImpl implements TextService {
    @Override
    public int findMaxCountOfSentencesWithSimilarWords(TextComponent text){
        List<TextComponent> sentences = getSentences(text);

        Map<String, Integer> wordsMap = new HashMap<>();
        for(TextComponent sentence : sentences){
            for(TextComponent lexeme : sentence.getChild()){
                for(TextComponent word : lexeme.getChild()){
                    wordsMap.put(word.toString(), wordsMap.getOrDefault(word.toString(),0) + 1);
                }
            }
        }

        int maxCount = wordsMap.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        return maxCount;
    }

    @Override
    public List<TextComponent> findAndSortAllSentences(TextComponent text) {
        List<TextComponent> sentences = getSentences(text);
        sentences.sort(Comparator.comparingInt(s -> s.getChild().size()));

        return sentences;
    }

    @Override
    public TextComponent changeFirstAndLastLexemes(TextComponent text) {
//        TextComponent changedText = new TextComposite(TypeComponent.TEXT);
//
//        for(TextComponent paragraph : text.getChild()){
//            for(TextComponent sentence : paragraph.getChild()){
//
//            }
//        }
        throw new UnsupportedOperationException("changeFirstAndLastLexemes was not implemented");
    }

    private List<TextComponent> getSentences(TextComponent text){
        List<TextComponent> sentences = new ArrayList<>();
        for(TextComponent paragraph : text.getChild()){
            sentences.addAll(paragraph.getChild());
        }

        return sentences;
    }
}
