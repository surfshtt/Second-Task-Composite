package by.innowise.task.parser;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text) {
        TextComposite textComposite = new TextComposite(TypeComponent.TEXT);
        Pattern pattern = Pattern.compile(RegexConstant.PARAGRAPH_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String paragraphText = matcher.group().strip();
            if (!paragraphText.isBlank()) {
                TextComposite paragraph = new TextComposite(TypeComponent.PARAGRAPH);
                TextComponent sentenceComponent = this.getNext().parse(paragraphText);
                for (TextComponent sentence : sentenceComponent.getChild()) {
                    paragraph.add(sentence);
                }
                textComposite.add(paragraph);
            }
        }
        logger.debug("Parsed text: {}", textComposite.toString());
        return textComposite;
    }
}
