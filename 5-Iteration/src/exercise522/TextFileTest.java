package exercise522;

import org.junit.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.Assert.*;

public class TextFileTest {
    public static void main(String... args) throws IOException {
        TextFile tf = new TextFile(
                "src/exercise522/"
                        + "TextFile.java");
        for (String line : tf) {
            System.out.println("> " + line);
        }
        TextFileTest tft = new TextFileTest();
        tft.testRead();
    }

    @Test
    public void testRead() throws NoSuchFileException {
        TextFile tf = new TextFile(
                "src/exercise521_v1/"
                        + "hello.txt");
        Iterator<String> it = tf.iterator();
        assertEquals("hello", it.next());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals("goodbye", it.next());
        assertEquals("world", it.next());
        assertFalse(it.hasNext());

        try {
            it.next();
            fail("at the end, next() should throw an exception");
        } catch (NoSuchElementException expected) {
        }
    }

    @Test
    public void testNoSuchFile() {
        String filename = "does_not_exist.txt";
        try {
            TextFile tf = new TextFile(filename);
            fail("expected this to fail");
        } catch (NoSuchFileException success) {
            assertEquals(filename, success.getFile());
        }
    }

    @Test
    public void testUncheckedIOException() throws FileNotFoundException, NoSuchFileException {
        String filename = "phantomfile.txt";
        File file = new File(filename);
        PrintStream out = new PrintStream(file);
        out.println("I'm here for a bit");
        out.close();

        TextFile tf = new TextFile(filename);
        file.delete();
        try {
            for (String s : tf) {
                System.out.println(s);
            }
            fail("Expected an exception");
        } catch (UncheckedIOException success) {
            IOException cause = success.getCause();
            assertSame(cause.getClass(), NoSuchFileException.class);
            assertEquals(filename, ((NoSuchFileException) cause).getFile());
        } catch (Throwable error) {
            fail(error.toString());
        }
    }
}
