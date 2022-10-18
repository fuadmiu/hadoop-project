package part2.util;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pair implements WritableComparable<Pair> {
    private String x;
    private String y;

    public Pair(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public static Pair read(DataInput in) throws IOException {
        Pair pair = new Pair();
        pair.readFields(in);
        return pair;
    }

    @Override
    public int compareTo(Pair o) {
        int k = this.getX().compareTo(o.getX());
        if(k != 0) return k;
        return this.getY().compareTo(o.getY());
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(x);
        out.writeUTF(y);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        x = in.readUTF();
        y = in.readUTF();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
