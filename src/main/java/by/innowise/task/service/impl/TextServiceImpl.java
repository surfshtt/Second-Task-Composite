package by.innowise.task.service.impl;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;
import by.innowise.task.service.TextService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.*;

public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();

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

        logger.info("Max count of same-word: {}", maxCount);
        return maxCount;
    }

    @Override
    public List<TextComponent> findAndSortAllSentences(TextComponent text) {
        List<TextComponent> sentences = getSentences(text);
        logger.info("Sentences before sorting: {}", sentences);
        sentences.sort(Comparator.comparingInt(s -> s.toString().length()));
        logger.info("Sentences after sorting: {}", sentences);

        return sentences;
    }

    @Override
    public TextComponent changeFirstAndLastLexemes(TextComponent text) {
        if (text == null) return text;

        for(TextComponent paragraph : text.getChild()) {
            for (TextComponent sentence : paragraph.getChild()) {
                List<TextComponent> children = new ArrayList<>(sentence.getChild());
                List<Integer> lexemeIndexes = new ArrayList<>();
                for (int i = 0; i < children.size(); i++) {
                    if (children.get(i).getType() == TypeComponent.LEXEME) lexemeIndexes.add(i);
                }

                if (lexemeIndexes.size() >= 2) {
                    int firstIdx = lexemeIndexes.get(0);
                    int lastIdx = lexemeIndexes.get(lexemeIndexes.size() - 1);

                    TextComponent first = children.get(firstIdx);
                    TextComponent last = children.get(lastIdx);

                    children.set(firstIdx, last);
                    children.set(lastIdx, first);

                    sentence.setChild(children);
                }
            }
        }
        return text;
//        List<TextComponent> sentences = getSentences(text);
//        TextComposite newSentence = new TextComposite(TypeComponent.SENTENCE);
//
//        for(TextComponent sentence : sentences){
//            if(sentence.getChild().size() > 3) {
//                newSentence.add(sentence.getChild().get(sentence.getChild().size() - 1));
//                for (int i = 1; i < sentence.getChild().size() - 1; i++) {
//                    newSentence.add(sentence.getChild().get(i));
//                }
//                newSentence.add(sentence.getChild().get(0));
//            }
//        }
//        logger.info("Lexems been changed: {}", newSentence);
//
//        TextComposite newText = new TextComposite(TypeComponent.TEXT);
//        for(TextComponent paragraph : text.getChild()){
//            TextComposite newParagraph = new TextComposite(TypeComponent.PARAGRAPH);
//            for(TextComponent sentence : newSentence.getChild()){
//                newParagraph.add(sentence);
//            }
//            newText.add(newParagraph);
//        }
//
//        return newText;
    }

    private List<TextComponent> getSentences(TextComponent text){
        List<TextComponent> sentences = new ArrayList<>();
        for(TextComponent paragraph : text.getChild()){
            sentences.addAll(paragraph.getChild());
        }
        logger.info("Sentences: {}", sentences);

        return sentences;
    }
}
