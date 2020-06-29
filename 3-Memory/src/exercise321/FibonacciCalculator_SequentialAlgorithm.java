package exercise321;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

//This uses sequential algorithm so stack overflow exception doesn't
// happen for large values
public class FibonacciCalculator_SequentialAlgorithm {
    public String f(int n) {
        if (n < 0)
            throw new IllegalArgumentException("" + n);
        BigInteger n0 = BigInteger.ZERO;
        BigInteger n1 = BigInteger.ONE;
        BigInteger temp;

        for (int i = 0; i < n ; i++) {
            temp = n1;
            n1 = n0.add(n1);
            n0 = temp;
        }
        return n0.toString();
    }

    public static void main(String[] args) {
        FibonacciCalculator_SequentialAlgorithm fib = new FibonacciCalculator_SequentialAlgorithm();
        for (int i = 0; i < 10 ; i++) {
            System.out.println(fib.f(i));
        }
        System.out.println(fib.f(10_000));
    }
}
