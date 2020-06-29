package playground;

public class ModifyArrayContentsTest {
    public static void main(String[] args) {
        String[] values = {"a","b","c"};
        java.lang.reflect.Array.set(values,2,"hello");
        //output : [a, b, hello]
        System.out.println(java.util.Arrays.toString(values));
        //output : b
        System.out.println(java.lang.reflect.Array.get(values,1));

    }
}
