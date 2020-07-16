package exercise541_preJava8way;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
            (o1, o2) -> {
                int result = -Double.compare(o1.getSalary(), o2.getSalary());

                if (result != 0) return result;

                result = -Integer.compare(o1.getLanguages().size(), o2.getLanguages().size());

                if (result != 0) return result;

                return o1.getName().compareTo(o2.getName());

            };


    //  sort by largest number of languages, then biggest salary, then name
    public static final Comparator<Programmer> SMART_RICH_ORDER =
            (o1,o2) -> {
                int result = -Integer.compare(o1.getLanguages().size(),o2.getLanguages().size());
                if(result != 0) return result;

                result = -Double.compare(o1.getSalary(),o2.getSalary());

                if(result !=0) return result;

                return o1.getName().compareTo(o2.getName());
            };

}
