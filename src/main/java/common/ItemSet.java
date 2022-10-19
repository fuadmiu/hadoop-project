package common;

import java.util.List;

public class ItemSet {
    private final String u;
    private final List<String> window;

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
