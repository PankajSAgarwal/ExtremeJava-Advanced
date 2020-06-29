package exercise422;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ArraysWithReflectionMagic {
    private static final Method cloneMethod;
    static{
        try {
            cloneMethod = Object.class.getDeclaredMethod("clone");
            cloneMethod.setAccessible(true);
        } catch (ReflectiveOperationException e) {
           throw new ExceptionInInitializerError(e);
        }
    }
    public static <A> A deepClone(A source) {
        // Returns a deep clone of the array source, but does not clone the
        // actual elements of the array (unless they are also arrays)
        //
        // If the source is not an array, simply return it
        // First-solution - best solution
        /*if (source == null || !source.getClass().isArray()) return source;
        int length = Array.getLength(source);
        A clone = (A) Array.newInstance(source.getClass().getComponentType(),length);
        for (int i = 0; i < length; i++) {
            Array.set(clone,i,deepClone(Array.get(source,i)));

        }*/
        // second solution

        if(source==null || !source.getClass().isArray()) return source;
        int length = Array.getLength(source);
        A clone = clone0(source);
        for (int i = 0; i < length ; i++) {
            Array.set(clone,i,deepClone(Array.get(clone,i)));
        }

        return clone;
    }

    // second solution
    /*private static <A> A clone0(A source) {
        assert source != null;
        if(source instanceof int[]){
            return (A) ((int[])source).clone();
        }else if(source instanceof long[]){
            return (A) ((long[])source).clone();
        }else if(source instanceof char[]){
            return (A) ((char[])source).clone();
        }else if(source instanceof byte[]){
            return (A) ((byte[])source).clone();
        }else if(source instanceof float[]){
            return (A) ((float[])source).clone();
        }else if(source instanceof double[]){
            return (A) ((double[])source).clone();
        }else{
            return (A) ((Object[])source).clone();
        }
    }*/

    // third solution
    private static <A> A clone0(A source) {
        assert source != null;
        try {
            return (A) cloneMethod.invoke(source);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }



}
