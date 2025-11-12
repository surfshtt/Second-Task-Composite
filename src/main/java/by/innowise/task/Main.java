package by.innowise.task;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.exception.FileReadException;
import by.innowise.task.io.TextReader;
import by.innowise.task.io.impl.TextReaderImpl;
import by.innowise.task.parser.AbstractTextParser;
import by.innowise.task.parser.ParserChainBuilder;

public class Main {
    public static void main(String[] args) throws FileReadException {
        TextReader reader = new TextReaderImpl();
        String text = reader.readText("src/main/resources/text.txt");

        ParserChainBuilder parserChainBuilder = new ParserChainBuilder();
        AbstractTextParser textParser = parserChainBuilder.buildChain();

        TextComponent chainText = textParser.parse(text);
        for(TextComponent paragraphs : chainText.getChild()){
            for(TextComponent sentences : paragraphs.getChild()){
                System.out.print(sentences.toString());
            }
        }
    }
}