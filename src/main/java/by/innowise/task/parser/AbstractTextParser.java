package by.innowise.task.parser;
import by.innowise.task.entity.TextComponent;

public abstract class AbstractTextParser {
    private AbstractTextParser nextParser;

    public void setNext(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public AbstractTextParser getNext(){
        return nextParser;
    }

    public abstract TextComponent parse(String text);
}
