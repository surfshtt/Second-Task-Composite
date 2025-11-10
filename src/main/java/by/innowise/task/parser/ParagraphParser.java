package by.innowise.task.parser;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractTextParser {
    @Override
    public TextComponent parse(String text) {
        TextComposite textComposite = new TextComposite(TypeComponent.TEXT);
        Pattern pattern = Pattern.compile(RegexConstant.PARAGRAPH_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String paragraphText = matcher.group().strip();
            if (!paragraphText.isBlank()) {
                TextComposite paragraph = new TextComposite(TypeComponent.PARAGRAPH);
                TextComponent sentenceComponent = nextParser.parse(paragraphText);
                for (TextComponent sentence : sentenceComponent.getChild()) {
                    paragraph.add(sentence);
                }
                textComposite.add(paragraph);
            }
        }
        return textComposite;
    }
}
