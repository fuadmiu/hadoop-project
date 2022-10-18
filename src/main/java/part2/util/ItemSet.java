package part2.util;

import java.util.List;

public class ItemSet {
    private String u;
    private List<String> window;

    public ItemSet(String u, List<String> window) {
        this.u = u;
        this.window = window;
    }

    public String getU() {
        return u;
    }

    public List<String> getWindow() {
        return window;
    }
}
