package by.innowise.task.io;

import by.innowise.task.exception.FileReadException;

public interface TextReader {
    String readText(String fileName) throws FileReadException;
}
