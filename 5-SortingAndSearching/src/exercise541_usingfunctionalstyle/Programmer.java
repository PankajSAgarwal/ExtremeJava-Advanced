package exercise541_usingfunctionalstyle;

import java.util.*;

public class Programmer implements Comparable<Programmer> {
    private final String name;
    private final double salary;
    private final Collection<String> languages =
            new ArrayList<>();

    public Programmer(String name, double salary,
                      String... languages) {
        this.name = name;
        this.salary = salary;
        Collections.addAll(this.languages, languages);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Collection<String> getLanguages() {
        return Collections.unmodifiableCollection(languages);
    }

    public String toString() {
        return name +
                ", salary=" + salary +
                ", languages=" + languages;
    }

    public int compareTo(Programmer p) {
        return name.compareTo(p.name);
    }

    //  sort by biggest salary, then largest number of languages, then name
    public static final Comparator<Programmer> RICH_SMART_ORDER =
            Comparator
                    .comparingDouble(Programmer::getSalary)
                    .thenComparingInt((Programmer programmer) -> programmer.getLanguages().size())
                    .reversed() //default order is from lowest to highest, so need to use reversed
                    .thenComparing(Programmer::getName);



    //  sort by largest number of languages, then biggest salary, then name
    public static final Comparator<Programmer> SMART_RICH_ORDER =
            Comparator
                    .comparingInt((Programmer programmer) -> programmer.getLanguages().size())
                    // Alternate way to use type witness Comparator.<Programmer>comparingInt( programmer -> programmer.getLanguages().size())
                    .thenComparingDouble(Programmer::getSalary)
                    .reversed()
                    .thenComparing(Programmer::getName);
}
