package by.innowise.task.service.impl;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.exception.FileReadException;
import by.innowise.task.io.TextReader;
import by.innowise.task.io.impl.TextReaderImpl;
import by.innowise.task.parser.AbstractTextParser;
import by.innowise.task.parser.ParserChainBuilder;
import by.innowise.task.service.TextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextServiceTest {
    private TextService textService;
    private AbstractTextParser parser;
    private ParserChainBuilder chainBuilder;
    private TextReader reader;
    private TextComponent textChain;

    @BeforeEach
    void SetUp() throws FileReadException {
        textService = new TextServiceImpl();
        chainBuilder = new ParserChainBuilder();
        reader = new TextReaderImpl();

        String text = reader.readText("src/main/resources/text.txt");

        parser = chainBuilder.buildChain();
        textChain = parser.parse(text);
    }

    @Test
    void findMaxCountOfSentencesWithSimilarWordsTest(){
        int expected = 6;

        int actual = textService.findMaxCountOfSentencesWithSimilarWords(textChain);

        assertEquals(expected, actual);
    }

    @Test
    void findAndSortAllSentencesTest(){
        List<Integer> expected = Arrays.asList(4, 84, 118, 124, 171, 200);

        List<Integer> actual = new ArrayList<>();
        for(TextComponent sentence : textService.findAndSortAllSentences(textChain)){
            actual.add(sentence.toString().length());
        }

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void changeFirstAndLastLexemes(){
        int expected = textChain.toString().length() + 1;

        int actual = textService.changeFirstAndLastLexemes(textChain).toString().trim().length();

        assertEquals(expected, actual);
    }
}
