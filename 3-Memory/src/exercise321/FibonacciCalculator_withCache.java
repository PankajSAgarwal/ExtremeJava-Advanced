package exercise321;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
// This can have problem with stack space for large value e.g 10000
// As this is a recursive algorithm but complexity is not logarithmic
public class FibonacciCalculator_withCache {
    // this should be avoided as causes memory leak and not thread safe
    //private final Map<Integer,String> cache = new HashMap<>();
    public String f(int n) {
        if (n < 0)
            throw new IllegalArgumentException("" + n);

        return f(n,new HashMap<>());
    }

    // cache is local so will get cleared out and will not cause memory leak
    private String f(int n,Map<Integer,String> cache) {
        if (n <= 1)
            return "" + n;
        String result = cache.get(n);
        if(result == null){
            cache.put(n,result = new BigInteger(f(n-1,cache))
                    .add(new BigInteger(f(n-2,cache))).toString());
        }

        return result;
    }

    public static void main(String[] args) {
        FibonacciCalculator_withCache fib = new FibonacciCalculator_withCache();
        System.out.println(fib.f(1000));// this works
        //System.out.println(fib.f(10_000));// this gives stack overflow

    }

}
