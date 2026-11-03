public class PQueue implements HQueue{
    HuffmanNode[] heap;
    int size;
    public PQueue(HuffmanNode[] heap, int size){
        this.heap = heap;
        this.size = size;
    }
    public void enqueue(HuffmanNode h){
        heap[size] = h;
        siftup(size);
        size += 1;
    }
    public HuffmanTree dequeue(){
        int index = 0;
        HuffmanNode dead = heap[0];
        if (size <= 0){
            throw new IllegalArgumentException("empty dequeue");
        }
        while (index < size){
            if(heap[(index*2)+1].freq() < heap[(index*2)+2].freq()){
                heap[index] = heap[(index*2)+1];
                index = index*2+1;
            }
            else{
                heap[index] = heap[(index*2)+2];
                index = index*2+2;
            }
        }
        size -= 1;
        return (HuffmanTree) dead;
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
    public void siftup(int index){
        if (heap[(index-1)/2].freq() > heap[index].freq()){
            swap(index, (index-1)/2);
            siftup((index-1)/2);
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
