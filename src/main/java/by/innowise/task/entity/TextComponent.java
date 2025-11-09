package by.innowise.task.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    void remove(TextComponent component);
    TypeComponent getType();
    List<TextComponent> getChild();
}
