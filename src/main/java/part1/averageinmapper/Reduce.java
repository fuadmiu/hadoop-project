package part1.averageinmapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, Pair, Text, DoubleWritable> {

    public void reduce(Text key, Iterable<Pair> values, Context context) throws IOException, InterruptedException {
        int count = 0, total = 0;
        for (Pair val : values) {
            count += val.count.get();
            total += val.total.get();
        }
        context.write(key, new DoubleWritable(((double)total / count)));
    }
}
