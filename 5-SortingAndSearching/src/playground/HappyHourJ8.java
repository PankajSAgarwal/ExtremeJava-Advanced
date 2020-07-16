package playground;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class HappyHourJ8 extends BoozeFactory {

    private static Comparator<Employee> casual =
            Comparator.comparing(Employee::getFirstName)
            .thenComparing(Employee::getSurName);

    public static void main(String[] args) {

        Arrays.sort(staff,casual);
        System.out.println("After Sorting ...");
        Stream.of(staff).forEach(System.out::println);
    }
}
