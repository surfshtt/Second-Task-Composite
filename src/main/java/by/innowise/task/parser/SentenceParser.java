package by.innowise.task.parser;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextComposite;
import by.innowise.task.entity.TypeComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text){
        TextComposite paragraphComposite = new TextComposite(TypeComponent.PARAGRAPH);
        Pattern pattern = Pattern.compile(RegexConstant.SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String sentenceText = matcher.group().strip();
            if (!sentenceText.isBlank()) {
                TextComposite sentence = new TextComposite(TypeComponent.SENTENCE);
                TextComponent lexemeComponent = this.getNext().parse(sentenceText);
                for (TextComponent lexeme : lexemeComponent.getChild()) {
                    sentence.add(lexeme);
                }
                paragraphComposite.add(sentence);
            }
        }
        logger.debug("Parsed sentences {}", paragraphComposite.toString());
        return paragraphComposite;
    }
}
