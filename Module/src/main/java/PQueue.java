public class PQueue implements HQueue{
    HuffmanTree[] heap;
    int size;
    public PQueue(HuffmanTree[] heap, int size){
        this.heap = heap;
        this.size = size;
    }
    public void enqueue(HuffmanTree h){
        if (size == heap.length) {
            newArray();
        }
        heap[size] = h;
        siftup(size);
        size += 1;
    }
    private void newArray() {
        HuffmanTree[] newArray = new HuffmanTree[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = this.heap[i];
        }
        this.heap = newArray;
    }
    public HuffmanTree dequeue(){
        int index = 0;
        HuffmanTree dead = heap[0];
        if (size <= 0){
            throw new IllegalArgumentException("empty dequeue");
        }
        swap(0, size - 1);
        while (((index*2)+1) < size -1) {
            int smallerIndex;
            if ((index * 2) + 2 < size - 1) {
                if(heap[(index*2)+1].comesBefore(heap[(index*2)+2])) {
                    smallerIndex = (index * 2) + 1;
                } else {
                    smallerIndex = (index * 2) + 2;
                }
            } else {
                smallerIndex = (index * 2) + 1;
            }
            if (heap[smallerIndex].comesBefore(heap[index])) {
                swap(index, smallerIndex);
                index = smallerIndex;
            } else {
                break;
            }
        }
        heap[size - 1] = null;
        size--;
        return dead;
    }
    public static PQueue empty_queue(){
        return new PQueue(new HuffmanTree[7], 0);
    }
    public HuffmanTree peek(){
        if (heap[0] != null){
            return heap[0];
        }else{
            throw new IllegalArgumentException("peek is null");
        }

    }
    public int count(){
        return this.size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void siftup(int index){
        if (heap[index].comesBefore(heap[(index-1)/2])){
            swap(index, ((index-1)/2));
            siftup((index-1)/2);
        }
    }

    public void swap(int index1, int index2){
        HuffmanTree temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    @Override
    public String toString() {
        for (int i=0; i < size; i++){
            IO.print(heap[i].freq() + " - ");
        }
        return "";
    }

}
