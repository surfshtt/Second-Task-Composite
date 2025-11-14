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
        List<TextComponent> sentences = getSentences(text);
        TextComposite newSentence = new TextComposite(TypeComponent.SENTENCE);

        for(TextComponent sentence : sentences){
            if(sentence.getChild().size() > 3) {
                newSentence.add(sentence.getChild().get(sentence.getChild().size() - 1));
                for (int i = 1; i < sentence.getChild().size() - 1; i++) {
                    newSentence.add(sentence.getChild().get(i));
                }
                newSentence.add(sentence.getChild().get(0));
            }
        }

        TextComposite newText = new TextComposite(TypeComponent.TEXT);

        for(TextComponent paragraph : text.getChild()){
            TextComposite newParagraph = new TextComposite(TypeComponent.PARAGRAPH);
            for(TextComponent sentence : newSentence.getChild()){
                newParagraph.add(sentence);
            }
            newText.add(newParagraph);
        }

        return newText;
    }

    private List<TextComponent> getSentences(TextComponent text){
        List<TextComponent> sentences = new ArrayList<>();
        for(TextComponent paragraph : text.getChild()){
            sentences.addAll(paragraph.getChild());
        }

        return sentences;
    }
}
