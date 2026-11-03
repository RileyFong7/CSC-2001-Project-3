public record HuffmanLeaf (char charASCII, int freq) implements HuffmanTree{

    @Override
    public boolean comesBefore(HuffmanTree other) {
        switch (other) {
            case HuffmanNode o:
                if (this.freq == o.freq()) {
                    return this.charASCII < o.charAscii();
                } else {
                    return this.freq < o.freq();
                }
            case HuffmanLeaf o:
                if (this.freq == o.freq) {
                    return this.charASCII < o.charASCII;
                } else {
                    return this.freq < o.freq;
                }
            default:
                throw new IllegalArgumentException("input must be a HuffmanNode or a HuffmanLeaf");
        }
    }

    @Override
    public HuffmanTree combine(HuffmanTree other) {
        return switch (other) {
            case HuffmanNode o -> new HuffmanNode(this.charASCII, this.freq + o.freq(), this, o);
            case HuffmanLeaf o -> new HuffmanNode(o.charASCII(), this.freq + o.freq(), o, this);
            default -> throw new IllegalArgumentException("input must be a HuffmanNode or a HuffmanLeaf");
        };
    }

    @Override
    public int compareTo(HuffmanTree other) {
        if (this.comesBefore(other)) return -1;
        if (other.comesBefore(this)) return 1;
        return 0;
    }
}
