package part3;

import common.Helper;
import common.ItemSet;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import part3.util.PrintableMapWritable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StripeMapper extends Mapper<LongWritable, Text, Text, PrintableMapWritable> {
    private Map<Text, PrintableMapWritable> mainMap;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        this.mainMap = new HashMap<>();
    }

    @SuppressWarnings("rawtypes")
    public void map(LongWritable lineOffset, Text line, Context context) throws IOException, InterruptedException {
        List<ItemSet> itemSets = Helper.getItemSets(line.toString());
        for (ItemSet itemSet : itemSets) {
            PrintableMapWritable map = new PrintableMapWritable();
            populateMap(itemSet, map);

            Text key = new Text(itemSet.getU());
            if (mainMap.containsKey(key)) {
                for (PrintableMapWritable.Entry entry : map.entrySet()) {
                    updateExistingMap(key, entry);
                }
            } else {
                mainMap.put(key, map);
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Map.Entry<Text, PrintableMapWritable> entry : mainMap.entrySet()) {
            context.write(entry.getKey(), entry.getValue());
        }
    }

    @SuppressWarnings("rawtypes")
    private void updateExistingMap(Text key, Map.Entry entry) {
        PrintableMapWritable existingMap = mainMap.get(key);
        Text k = (Text) entry.getKey();
        IntWritable v = (IntWritable) entry.getValue();
        if (existingMap.containsKey(k)) {
            IntWritable newValue = new IntWritable(((IntWritable) existingMap.get(k)).get() + v.get());
            existingMap.put(k, newValue);
        } else {
            existingMap.put(k, v);
        }
    }

    private void populateMap(ItemSet itemSet, PrintableMapWritable map) {
        IntWritable one = new IntWritable(1);
        for (String item : itemSet.getWindow()) {
            Text k = new Text(item);
            if (map.containsKey(k)) {
                map.put(k, new IntWritable(((IntWritable) map.get(k)).get() + one.get()));
            } else {
                map.put(k, one);
            }
        }
    }
}
