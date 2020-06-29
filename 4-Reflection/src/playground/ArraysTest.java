package playground;

public class ArraysTest {
    public static void showComponent(Class<?> c){
        Class<?> comp = c.getComponentType();
        System.out.println(comp);
        System.out.println(comp.getSimpleName());

    }
    public static void main(String[] args) {
        //output
        //char
        //char
        showComponent(char[].class);
        //output
        //class [[Ljava.lang.Integer;
        //Integer[][]
        showComponent(Integer[][][].class);
    }
}
