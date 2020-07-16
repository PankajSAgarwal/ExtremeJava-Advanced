package playground;

import java.util.Objects;

public class CompoundKey {
    private final String s;
    private final int i;

    public CompoundKey(String s, int i) {
        this.s = s;
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompoundKey that = (CompoundKey) o;
        return i == that.i &&
                Objects.equals(s, that.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, i);
    }
}
