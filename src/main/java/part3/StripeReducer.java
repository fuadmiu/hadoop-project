package part3;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import part3.util.PrintableMapWritable;

import java.io.IOException;

public class StripeReducer extends Reducer<Text, PrintableMapWritable, Text, PrintableMapWritable> {
    public void reduce(Text key, Iterable<PrintableMapWritable> values, Context context) throws IOException, InterruptedException {
        PrintableMapWritable finalMap = new PrintableMapWritable();
        for (PrintableMapWritable map : values) {
            for (PrintableMapWritable.Entry entry : map.entrySet()) {
                Text k = (Text) entry.getKey();
                IntWritable v = (IntWritable) entry.getValue();
                if (finalMap.containsKey(k)) {
                    IntWritable existingVal = (IntWritable) finalMap.get(k);
                    v = new IntWritable(existingVal.get() + v.get());
                }
                finalMap.put(k, v);
            }
        }

        int totalSum = getTotalSum(finalMap);

        for (PrintableMapWritable.Entry entry : finalMap.entrySet()) {
            Text k = (Text) entry.getKey();
            IntWritable v = (IntWritable) entry.getValue();
            DoubleWritable frequency = new DoubleWritable((double) v.get() / totalSum);
            finalMap.put(k, frequency);
        }
        context.write(key, finalMap);
    }

    private int getTotalSum(PrintableMapWritable finalMap) {
        int totalSum = 0;
        for (PrintableMapWritable.Entry entry : finalMap.entrySet()) {
            IntWritable v = (IntWritable) entry.getValue();
            totalSum += v.get();
        }
        return totalSum;
    }
}
