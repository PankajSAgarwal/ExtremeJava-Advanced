package playground.serialization;

import java.io.Serializable;

public class Alien implements Serializable {
    private final String planet;
    private final long id;

    public Alien(String planet, long id) {
        this.planet = planet;
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " from " + planet;
    }
}
