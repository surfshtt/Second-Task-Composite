package by.innowise.task.parser;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextLeaf;
import by.innowise.task.entity.TypeComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractTextParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public TextComponent parse(String text){
        Pattern pattern = Pattern.compile(RegexConstant.WORD_REGEX);
        Matcher matcher = pattern.matcher(text);

        if(matcher.find()) {
            String word = matcher.group();
            logger.debug("Parsed word {}", word);
            return new TextLeaf(word.strip(), TypeComponent.WORD);
        }
        else{
            logger.debug("Parsed punctuation {}", text);
            return new TextLeaf(text,TypeComponent.PUNCTUATION);
        }
    }
}