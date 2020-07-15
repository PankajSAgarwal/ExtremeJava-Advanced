package playground.concurrentmodificationexception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CatIterators {
    public static void main(String[] args) {
        Collection<Cat> cats = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            cats.add(new Cat(i));

        Iterator<Cat> it = cats.iterator();
        it.next().meow();
        cats.add(new Cat(4));
        it.next().meow();// Concurrent modification exception

    }
}
