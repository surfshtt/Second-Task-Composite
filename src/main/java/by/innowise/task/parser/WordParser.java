package by.innowise.task.parser;

import by.innowise.task.entity.TextComponent;
import by.innowise.task.entity.TextLeaf;
import by.innowise.task.entity.TypeComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractTextParser {
    @Override
    public TextComponent parse(String text){
        Pattern pattern = Pattern.compile(RegexConstant.WORD_REGEX);
        Matcher matcher = pattern.matcher(text);

        if(matcher.find()) {
            String word = matcher.group();
            return new TextLeaf(word.strip(), TypeComponent.WORD);
        }
        else{
            return new TextLeaf(text,TypeComponent.PUNCTUATION);
        }
    }
}