package by.innowise.task.parser;

public class ParserChainBuilder {
    public AbstractTextParser buildChain(){
        AbstractTextParser wordParser = new WordParser();
        AbstractTextParser lexemeParser = new LexemeParser();
        lexemeParser.setNext(wordParser);

        AbstractTextParser sentenceParser = new SentenceParser();
        sentenceParser.setNext(lexemeParser);

        AbstractTextParser paragraphParser = new ParagraphParser();
        paragraphParser.setNext(sentenceParser);

        return paragraphParser;
    }
}
