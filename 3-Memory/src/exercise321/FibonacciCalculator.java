package exercise321;

import java.math.*;

public class FibonacciCalculator {
    /*public String f(int n) {
        if (n < 0)
            throw new IllegalArgumentException("" + n);
        if (n <= 1)
            return "" + n;
        BigInteger b1 = new BigInteger(f(n - 1));
        BigInteger b2 = new BigInteger(f(n - 2));
        return b1.add(b2).toString();
    }*/
    public String f(int n) {
        if (n < 0)
            throw new IllegalArgumentException("" + n);
        return "" + f_long(n);
    }

    //1st Iteration-reduces averageCreationRate
    private BigInteger f_bi(int n) {

        if(n==0) return BigInteger.ZERO;
        if(n==1) return BigInteger.ONE;
        return f_bi(n - 1).add(f_bi(n - 2));
    }

    // Iteration2 - reduces averageCreationRate but time complexity is bad
    private long f_long(int n) {
        if(n<=1) return n;
        return f_long(n - 1)+ f_long(n - 2);
    }
}
