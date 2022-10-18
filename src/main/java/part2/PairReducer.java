package part2;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import part2.util.Pair;

import java.io.IOException;

public class PairReducer extends Reducer<Pair, IntWritable, Pair, DoubleWritable> {
    private final DoubleWritable outputValue = new DoubleWritable();
    private Double sum;

    @Override
    protected void setup(Reducer<Pair, IntWritable, Pair, DoubleWritable>.Context context) throws IOException, InterruptedException {
        this.sum = 0.0;
    }

    public void reduce(Pair key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        double partialSum = 0;
        for (IntWritable val : values) {
            partialSum += val.get();
        }
        if(key.getY().equals("*")) {
            this.sum = partialSum;
        } else {
            outputValue.set(partialSum / this.sum);
            context.write(key, outputValue);
        }
    }
}
