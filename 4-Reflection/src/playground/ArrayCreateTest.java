package playground;

import java.lang.reflect.Array;

// We can create array from any class type
public class ArrayCreateTest {

    public static void main(String[] args) {
        int[] m = (int[]) Array.newInstance(int.class,5);
        m[0] = 35;
        m[1] = 36;
        m[2] = 9;
        m[3] = 6;
        m[4] = 1;

        for (int i : m) {
            System.out.println(i);
        }
    }
}
