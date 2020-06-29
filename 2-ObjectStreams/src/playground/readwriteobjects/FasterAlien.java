package playground.readwriteobjects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Implements own custom read/write object without serialization caching
//Alien serialization supporting null planet
public class FasterAlien implements Serializable {

    private String planet; // can be made final but will require reflection to change
    private long id;

    public FasterAlien(String planet, long id) {
        this.planet = planet;
        this.id = id;
    }

    // readObject and writeObject needs to be private
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeBoolean(planet != null);
        if(planet != null)
            out.writeUTF(planet); // this avoids problems of caching in serialization but doesn't support null
        out.writeLong(id);
    }

    private void readObject(ObjectInputStream in) throws IOException {
        if(in.readBoolean())
            planet = in.readUTF();
        id = in.readLong();
    }
}
