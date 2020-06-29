package playground.serialization;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ObjectOutputDemo2 {
    public static void main(String[] args) throws IOException {
        try(
            FileOutputStream fos = new FileOutputStream("objects.bin");
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(fos))
        ){
            out.writeObject(new Alien("Mars",341242L));
            List<Object> aliens = new LinkedList<>();
            aliens.add(new Alien("Venus",1237232L));
            aliens.add(new Alien("Venus",1237233L));
            out.writeObject(aliens);
            out.writeObject(null); // end of objects
        }
    }
}
