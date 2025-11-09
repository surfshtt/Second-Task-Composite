package by.innowise.task.parser;
import by.innowise.task.entity.TextComponent;

public abstract class AbstractTextParser {
    public AbstractTextParser nextParser;

    public void setNext(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);
}
