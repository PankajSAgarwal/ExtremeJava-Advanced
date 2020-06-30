package playground.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndianTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(12);
        buffer.putInt(0x0000003b);
        buffer.putLong(0xfb7126b2d8d5669cL);

        buffer.flip();
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.printf("0x%08x / %1$d%n",buffer.getInt());//output 0x3b000000 / 989855744
        System.out.printf("0x%016x / %1$d%n",buffer.getLong());//output 0x9c66d5d8b22671fb / -7176813829489790469

        buffer.rewind();
        buffer.order(ByteOrder.BIG_ENDIAN);
        System.out.printf("0x%08x / %1$d%n",buffer.getInt());//output 0x0000003b / 59
        System.out.printf("0x%16x / %1$d%n",buffer.getLong());//output 0xfb7126b2d8d5669c / -328438748237437284


    }
}
