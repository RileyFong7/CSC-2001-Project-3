import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AQueueTest {
    AQueue<String> example1 = AQueue.empty_queue();
    AQueue<String> example2 = AQueue.empty_queue();

    @Test
    void Aenqueue() {
        example1.enqueue("1");
        example1.enqueue("2");
        example1.enqueue("3");

        assertTrue(new AQueue<String>(new String[]{"1", "2", "3"}, 3).equalElts(example1));
    }
    @Test
    void Adequeue(){
        example1.enqueue("1");
        example1.enqueue("2");
        example1.enqueue("3");
        example1.dequeue();
        assertTrue(new AQueue(new String[]{"2", "3"}, 2).equalElts(example1));
    }
    @Test
    void Apeek(){
        example1.enqueue("1");
        example1.enqueue("2");
        example1.enqueue("3");
        assertEquals("1", example1.peek());
    }
    @Test
    void Asize(){
        example1.enqueue("1");
        example1.enqueue("2");
        example1.enqueue("3");
        assertEquals(3, example1.size());
    }
}