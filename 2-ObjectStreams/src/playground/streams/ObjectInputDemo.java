package playground.streams;

import java.io.*;

public class ObjectInputDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try(
                FileInputStream fis = new FileInputStream("objects.bin");
                ObjectInputStream in = new ObjectInputStream(
                        new BufferedInputStream(fis)
                )
                ){
                    Object obj;
                    while ((obj = in.readObject()) != null){
                        System.out.println(obj);
                    }
        }
    }
}
