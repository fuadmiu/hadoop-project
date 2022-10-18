package common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class Helper {
    public static void removeOutputDir(String location, Configuration conf) throws IOException {
        Path path = new Path(location);
        FileSystem fs = path.getFileSystem(conf);

        if (fs.exists(path)) {
            fs.delete(path, true);
            System.out.println("Output Directory Removed!!!");
        }
    }
}
