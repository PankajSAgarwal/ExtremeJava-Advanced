package exercise521_v2;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
    private final FileChannel fileChannel;

    public TextFile(String filename) throws FileNotFoundException {
        this.filename = filename;
        RandomAccessFile raf = new RandomAccessFile(filename,"r");// r- readonly mode
        fileChannel = raf.getChannel();
    }

    public Iterator<String> iterator() {
        MappedByteBuffer buf;
        try {
            buf = fileChannel.map(FileChannel.MapMode.READ_ONLY,0,fileChannel.size());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        // Create an anonymous inner class for the iterator
        return new Iterator<String>() {
            // create a field for the next line
            private String nextLine = null;

            // use a boolean to remember if you read file
            private boolean hasRead = false;

            /**
             * Calling hasNext() repeatedly should not advance the file.
             */
            public boolean hasNext() {
                // implement method to read line from file
                // null means you have reached the end

//                if(!hasRead){
//                    nextLine = readLine(buf);
//                    hasRead = true;
//                }
//                return nextLine != null;

                return buf.hasRemaining();
            }



            public String next() {
                // return the next line if there is one
//                if(!hasNext()) throw new NoSuchElementException();
//                String result = nextLine;
//                nextLine = null;
//                hasRead = false;
//                return result;
                if(!hasNext()) throw new NoSuchElementException();
                return readLine(buf);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private String readLine(MappedByteBuffer buf) {
        StringBuilder sb = new StringBuilder();
        while (buf.hasRemaining()){
            char c = (char) buf.get();
            if(c != '\r'){
                if(c=='\n'){
                    return sb.toString();
                }else{
                    sb.append(c);
                }
            }
        }
        if(sb.length() == 0) return null;
        return sb.toString();
    }
}
