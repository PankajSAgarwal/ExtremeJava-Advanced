package playground.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedFileTest {

    public static void main(String[] args) throws IOException {
        int length = 128*1024*1024;
        try(
            RandomAccessFile file = new RandomAccessFile("file.out","rw");
            FileChannel channel = file.getChannel();
            ){
            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_WRITE,0,length);
            for (int i = 0; i < length ; i++) {
                buf.put((byte)42);
                System.out.println("Forcing output before closing");
                buf.force();
                System.out.println("...done");
            }
        }

    }

}
