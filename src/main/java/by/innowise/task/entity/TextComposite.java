package by.innowise.task.entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private static final String PARAGRAPHS_SYMBOL = "\n";
    private static final String LEXEMES_SYMBOL = " ";
    private static final String SENTENCES_SYMBOL= " ";

    private final List<TextComponent> components = new ArrayList<>();
    private final TypeComponent type;

    public TextComposite(TypeComponent type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component){
        components.add(component);
    }

    @Override
    public void remove(TextComponent component){
        components.remove(component);
    }

    @Override
    public TypeComponent getType(){
        return type;
    }

    @Override
    public List<TextComponent> getChild(){
        return components;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        for(TextComponent component : components){
            builder.append(component.toString());

            switch (type) {
                case TEXT:
                    builder.append(PARAGRAPHS_SYMBOL);
                    break;
                case PARAGRAPH:
                    builder.append(SENTENCES_SYMBOL);
                    break;
                case SENTENCE:
                    builder.append(LEXEMES_SYMBOL);
                    break;
                default:
                    break;
            }
        }

        return builder.toString();
    }
}



