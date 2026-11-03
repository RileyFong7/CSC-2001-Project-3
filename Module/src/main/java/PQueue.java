public class PQueue {
    HuffmanNode[] heap;
    int size;
    public PQueue(HuffmanNode[] heap, int size){
        this.heap = heap;
        this.size = size;
    }
    public void enqueue(HuffmanNode h){
        heap[size] = h;
        sift(size);
        size += 1;
    }
    public HuffmanNode dequeue(){
        return null;
    }
    public static PQueue empty_queue(){
        return new PQueue(new HuffmanNode[7], 0);
    }
    public HuffmanNode peek(){
        if (heap[0] != null){
            return heap[0];
        }else{
            throw new IllegalArgumentException("peek is null");
        }

    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void sift(int index){
        if (heap[(index-1)/2].freq() > heap[index].freq()){
            swap(index, (index-1)/2);
            sift((index-1)/2);
        }
    }
    public void swap(int index1, int index2){
        HuffmanNode temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
    public void main(){
        PQueue example1 = empty_queue();
    }
}
