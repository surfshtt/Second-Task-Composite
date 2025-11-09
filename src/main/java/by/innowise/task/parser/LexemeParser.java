package by.innowise.task.parser;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;

public class LexemeParser extends AbstractTextParser{
    @Override
    public TextComponent parse(String text){
        TextComposite sentenceComposite = new TextComposite(TypeComponent.SENTENCE);
        String[] parts = text.split(RegexConstant.LEXEME_REGEX);

        for (String part : parts) {
            if (part.isBlank()) {
                continue;
            }
            TextComponent lexemeComponent = nextParser.parse(part.trim());
            sentenceComposite.add(lexemeComponent);
        }

        return sentenceComposite;
    }
}
