public class ArrayStack<T> {
    private Object[] array;
    private int size;

    public ArrayStack() {
        this.array = new Object[1];
        this.size = 0;
    }
    public ArrayStack(Object[] array) {
        this.array = array;
        this.size = array.length;
    }

    /** returns whether or not this stack and the inputted stack are equal */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        switch (o) {
            case ArrayStack other:
                if (this.size != other.size) {
                    return false;
                }

                for (int i = 0; i < this.size; i++) {
                    if (!this.array[i].equals(other.array[i])) {
                        return false;
                    }
                }
                return true;
            case null, default:
                return false;
        }

    }

    /** creates a new internal array with size doubled the original array */
    private void newArray() {
        Object[] newArray = new Object[size * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
    }

    /** returns an empty stack */
    public static <T> ArrayStack<T> emptyStack() {
        return new ArrayStack<T>();
    }

    /** adds another element to the stack*/
    public void push(T v) {
        if (size == array.length) {
            newArray();
        }
        array[size] = v;
        size++;
    }

    /** removes an element from the top of the stack and returns it */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("stack is empty");
        }
        size--;
        return (T) array[size];
    }

    /** returns the element at the top of the stack */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("stack is empty");
        } else {
            return (T) array[size - 1];
        }
    }

    /** returns the size of the stack */
    public int size() {
        return size;
    }

    /** returns whether or not the stack is empty */
    public boolean isEmpty() {
        return size == 0;
    }
}
