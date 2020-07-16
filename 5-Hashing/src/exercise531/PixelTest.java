package exercise531;

import org.junit.*;

import java.util.*;
import java.util.function.*;

import static org.junit.Assert.*;

public class PixelTest {
    private final static IntUnaryOperator java4hash =
            h -> {
                h ^= (h >>> 20) ^ (h >>> 12);
                return h ^ (h >>> 7) ^ (h >>> 4);
            };

    private final static IntUnaryOperator java8hash = h -> h ^ (h >>> 16);

    private final static IntUnaryOperator nohash =
            IntUnaryOperator.identity();

    @Test
    public void testForDuplicatesNormalScreen() {
        checkResolution(1024, 768, nohash);
        checkResolution(1024, 768, java4hash);
        checkResolution(1024, 768, java8hash);
    }

    @Test
    public void testForDuplicatesRidiculousNewIMac() {
        checkResolution(5120, 2880, nohash);
        checkResolution(5120, 2880, java4hash);
        checkResolution(5120, 2880, java8hash);
    }

    private void checkResolution(int width, int height, IntUnaryOperator hash) {
        Set<Integer> hashCodes = new HashSet<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                hashCodes.add(hash.applyAsInt(new Pixel(x, y).hashCode()));
            }
        }
        System.out.println("Ratio of pixels / hashcodes = " +
                (((double) width) * height / hashCodes.size()));
        assertEquals(width * height, hashCodes.size());
    }
}
