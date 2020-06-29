package playground;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokingMethodTest {
    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method toUpperCase = String.class.getMethod("toUpperCase");
        System.out.println(toUpperCase.invoke("hello students"));
    }
}
