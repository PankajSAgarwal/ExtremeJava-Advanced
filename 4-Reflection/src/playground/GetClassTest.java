package playground;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Method;
import java.util.stream.Stream;

public class GetClassTest {
    public static void main(String[] args) {
        System.out.println("String has these methods");
        // prints all public method of String
        Stream.of("hello".getClass().getMethods())
                .map((Method method) -> "\t" + method)
                .forEach(System.out::println);
    }
}
