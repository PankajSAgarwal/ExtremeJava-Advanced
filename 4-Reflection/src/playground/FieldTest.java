package playground;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.reflect.Field;

public class FieldTest {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println("### Integer accessible fields ###");
        for(Field field:Integer.class.getFields()){
            System.out.println("\t" + field);
        }
        System.out.println("### Integer all fields ###");
        for (Field field:Integer.class.getDeclaredFields()){
            System.out.println("\t" + field);
        }
        System.out.println("### String private field value ###");
        Field value = String.class.getDeclaredField("value");
        System.out.println("\t" + value);

    }
}
