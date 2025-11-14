package by.innowise.task.io.impl;

import by.innowise.task.exception.FileReadException;
import by.innowise.task.io.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String readText(String fileName) throws FileReadException {
        String text = "";
        Path path = Path.of(fileName);

        try {
            if (!Files.exists(path)) {
                logger.error("There isn't file in such directory {}", fileName);
                throw new FileReadException("There isn't file in such directory: " + fileName);
            }

            if (!Files.isReadable(path)) {
                logger.error("File isn't readable in such directory {}", fileName);
                throw new FileReadException("File isn't readable in such directory: " + fileName);
            }

            text = Files.readString(path);
        }
        catch(IOException e) {
            logger.error("Error of file reading in such directory {}", fileName);
            throw new FileReadException("Error of file reading in such directory: " + fileName);
        }
        logger.info("Successfully reading of file {}", fileName);

        return text;
    }
}
