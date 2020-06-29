package playground;

import java.lang.reflect.Field;

//1. Final fields cannot be re-assigned - Reflection may allow us to
// change them in some versions of java
//2. If they are bound at compile time , they will get inlined
//3. Static final fields cannot be changed with reflection


public class PrivateFinalFieldTest {
    public static void main(String[] args)
            throws NoSuchFieldException, IllegalAccessException {
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set("hello!","cheers".toCharArray());
        // this will not work in java 9 as
        // we don't have charArray inside the field but byteArray
        System.out.println("hello!");//output : cheers

    }
}
