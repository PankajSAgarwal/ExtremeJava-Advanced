package exercise211;

import org.junit.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * DO NOT CHANGE.
 */
public class CorrectnessTest {
    private final Random rand = new Random(0);

    @Test
    public void testWritingRandomValue() throws ClassNotFoundException, IOException {
        testWriteAndRead(new ComplexClass(rand.nextInt(), rand.nextLong(),
                Integer.toString(rand.nextInt()), rand.nextBoolean(),
                rand.nextDouble(), rand.nextFloat()));
    }

    @Test
    public void testNullValues() throws ClassNotFoundException, IOException {
        testWriteAndRead(new ComplexClass(rand.nextInt(), rand.nextLong(), null,
                rand.nextBoolean(), null, rand.nextFloat()));
    }

    @Test
    public void testNaNValues() throws ClassNotFoundException, IOException {
        testWriteAndRead(new ComplexClass(rand.nextInt(), rand.nextLong(), "hello",
                rand.nextBoolean(), Double.NaN, rand.nextFloat()));
    }

    @Test
    public void testWithIntValues() throws ClassNotFoundException, IOException {
        ComplexClass cc = new ComplexClass(
                rand.nextInt(), rand.nextLong(), "parent",
                rand.nextBoolean(), Double.NaN, rand.nextFloat()
        );
        for (int i = 0; i < 100; i++) {
            cc.add(i);
        }
        testWriteAndRead(cc);
    }

    private static void testWriteAndRead(ComplexClass cc1) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(cc1);
        out.close();
        ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(bout
                .toByteArray()));
        ComplexClass cc2 = (ComplexClass) oin.readObject();
        assertEquals(cc1, cc2);
    }

    @Test
    public void testSerialVersionUID() {
        try {
            Method writeObject = ComplexClass.class.getDeclaredMethod(
                    "writeObject", ObjectOutputStream.class);
            Field serialVersionUID = ComplexClass.class.getDeclaredField("serialVersionUID");
            serialVersionUID.setAccessible(true);
            assertFalse(1 == (Long) serialVersionUID.get(null));
        } catch (NoSuchMethodException e) {
            // return;
        } catch (NoSuchFieldException e) {
            fail("Probably should keep the serialVersionUID in the class");
        } catch (IllegalAccessException e) {
            fail();
        }
    }
}
