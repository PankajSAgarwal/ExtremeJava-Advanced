package playground.streams;

import java.io.*;
import java.util.ArrayList;

public class ObjectOutputDemo {
    public static void main(String[] args) throws IOException {
        try(
            FileOutputStream fos =
                    new FileOutputStream("objects.bin");
            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new BufferedOutputStream(fos)
                    )


                ){
                out.writeObject("Hello there!");
            ArrayList<Object> items = new ArrayList<>();
            items.add("el1");
            items.add("el2");
            out.writeObject(items);
            out.writeObject(null);// show end of objects
        }
    }

}
