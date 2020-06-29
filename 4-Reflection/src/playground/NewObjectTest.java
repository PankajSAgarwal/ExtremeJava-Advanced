package playground;

import java.util.ArrayList;
import java.util.Collection;

public class NewObjectTest {
    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException {
        //Using newInstance can be dangerous :
        // checked exception can be thrown from constructor
        // see the class NewObjectWithParametersTest: first get the constructor
        // and then use it with newInstance
        Collection names = ArrayList.class.newInstance();
        names.add("Sanu");
        names.add("Bagul");
        names.add("Pankaj");
        System.out.println(names);

    }
}
