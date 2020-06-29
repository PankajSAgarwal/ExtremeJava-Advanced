package exercise321;

import org.junit.BeforeClass;
import org.junit.Test;
import util.SuperSimpleGCParser;

import static org.junit.Assert.assertEquals;

public class FibonacciCalculator_withCacheTest {
    private final FibonacciCalculator_withCache fc = new FibonacciCalculator_withCache();

    @BeforeClass
    public static void setupGCViewerEvent() {
        SuperSimpleGCParser.showGCLogSummaryAtExit();
    }

    @Test
    public void test5() {
        assertEquals("5", fc.f(5));
    }

    @Test
    public void test20() {
        assertEquals("6765", fc.f(20));
    }

    @Test
    public void test40() {
        assertEquals("102334155", fc.f(40));
    }

    @Test
    public void test100() {
        assertEquals("354224848179261915075", fc.f(100));
    }

    @Test
    public void test120() {
        assertEquals("5358359254990966640871840", fc.f(120));
    }
}
