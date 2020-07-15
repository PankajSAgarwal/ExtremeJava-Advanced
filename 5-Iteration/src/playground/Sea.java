package playground;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// make anything iterable
public class Sea implements Iterable<Octopus> {
    private final Collection<Octopus> octopi = new ArrayList<>();

    @Override
    public Iterator<Octopus> iterator() {
        return octopi.iterator();
    }
}
