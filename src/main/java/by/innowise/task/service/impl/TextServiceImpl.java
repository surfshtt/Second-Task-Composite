package by.innowise.task.service.impl;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;
import by.innowise.task.service.TextService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int findMaxCountOfSentencesWithSimilarWords(TextComponent text){
        Map<Set<String>, Integer> wordSetToCount = new HashMap<>();
        for(TextComponent paragraph : text.getChild()){
            for (TextComponent sentence : paragraph.getChild()) {
                List<String> words = new ArrayList<>();
                for (TextComponent lexeme : sentence.getChild()) {
                    for (TextComponent child : lexeme.getChild()) {
                        if (child.getType() == TypeComponent.WORD) {
                            words.add(child.toString());
                        }
                    }
                }
                wordSetToCount.merge(new HashSet<>(words), 1, Integer::sum);
            }
        }

        int maxCount = wordSetToCount.values().stream()
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
        TextComposite newText = new TextComposite(TypeComponent.TEXT);
        for(TextComponent paragraph : text.getChild()){
            TextComposite newParagraph = new TextComposite(TypeComponent.PARAGRAPH);
            for(TextComponent sentence : paragraph.getChild()){
                TextComposite newSentence = new TextComposite(TypeComponent.SENTENCE);
                int n = sentence.getChild().size();

                newSentence.add(sentence.getChild().get(n-1));
                for(int i = 1;i < n - 1; i++){
                    newSentence.add(sentence.getChild().get(i));
                }
                newSentence.add(sentence.getChild().get(0));

                newParagraph.add(newSentence);
            }
            newText.add(newParagraph);
        }
        logger.info("Changed text: {}", newText);

        return newText;
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
