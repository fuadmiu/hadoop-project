package part3.util;

import org.apache.hadoop.io.MapWritable;

import java.util.ArrayList;
import java.util.List;

public class PrintableMapWritable extends MapWritable {
    @Override
    public String toString() {
        List<String> printList = new ArrayList<>();
        for (MapWritable.Entry entry : this.entrySet()) {
            printList.add(entry.toString());
        }
        return printList.toString();
    }
}
