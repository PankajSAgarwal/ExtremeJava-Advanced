package playground.cachingobjects;

import playground.streams.FileTokens;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EndOfFileInObjectInputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try(ObjectOutputStream out = new ObjectOutputStream(bout)){
            //out.writeInt(2); // upfront mention the number of objects to be written so easy to use while reading
            //out.writeObject("Hello World");
            out.writeUnshared("Hello World");
            Map<String,String> greetings = new HashMap<>();
            greetings.put("English","Good Morning");
            greetings.put("German","Guten Morgen");
            greetings.put("Hindi","Namastey");
            greetings.put("Odia","Namaskaram");
            //out.writeObject(greetings);
            // out.writeUnshared writes object everytime ven if no change in object
            out.writeUnshared(greetings);
            // oops , odia was incorrect
            //out.reset();
            greetings.put("Odia","Namaskar");
            //out.writeObject(greetings);
            out.writeUnshared(greetings);
            out.writeUnshared(new String[]{"Good Morning"});
            out.writeUnshared("Good Morning");
            //out.writeObject(null);//Best way to let know a file has ended
            //out.writeObject(FileTokens.EOF);
            out.writeUnshared(FileTokens.EOF);
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

            // Best wayR

            Object o;

            //while((o=in.readObject()) != null){

            while((o=in.readUnshared()) != FileTokens.EOF){
                System.out.println(o);
            }


        }

        try(ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bout.toByteArray())
        )){
            in.readUnshared(); //String
            Map<String,String> map1= (Map<String, String>) in.readUnshared();
            Map<String,String> map2= (Map<String, String>) in.readUnshared();
            String[] wrappedHello = (String[]) in.readUnshared();
            String unsharedHello = (String) in.readUnshared();
            System.out.println(map1.get("English") == map2.get("English"));//true
            System.out.println(map1.get("English") == wrappedHello[0]);//true
            System.out.println(map1.get("English") == unsharedHello);//false
        }
    }
}
