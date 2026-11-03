/**
 * A single node in a Huffman tree, implemented as a record.
 *
 * A HuffmanNode represents either a leaf (an actual character together
 * with its occurrence count) or an internal node created by combining two
 * subtrees during tree construction.
 *
 * Records are immutable and give you charAscii(), freq(), left(), and
 * right() accessor methods automatically, along with
 * equals()/hashCode()/toString(). Do not rename the components below.
 */
public record HuffmanNode(int charAscii, int freq, HuffmanTree left, HuffmanTree right)
        implements HuffmanTree {

    /** Convenience constructor for a leaf node (no children). */
    public HuffmanNode(int charAscii, int freq) {
        this(charAscii, freq, null, null);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Returns true if the tree rooted at node a comes before the tree
     * rooted at node b, false otherwise.
     *
     * A node "comes before" another if its frequency count is smaller.
     * Ties are broken using the ASCII value of the character: the node
     * with the smaller ASCII value comes first.
     */
    public boolean comesBefore(HuffmanTree other) {
        switch (other) {
            case HuffmanNode o:
                if (this.freq == o.freq) {
                    return this.charAscii < o.charAscii;
                } else {
                    return this.freq < o.freq;
                }
            case HuffmanLeaf o:
                if (this.freq == o.freq()) {
                    return this.charAscii < o.charASCII();
                } else {
                    return this.freq < o.freq();
                }
            default:
                throw new IllegalArgumentException("input must be a HuffmanNode or a HuffmanLeaf");
        }
    }

    /**
     * Creates and returns a new internal HuffmanNode with a and b as its
     * two children.
     *
     * The child for which comesBefore() is true goes on the left, the
     * other goes on the right. The new node's frequency is the sum of a's
     * and b's frequencies. The new node's charAscii value is the smaller
     * of a's and b's charAscii values (used to break ties later on).
     */
    @Override
    public HuffmanTree combine(HuffmanTree other) {
        int freqSum = this.freq + other.freq();
        char lowestChar;
        switch (other) {
            case HuffmanNode o:
                if (this.charAscii < o.charAscii()) {
                    lowestChar = (char) this.charAscii;
                } else {
                    lowestChar = (char) o.charAscii;
                }
                break;
            case HuffmanLeaf o:
                if (this.charAscii < o.charASCII()) {
                    lowestChar = (char) this.charAscii;
                } else {
                    lowestChar = o.charASCII();
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

    /**
     * Natural ordering consistent with comesBefore(), so that HuffmanNode
     * objects can be stored directly in a sorted structure (e.g. a
     * PriorityQueue or a sorted List) while building the tree.
     */
    @Override
    public int compareTo(HuffmanTree other) {
        if (this.comesBefore(other)) return -1;
        if (other.comesBefore(this)) return 1;
        return 0;
    }
}
