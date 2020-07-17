package exercise631;

public class OutOfRangeException extends IllegalArgumentException {
    private final int value;
    private final int min;
    private final int max;

    /**
     * value should be >= min and <max
     * [min,max)
     * @param value
     * @param min
     * @param max
     */
    public OutOfRangeException(int value, int min,int max) {
        super(String.format("out of range [%d,%d),value = ", min, max));

        this.value = value;
        this.min = min;
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
