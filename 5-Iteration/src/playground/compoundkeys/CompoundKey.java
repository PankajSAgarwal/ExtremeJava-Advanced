package playground.compoundkeys;

import java.util.Objects;

public class CompoundKey {
    private final String s;
    private final int i;

    public CompoundKey(String s, int i) {
        this.s = s;
        this.i = i;
    }

    public boolean equals(Object o){
        if ( this == o) return true;
        if(o == null || getClass() != o.getClass())
            return false;
        CompoundKey that = (CompoundKey) o;
        return Objects.equals(i,that.i) && Objects.equals(s,that.s);
    }

    public int hashCode(){
        return Objects.hash(s,i);
    }
}
