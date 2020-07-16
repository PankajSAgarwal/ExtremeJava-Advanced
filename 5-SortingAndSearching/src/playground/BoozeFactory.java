package playground;

import java.util.Arrays;
import java.util.stream.Stream;

public class BoozeFactory {

    protected static Employee[] staff = {
            new Employee("John","Smith"),
            new Employee("Johnny","Walker"),
            new Employee("Peter","Stuyvesant"),
            new Employee("Albert","Zero"),
            new Employee("Sakkie","Nel"),
            new Employee("Zach","Thalla")
    };

    public static void main(String[] args) {
        System.out.println("// Before Sorting ...");
        Stream.of(staff).forEach(System.out::println);
        Arrays.sort(staff);
        System.out.println("// After Sorting ...");
        Stream.of(staff).forEach(System.out::println);

    }
}
