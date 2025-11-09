package by.innowise.task.parser;

final class RegexConstant {
    static final String PARAGRAPH_REGEX = "(?m)^\\s*\\S.*$";
    static final String SENTENCE_REGEX = "[^.!?]+[.!?]+";
    static final String LEXEME_REGEX = "\\s+";
    static final String WORD_REGEX = "[\\p{L}'-]+";

    private RegexConstant() {}
}
