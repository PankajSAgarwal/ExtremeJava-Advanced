package playground;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EndOfFileInObjectInputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try(ObjectOutputStream out = new ObjectOutputStream(bout)){
            //out.writeInt(2); // upfront mention the number of objects to be written so easy to use while reading
            out.writeObject("Hello World");
            Map<String,String> greetings = new HashMap<>();
            greetings.put("English","hello");
            greetings.put("German","Guten Morgen");
            greetings.put("Hindi","Namastey");
            greetings.put("Odia","Namaskar");
            out.writeObject(greetings);
            //out.writeObject(null);//Best way to let know a file has ended
            out.writeObject(FileTokens.EOF);
        }

        try(ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bout.toByteArray())
        )){
            // in.available() gives 0
            // You can't use available to figure out
            // how much is left to be read from ObjectInputStream
            //System.out.println("in.available() = " + in.available());
            // Second way - to read the number of obejcts - not the beste way
            //int numberOfObjects = in.readInt();
//            for (int i = 0; i < numberOfObjects ; i++) {
//                System.out.println(in.readObject());
//            }

            // Best way

            Object o;

            //while((o=in.readObject()) != null){

            while((o=in.readObject()) != FileTokens.EOF){
                System.out.println(o);
            }


        }
    }
}
