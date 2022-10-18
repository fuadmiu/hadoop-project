package part1.averageinmapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, Pair> {
    private Text word = new Text();
    private HashMap<String, Pair> hashMap;

    protected void setup(Context context) throws IOException, InterruptedException {
        hashMap = new HashMap<>();
    }

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Creating a regular expression for the records
        final String filterRegex = "^(\\S+) (\\S+) (\\S+) " +
                "\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+)" +
                " (\\S+)\\s*(\\S+)?\\s*\" (\\d{3}) (\\S+)";

        String zeroTo255 = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";

        String ipRegex = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;

        // Compile the ReGex
        Pattern p = Pattern.compile(filterRegex);
        final Pattern pattern = Pattern.compile(filterRegex);
        final Matcher matcher = pattern.matcher(value.toString());

        while (matcher.find()) {
            String ip = matcher.group(1);
            Pattern ipPattern = Pattern.compile(ipRegex);
            Matcher m = ipPattern.matcher(ip);

            int count = 0;
            try {
                // checking valid integer using parseInt()
                count = Integer.parseInt(matcher.group(9));
            } catch (NumberFormatException e) {
                count = -1;
            }

            if (m.matches() && count != -1) {
                if (hashMap.containsKey(ip)) {
                    Pair pair = hashMap.get(ip);
                    pair.count = new IntWritable(pair.count.get() + 1);
                    pair.total = new IntWritable(pair.total.get() + count);
                    hashMap.put(ip, pair);
                } else {
                    hashMap.put(ip, new Pair(new IntWritable(count), new IntWritable(1)));
                }                
            }

        }
    }

    public void cleanup(Context context) throws IOException, InterruptedException {
        Iterator<Entry<String, Pair>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Pair> entry = it.next();
            String sKey = entry.getKey();
            Pair pair = entry.getValue();
            word.set(sKey);
            context.write(word, pair);
        }
    }
}
