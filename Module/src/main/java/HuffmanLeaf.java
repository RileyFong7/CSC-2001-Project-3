public record HuffmanLeaf (int freq, char charASCII) implements HuffmanTree{

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
        int freqSum = this.freq + other.freq();
        char lowestChar;
        switch (other) {
            case HuffmanNode o:
                if (this.charASCII < o.charAscii()) {
                    lowestChar = this.charASCII;
                } else {
                    lowestChar = (char) o.charAscii();
                }
                break;
            case HuffmanLeaf o:
                if (this.charASCII < o.charASCII) {
                    lowestChar = this.charASCII;
                } else {
                    lowestChar = o.charASCII;
                }
                break;
            default:
                throw new IllegalArgumentException("input must be a HuffmanNode or a HuffmanLeaf");
        }
        if (this.comesBefore(other)) {
            return new HuffmanNode(lowestChar, freqSum, this, other);
        } else {
            return new HuffmanNode(lowestChar, freqSum, other, this);
        }
    }

    @Override
    public int compareTo(HuffmanTree other) {
        if (this.comesBefore(other)) return -1;
        if (other.comesBefore(this)) return 1;
        return 0;
    }
}
