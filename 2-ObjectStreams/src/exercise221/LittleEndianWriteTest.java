package exercise221;

import org.junit.*;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

import static org.junit.Assert.*;

/**
 * DO NOT CHANGE.
 */
public class LittleEndianWriteTest {
    @Test
    public void testFileSize() throws IOException {
        LittleEndianWrite.main((String[]) null);
        try (
                RandomAccessFile raf = new RandomAccessFile(
                        LittleEndianWrite.FILENAME, "rw");
        ) {
            long expected = Long.BYTES + Short.BYTES + Integer.BYTES +
                    Long.BYTES + Character.BYTES;
            assertEquals("Incorrect file length", expected, raf.length());
        }
    }

    @Test
    public void testLittleEndianBytes() throws IOException {
        LittleEndianWrite.main((String[]) null);
        try (
                RandomAccessFile raf = new RandomAccessFile(
                        LittleEndianWrite.FILENAME, "rw");
                FileChannel fc = raf.getChannel();
        ) {
            MappedByteBuffer buf = fc.map(FileChannel.MapMode.READ_ONLY,
                    0, fc.size());

            assertEquals((byte) 0x00, buf.get());
            assertEquals((byte) 0xEF, buf.get());
            assertEquals((byte) 0xCD, buf.get());
            assertEquals((byte) 0xAB, buf.get());
            assertEquals((byte) 0x21, buf.get());
            assertEquals((byte) 0x43, buf.get());
            assertEquals((byte) 0x65, buf.get());
            assertEquals((byte) 0x87, buf.get());
            assertEquals((byte) 0x33, buf.get());
            assertEquals((byte) 0x00, buf.get());
            assertEquals((byte) 0x78, buf.get());
            assertEquals((byte) 0x56, buf.get());
            assertEquals((byte) 0x34, buf.get());
            assertEquals((byte) 0x12, buf.get());
            assertEquals((byte) 0x33, buf.get());
            assertEquals((byte) 0x33, buf.get());
            assertEquals((byte) 0x22, buf.get());
            assertEquals((byte) 0x22, buf.get());
            assertEquals((byte) 0x11, buf.get());
            assertEquals((byte) 0x11, buf.get());
            assertEquals((byte) 0x00, buf.get());
            assertEquals((byte) 0x00, buf.get());
            assertEquals((byte) 0xBC, buf.get());
            assertEquals((byte) 0xFA, buf.get());
            assertFalse(buf.hasRemaining());
        }
    }

    @Test
    public void testLittleEndianDataTypes() throws IOException {
        LittleEndianWrite.main((String[]) null);
        try (
                RandomAccessFile raf = new RandomAccessFile(
                        LittleEndianWrite.FILENAME, "rw");

                FileChannel fc = raf.getChannel();
        ) {
            MappedByteBuffer buf = fc.map(FileChannel.MapMode.READ_ONLY,
                    0, fc.size());

            buf.order(ByteOrder.LITTLE_ENDIAN);

            long l1 = 0x87654321ABCDEF00L;
            short s1 = 0x0033;
            int i1 = 0x12345678;
            long l2 = 0x0000111122223333L;
            char c1 = 0xFABC;

            assertEquals(l1, buf.getLong());
            assertEquals(s1, buf.getShort());
            assertEquals(i1, buf.getInt());
            assertEquals(l2, buf.getLong());
            assertEquals(c1, buf.getChar());
            assertFalse(buf.hasRemaining());
        }
    }
}
