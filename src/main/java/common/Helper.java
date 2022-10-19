package common;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static void removeOutputDir(String location, Configuration conf) throws IOException {
        Path path = new Path(location);
        FileSystem fs = path.getFileSystem(conf);

        if (fs.exists(path)) {
            fs.delete(path, true);
            System.out.println("Output Directory Removed!!!");
        }
    }

    // Returns list of ItemSet that contains item and the window for that item
    public static List<ItemSet> getItemSets(String line) {
        String[] parts = line.split("\\s+");
        List<ItemSet> itemSets = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            List<String> window = new ArrayList<>();
            for (int j = i + 1; j < parts.length; j++) {
                if (parts[j].equals(parts[i])) {
                    break;
                }
                window.add(parts[j]);
            }
            itemSets.add(new ItemSet(parts[i], window));
        }
        return itemSets;
    }
}
