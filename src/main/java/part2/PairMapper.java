package part2;

import common.Helper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import common.ItemSet;
import part2.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairMapper extends Mapper<LongWritable, Text, Pair, IntWritable> {
    private Map<Pair, Integer> map;

    @Override
    protected void setup(Context context) {
        this.map = new HashMap<>();
    }

    @Override
    public void map(LongWritable lineOffset, Text line, Context context) {
        List<ItemSet> itemSets = Helper.getItemSets(line.toString());
        for(ItemSet itemSet: itemSets) {
            for(String item : itemSet.getWindow()) {
                aggregate(itemSet.getU(), item);
                aggregate(itemSet.getU(), "*");
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Map.Entry<Pair, Integer> entry : map.entrySet()) {
            context.write(entry.getKey(), new IntWritable(entry.getValue()));
        }
    }

    private void aggregate(String key, String value) {
        Pair pair = new Pair(key, value);
        if (map.containsKey(pair)) {
            map.put(pair, map.get(pair) + 1);
        } else {
            map.put(pair, 1);
        }
    }
}
