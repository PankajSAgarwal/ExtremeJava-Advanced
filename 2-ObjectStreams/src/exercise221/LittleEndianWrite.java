package exercise221;

import java.io.*;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LittleEndianWrite {
    public static final String FILENAME = "data.out";

    public static void main(String... args) throws IOException {
        long l1 = 0x87654321ABCDEF00L;
        short s1 = 0x0033;
        int i1 = 0x12345678;
        long l2 = 0x0000111122223333L;
        char c1 = 0xFABC;

        // Getting the length in bytes so that MappedByteBuffer length can be provided
        int length = Long.BYTES + Short.BYTES + Integer.BYTES + Long.BYTES + Character.BYTES;

        try (

                RandomAccessFile file = new RandomAccessFile(FILENAME, "rw");
                // Get the FileChannel from a RandomAccessFile
                FileChannel channel = file.getChannel();
        ) {
            // Get a mapped byte buffer from the channel
            //8-long,2-short,4-int,8-long,2-char
            // ByteBuffer not auto-closeable and MappedByteBuffer gets closed only when garbage collected
            //MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 8 + 2 + 4 + 8 + 2);
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);
            // Set the buffer to use Little Endian order
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            // write the various values and close the channel
            buffer.putLong(l1) ;
            buffer.putShort(s1) ;
            buffer.putInt(i1) ;
            buffer.putLong(l2) ;
            buffer.putChar(c1) ;
        }


    }
}
