package part2.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
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
