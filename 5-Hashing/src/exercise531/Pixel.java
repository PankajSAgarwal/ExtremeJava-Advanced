package exercise531;

import java.util.*;

public class Pixel implements Comparable<Pixel>{
    private final int x, y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Pixel pixel = (Pixel) o;
        // Objects.equals useful only when objects can be null,
        // but in this case fields are primitive
        /*return Objects.equals(x,pixel.x) &&
                Objects.equals(y,pixel.y);*/
        return x == pixel.x && y == pixel.y;
    }


    @Override
    public int hashCode() {
        //return x * 2880 + y;// 16s without comparable,19s with comparable
        //return x << 16 ^ y; // this passes test cases  gives a performance of 32 sec without comparable interface
        return x << 16 ^ y;// 30 s with comparable interface
        //return Objects.hash(x, y);

    }

    @Override
    public int compareTo(Pixel other) {
        int result = Integer.compare(x,other.x);
        if(result == 0){
            result = Integer.compare(y,other.y);
        }
        return result;
    }
}
