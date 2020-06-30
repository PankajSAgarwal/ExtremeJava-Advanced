package playground.nio;

import java.nio.ByteOrder;

public class EndianOrder {
    public static void main(String[] args) {
        //java.nio.ByteOrder.nativeOrder();
        // Output on a macbook pro - LITTLE_ENDIAN
        System.out.println("ByteOrder.nativeOrder() = " + ByteOrder.nativeOrder());
    }
}
