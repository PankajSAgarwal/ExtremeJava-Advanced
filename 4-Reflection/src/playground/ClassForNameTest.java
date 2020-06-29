package playground;

import java.lang.reflect.Field;

public class ClassForNameTest {
    public static void main(String[] args) {
        for(String class_name:args){
            try {
                //Better practice to use Class.forName along with classloader
                // when your class is expected to be used in other frameworks of Application server
                Class <?> c = Class.forName(class_name);
                System.out.println(c.getSimpleName());
                for (Field field : c.getDeclaredFields()) {
                    System.out.println(field);

                }
            } catch (ClassNotFoundException e) {
                System.err.println("No class " + class_name);
            }
        }
    }
    // output: Run the program with program argument java.util.ArrayList
}
