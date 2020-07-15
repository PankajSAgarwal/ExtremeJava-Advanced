package exercise522;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.*;

/**
 * Instead of writing our own Iterators, we can simply delegate
 * to the Files.line(Path) method, which works similarly to our
 * exercise 5.2.1.
 *
 * @author Heinz Kabutz
 * @see TextFileTest
 */
public class TextFile implements Iterable<String> {
    private final Path path;

    public TextFile(String filename) throws NoSuchFileException {
        this.path = Paths.get(filename);
        // throw NoSuchFileException if the file does not exist.
        if(!this.path.toFile().exists())
            throw new NoSuchFileException(filename);
    }

    /**
     * Convert the stream from Files.lines() to an iterator.
     * If an IOException occurs, make it unchecked and throw it
     */
    public Iterator<String> iterator() {
        try {
           return Files.lines(path).iterator();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}