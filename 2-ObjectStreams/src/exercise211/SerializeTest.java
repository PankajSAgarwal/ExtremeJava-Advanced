package exercise211;

import util.*;

import util.Benchmark;
import util.CountingOutputStream;
import util.SuperSimpleGCParser;

import java.io.*;
import java.util.concurrent.*;

/**
 * User time should be about 10x faster.
 * Memory should be about 9x less
 * bytes streamed should be about 1.8x less
 * Use VM argument -Xloggc:filename.vgc
 * <p>
 * DO NOT CHANGE.
 */
public class SerializeTest {
    public static void main(String... args) throws Exception {
        SuperSimpleGCParser.showGCLogSummaryAtExit();
        ComplexClass[] objects = new ComplexClass[100000];
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new ComplexClass(
                    tlr.nextInt(), tlr.nextLong(), "" + tlr.nextInt(),
                    tlr.nextBoolean(), tlr.nextDouble(), tlr.nextFloat());
            int intvals = tlr.nextInt(10);
            for (int j = 0; j < intvals; j++) {
                objects[i].add(tlr.nextInt());
            }
        }

        for (int i = 0; i < 30; i++) {
            test(objects);
        }
    }

    private static void test(ComplexClass... objects) throws IOException {
        Benchmark mbm = new Benchmark();
        mbm.start();
        CountingOutputStream countingStream = new CountingOutputStream();
        for (int j = 0; j < 10; j++) {
            ObjectOutput out = new ObjectOutputStream(countingStream);
            for (ComplexClass object : objects) {
                out.writeObject(object);
            }
            out.close();
        }
        mbm.stop();
        System.out.printf("%s,%s%n", mbm,
                countingStream);
    }
}
