package exercise521_v1;

import org.junit.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class TextFileTest {
    public static void main(String... args) throws IOException {
        TextFile tf = new TextFile(
                "src/solution521_v1/"
                        + "TextFile.java");
        for (String line : tf) {
            System.out.println("> " + line);
        }
        TextFileTest tft = new TextFileTest();
        tft.testRead();
    }

    @Test
    public void testRead() throws FileNotFoundException {
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
        } catch (Exception unexpected) {
            fail("expected a NoSuchElementException, not a " +
                    unexpected.getClass().getSimpleName());
        }
    }
}
