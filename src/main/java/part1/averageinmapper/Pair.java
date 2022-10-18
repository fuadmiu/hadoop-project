package part1.averageinmapper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

public class Pair implements Writable {
    IntWritable total;
    IntWritable count;

    public Pair() {
        this.total = new IntWritable(0);
        this.count = new IntWritable(0);
    }

    public Pair(IntWritable total, IntWritable count) {
        this.total = total;
        this.count = count;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        total.write(out);
        count.write(out);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        total.readFields(in);
        count.readFields(in);
    }
}
