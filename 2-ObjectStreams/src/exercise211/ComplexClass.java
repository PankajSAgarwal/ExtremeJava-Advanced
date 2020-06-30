package exercise211;

import java.io.*;
import java.util.*;
/*
    original
    cpu=3586,user=3559,elapsed=3595,memory=972.1MB,bytes streamed=125.4MB

 */
public class ComplexClass implements Serializable {
    private final static long serialVersionUID = 2;// if the class is changed version id changes
    private int i;
    private long l;
    private String s;
    private boolean b;
    private Double d;
    private Float f;
    private Collection<Integer> v = new Vector<>();

    public ComplexClass(int i, long l, String s, boolean b, Double d, float f) {
        this.i = i;
        this.l = l;
        this.s = s;
        this.b = b;
        this.d = d;
        this.f = f;
    }

    public void add(int val) {
        v.add(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexClass that = (ComplexClass) o;

        if (b != that.b) return false;
        if (i != that.i) return false;
        if (l != that.l) return false;
        if (d != null ? !d.equals(that.d) : that.d != null) return false;
        if (f != null ? !f.equals(that.f) : that.f != null) return false;
        if (s != null ? !s.equals(that.s) : that.s != null) return false;
        if (v != null ? !v.equals(that.v) : that.v != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + (int) (l ^ (l >>> 32));
        result = 31 * result + (s != null ? s.hashCode() : 0);
        result = 31 * result + (b ? 1 : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (f != null ? f.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
        return result;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        out.writeInt(i);
        out.writeLong(l);
        out.writeBoolean(s!=null);
        if(s!=null) out.writeUTF(s);
        out.writeBoolean(b);
        out.writeBoolean(d != null);
        if(d != null) out.writeDouble(d);
        out.writeFloat(f);
        // serialize vector size
        out.writeInt(v.size());
        // serialize each element of the vector
        for (Integer el : v) {
              out.writeInt(el);
        }
        //Collection<Integer> v = new Vector<>();
    }

    private void readObject(ObjectInputStream in) throws IOException {
       i = in.readInt();
       l = in.readLong();
       if(in.readBoolean()) s = in.readUTF();
       b = in.readBoolean();
       if(in.readBoolean()) d = in.readDouble();
       f = in.readFloat();
       v = new Vector<>();
       int vsize = in.readInt();
        for (int j = 0; j < vsize ; j++) {
            v.add(in.readInt());
        }
    }
}
