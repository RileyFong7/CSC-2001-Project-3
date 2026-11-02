public class AQueue {
    String[] array;
    int size;
    int mod;
    public AQueue(String[] array, int size){
        this.array = array;
        this.size = size;
        this.mod = 0;
    }
    public boolean equalElts(AQueue arr1){
        if (this.size != arr1.size){
            return false;
        }
        for (int i=0; i < arr1.size; i++){
            if (!(arr1.array[(i+arr1.mod)%arr1.array.length].equals(this.array[(i+this.mod)%this.array.length]))){
                return false;
            }
        }
        return true;
    }
    public boolean equals(Object o){
        switch (o) {
            case AQueue arr1:
                if ((arr1.array.length == this.array.length) && equalElts(arr1)) {
                    return true;
                } else {
                    return false;
                }
            case null, default:
                return false;
        }
    }
    public static AQueue empty_queue(){
        return new AQueue(new String[3], 0);
    }
    public void enqueue(String string){
        if (this.size >= this.array.length){
            grow();
        }
        this.array[(this.size + mod)%array.length] = string;
        this.size += 1;

    }
    public String dequeue(){
        if (size == 0){
            throw new ArrayIndexOutOfBoundsException("No items in queue.");
        }
        size -=1;
        mod = (mod + 1) % array.length;
        return array[mod -1];
    }
    public String peek(){
        return array[mod];
    }
    public int size(){
        return size;
    }
    public boolean is_empty(){
        return size == 0;
    }
    public void grow(){
        String[] newArray = new String[array.length*2];
        for (int i=0; i < array.length; i++){
            newArray[i] = array[(i+mod)% array.length];
        }
        this.array = newArray;
        this.mod = 0;
    }
    public String toString() {
        for (int i=0; i < this.size; i++){
            IO.print(array[(i+mod)%array.length] + " - ");
        }
        return "";
    }
    public static void main(){
        AQueue example1 = empty_queue();
        example1.enqueue("1");
        example1.enqueue("2");
        example1.enqueue("3");
        example1.enqueue("4");
        IO.println(example1);
        IO.println(example1.dequeue());
        //IO.println(example1.peek());
        example1.enqueue("5");
        AQueue example2 = empty_queue();
        example2.enqueue("2");
        example2.enqueue("3");
        example2.enqueue("4");
        example2.enqueue("5");
        IO.println(example1.equals(example2));
        example2.enqueue("6");
        IO.println(example1.equals(example2));
    }
}