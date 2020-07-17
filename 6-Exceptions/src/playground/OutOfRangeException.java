package playground;

public class OutOfRangeException extends IllegalArgumentException {
    private final long lower,upper,value;


    public OutOfRangeException(long lower, long upper, long value) {
        super("Out Of range :" + value + ", valid range "+
                "is [" + lower + "," + upper +"]");

        this.lower = lower;
        this.upper = upper;
        this.value = value;
    }

    public long getLower() {
        return lower;
    }

    public long getUpper() {
        return upper;
    }

    public long getValue() {
        return value;
    }
}
