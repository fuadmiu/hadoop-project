package part1.wordcountinmapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text word = new Text();
    private HashMap<String, Integer> hashMap;
    private Logger logger = Logger.getLogger(WordCountInMapperMain.class);


    protected void setup(Context context) throws IOException, InterruptedException {
        hashMap = new HashMap<>();
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            logger.info(word);
            if (hashMap.containsKey(token)) {
                int count = hashMap.get(token);
                hashMap.put(token, count + 1);
            } else
                hashMap.put(token, 1);

        }
        //context.write(word, new IntWritable(count));
    }

    public void cleanup(Context context) throws IOException, InterruptedException {
        Iterator<Entry<String, Integer>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> entry = it.next();
            String sKey = entry.getKey();
            int total = entry.getValue();
            word.set(sKey);
            context.write(word, new IntWritable(total));
        }
    }
}
