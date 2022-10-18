package part1.averageinmapper;

import common.Helper;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class LogAverageInMapperMain {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        // Check for existing output, remove if exists
        Helper.removeOutputDir(args[1], conf);

        Job job = new Job(conf, "InMapperwordcount");
        job.setJarByClass(LogAverageInMapperMain.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Pair.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }

}
