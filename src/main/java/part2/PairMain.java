package part2;

import common.Helper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import part2.util.Pair;

public class PairMain {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        // Check for existing output, remove if exists
        Helper.removeOutputDir(args[1], conf);

        Job job = new Job(conf, "Pair Algorithm for Relative Frequency");
        job.setJarByClass(PairMain.class);

        job.setMapperClass(PairMapper.class);
        job.setReducerClass(PairReducer.class);

        job.setMapOutputKeyClass(Pair.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Pair.class);
        job.setOutputValueClass(DoubleWritable.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
