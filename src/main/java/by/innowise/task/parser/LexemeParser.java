package by.innowise.task.parser;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractTextParser{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text){
        TextComposite sentenceComposite = new TextComposite(TypeComponent.SENTENCE);
        String[] parts = text.split(RegexConstant.LEXEME_REGEX);

        for (String part : parts) {
            if (part.isBlank()) {
                continue;
            }
            TextComponent lexemeComponent = this.getNext().parse(part.strip());
            sentenceComposite.add(lexemeComponent);
        }
        logger.debug("Parsed lexemes {}", sentenceComposite);
        return sentenceComposite;
    }
}
