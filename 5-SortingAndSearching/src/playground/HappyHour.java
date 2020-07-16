package playground;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class HappyHour extends BoozeFactory {

    private static Comparator<Employee> casual =
            (e1, e2) -> {
                int c = e1.getFirstName().compareTo(e2.getFirstName());
                if (c == 0)
                    c = e1.getSurName().compareTo(e2.getSurName());
                return c;
            };

    public static void main(String[] args) {
        Arrays.sort(staff,casual);
        System.out.println("// After Sorting ...");
        Stream.of(staff).forEach(System.out::println);
    }
}
