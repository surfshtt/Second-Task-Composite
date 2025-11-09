package by.innowise.task.entity;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final String content;
    private final TypeComponent type;

    public TextLeaf(String content, TypeComponent type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Cannot add to leaf");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Cannot remove from leaf");
    }

    @Override
    public List<TextComponent> getChild() {
        return Collections.emptyList();
    }

    @Override
    public TypeComponent getType() {
        return type;
    }

    @Override
    public String toString() {
        return content;
    }
}
