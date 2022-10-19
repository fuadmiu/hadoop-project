package part3;

import common.Helper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import part3.util.PrintableMapWritable;

public class StripeMain {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        // Check for existing output, remove if exists
        Helper.removeOutputDir(args[1], conf);

        Job job = new Job(conf, "Stripe Algorithm for Relative Frequency");
        job.setJarByClass(StripeMain.class);

        job.setMapperClass(StripeMapper.class);
        job.setReducerClass(StripeReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PrintableMapWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PrintableMapWritable.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
