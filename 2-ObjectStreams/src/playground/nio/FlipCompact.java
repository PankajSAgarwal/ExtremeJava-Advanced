package playground.nio;

import java.io.BufferedReader;
import java.nio.ByteBuffer;

public class FlipCompact {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        System.out.println("buffer.capacity() = " + buffer.capacity());
        buffer.put(new byte[300]);
        System.out.println("buffer.position() = " + buffer.position());//300
        System.out.println("buffer.limit() = " + buffer.limit());// 1000
        //get the buffer limit and from there move to position 0
        buffer.limit(buffer.position()).position(0);// convenience method buffer.flip()
        System.out.println("buffer.position() = " + buffer.position());//0
        System.out.println("buffer.limit() = " + buffer.limit());// 300

        buffer.get(new byte[200]); //
        System.out.println("buffer.position() = " + buffer.position());//200
        System.out.println("buffer.limit() = " + buffer.limit());//300
        buffer.compact();
        //buffer.position(buffer.limit());
        //buffer.limit(buffer.capacity());;
        System.out.println("buffer.position() = " + buffer.position());//100
        System.out.println("buffer.limit() = " + buffer.limit());//1000
        buffer.put(new byte[400]);
        System.out.println("buffer.position() = " + buffer.position());//500
        System.out.println("buffer.limit() = " + buffer.limit());//1000



    }
}
