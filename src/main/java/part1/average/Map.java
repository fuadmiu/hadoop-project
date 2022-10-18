package part1.average;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

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
                context.write(new Text(ip), new IntWritable(count));
            }
        }
    }
}
