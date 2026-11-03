public record LeafPath(int value, String path, LeafPath rest) {
    public static LeafPath append(LeafPath a, LeafPath b) {
        switch (a) {
            case null:
                return b;
            case LeafPath(int v, String p, LeafPath r):
                return new LeafPath(v, p, append(r, b));
        }
    }
}
