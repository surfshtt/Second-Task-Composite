package by.innowise.task.io.impl;

import by.innowise.task.exception.FileReadException;
import by.innowise.task.io.TextReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {
    @Override
    public String readText(String fileName) throws FileReadException {
        String text = "";
        Path path = Path.of(fileName);

        try {
            if (!Files.exists(path)) {
                throw new FileReadException("There isn't file in such directory: " + fileName);
            }

            if (!Files.isReadable(path)) {
                throw new FileReadException("File isn't readable in such directory: " + fileName);
            }

            text = Files.readString(path);
        }
        catch(IOException e) {
            throw new FileReadException("Error of file reading in such directory: " + fileName);
        }

        return text;
    }
}
