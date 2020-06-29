package playground;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NewObjectWithParametersTest {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        
        // getDeclaredConstructor gets all public and private constructor of an object
        Constructor<String> newString = String.class.getDeclaredConstructor(String.class);
        String pankaj = newString.newInstance("Pankaj");
        System.out.println("name = " + pankaj);

    }
}
