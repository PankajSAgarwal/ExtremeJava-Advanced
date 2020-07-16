package exercise541_usingfunctionalstyle;

import org.junit.*;

import java.util.*;
import java.util.stream.*;

import static org.junit.Assert.*;

public class ProgrammerTest {
    private final Programmer[] programmers = {
            new Programmer("Marc Fleury", 200_000_000, "Javascript", "Delphi", "VisualBasic"),
            new Programmer("Rod Johnson", 400_000_000, "Java", "Smalltalk"),
            new Programmer("Peter Lawrey", 1_000_000, "Java", "Assembler"),
            new Programmer("Martin Fowler", 1_000_000, "Java", "C++"),
            new Programmer("Martin Thompson", 1_000_000, "Java", "C++", "Clojure", "Scheme", "QuickBasic"),
            new Programmer("Kirk Pepperdine", 1_000_000, "Java", "Smalltalk"),
    };

    @Test
    public void testRichList() {
        System.out.println("ProgrammerTest.testRichList");
        Arrays.sort(programmers, Programmer.RICH_SMART_ORDER);
        Stream.of(programmers).forEach(System.out::println);
        int index = 0;
        assertEquals("Rod Johnson", programmers[index++].getName());
        assertEquals("Marc Fleury", programmers[index++].getName());
        assertEquals("Martin Thompson", programmers[index++].getName());
        assertEquals("Kirk Pepperdine", programmers[index++].getName());
        assertEquals("Martin Fowler", programmers[index++].getName());
        assertEquals("Peter Lawrey", programmers[index++].getName());
        System.out.println();
    }

    @Test
    public void testSmartList() {
        System.out.println("ProgrammerTest.testSmartList");
        Arrays.sort(programmers, Programmer.SMART_RICH_ORDER);
        Stream.of(programmers).forEach(System.out::println);
        int index = 0;
        assertEquals("Martin Thompson", programmers[index++].getName());
        assertEquals("Marc Fleury", programmers[index++].getName());
        assertEquals("Rod Johnson", programmers[index++].getName());
        assertEquals("Kirk Pepperdine", programmers[index++].getName());
        assertEquals("Martin Fowler", programmers[index++].getName());
        assertEquals("Peter Lawrey", programmers[index++].getName());
        System.out.println();
    }

    @Test
    public void testNaturalList() {
        System.out.println("ProgrammerTest.testNaturalList");
        Arrays.sort(programmers);
        Stream.of(programmers).forEach(System.out::println);
        int index = 0;
        assertEquals("Kirk Pepperdine", programmers[index++].getName());
        assertEquals("Marc Fleury", programmers[index++].getName());
        assertEquals("Martin Fowler", programmers[index++].getName());
        assertEquals("Martin Thompson", programmers[index++].getName());
        assertEquals("Peter Lawrey", programmers[index++].getName());
        assertEquals("Rod Johnson", programmers[index++].getName());
        System.out.println();
    }
}
