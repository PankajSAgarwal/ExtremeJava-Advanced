package playground;

import java.lang.reflect.Method;

public class MethodTest {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println("### Integer accessible methods ###");
        for(Method method:Integer.class.getMethods()){
            System.out.println("\t" + method);
        }

        System.out.println("### Integer all methods ###");
        for(Method method:Integer.class.getDeclaredMethods()){
            System.out.println("\t" + method);
        }

        System.out.println("String private method value");
        Method method = String.class.getMethod("valueOf", int.class);
        System.out.println("\t" + method);

    }
}
