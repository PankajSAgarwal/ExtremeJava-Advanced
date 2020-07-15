package exercise521_v1;

import java.io.*;
import java.util.*;

/**
 * Instead of first building up a list and then iterating through
 * it, we iterate through the file. Be careful that hasNext()
 * does not unnecessarily advance the reading of the file.
 *
 * @author Heinz Kabutz
 * @see TextFileTest
 */
public class TextFile implements Iterable<String> {
    private final String filename;

    public TextFile(String filename) throws FileNotFoundException {
        this.filename = filename;
    }

    public Iterator<String> iterator() {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        // Create an anonymous inner class for the iterator
        return new Iterator<String>() {
            // create a field for the next line
                private String nextLine = null;
            // use a boolean to remember if you read file
                private boolean hasRead = false; // state machine

            /**
             * Calling hasNext() repeatedly should not advance the file.
             */
            public boolean hasNext() {
                // implement method to read line from file
                // null means you have reached the end
                if(!hasRead){
                    try {
                        nextLine = in.readLine();
                        hasRead = true;
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
                return nextLine != null;
            }

            public String next() {
                // return the next line if there is one
                if(!hasNext()) throw new  NoSuchElementException();
                String result = nextLine;
                nextLine = null;
                hasRead = false;
                return result;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
