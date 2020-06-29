package exercise422;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class ArraysWithReflectionMagicTest {
    @Test
    public void testIntArray() {
        int[] source = {1, 2, 3};
        int[] clone = ArraysWithReflectionMagic.deepClone(source);
        Arrays.fill(source, 0);
        assertFalse(Arrays.equals(source, clone));
    }

    @Test
    public void testIntArrayArray() {
        int[][] source = {{1, 2, 3}, {4, 5}, {6, 7, 8}};
        int[][] clone = ArraysWithReflectionMagic.deepClone(source);
        for (int i = 0; i < source.length; i++) {
            Arrays.fill(source[i], 0);
            assertFalse(Arrays.equals(source[i], clone[i]));
        }
    }

    @Test
    public void testObjectArray() {
        String[] source = {"John", "Anton", "Heinz"};
        String[] clone = ArraysWithReflectionMagic.deepClone(source);
        for (int i = 0; i < source.length; i++) {
            source[0] = "Dirk";
            assertFalse(Arrays.equals(source, clone));
        }
    }

    @Test
    public void testObjectArrayArray() {
        String[][] source = {
                {"John", "Anton", "Heinz"},
                {"Helene", "Heinz"},
                {"Maxi", "Connie", "Bangie", "Efi"}
        };
        String[][] clone = ArraysWithReflectionMagic.deepClone(source);
        for (int i = 0; i < source.length; i++) {
            Arrays.fill(source[i], "Anon");
            assertFalse(Arrays.equals(source[i], clone[i]));
        }
    }

    @Test
    public void testMixedObjectArrayArray() {
        Object[][] source = {
                new String[]{"John", "Anton", "Heinz"},
                new String[]{"Helene", "Heinz"},
                new String[]{"Maxi", "Connie", "Bangie", "Efi"}
        };
        Object[][] clone = ArraysWithReflectionMagic.deepClone(source);
        assertSame(source.getClass(), clone.getClass());
        assertSame(source.getClass().getComponentType(), clone.getClass().getComponentType());
        assertSame(source[0].getClass(), clone[0].getClass());
        assertSame(source[0].getClass().getComponentType(), clone[0].getClass().getComponentType());
        for (int i = 0; i < source.length; i++) {
            Arrays.fill(source[i], "Anon");
            assertFalse(Arrays.equals(source[i], clone[i]));

        }
    }

    @Test
    public void testNullObjectArrayArray() {
        Object[][] source = {
                new String[]{"John", "Anton", "Heinz"},
                null,
                null,
                new String[]{"Maxi", "Connie", "Bangie", "Efi"}
        };
        Object[][] clone = ArraysWithReflectionMagic.deepClone(source);
        assertSame(source.getClass(), clone.getClass());
        assertSame(source.getClass().getComponentType(), clone.getClass().getComponentType());
        assertSame(source[0].getClass(), clone[0].getClass());
        assertSame(source[0].getClass().getComponentType(), clone[0].getClass().getComponentType());
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                Arrays.fill(source[i], "Anon");
                assertFalse(Arrays.equals(source[i], clone[i]));
            } else {
                source[i] = new String[]{"Hello", "World"};
                assertFalse(Arrays.equals(source[i], clone[i]));
            }
        }
    }

    @Test
    public void testNonArraysSimplyReturn() {
        assertSame("Hello World", ArraysWithReflectionMagic.deepClone("Hello World"));
        assertNull(ArraysWithReflectionMagic.deepClone(null));
    }
}
