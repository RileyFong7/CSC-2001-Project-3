import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Starter test suite for Project 3 (Huffman Encoding/Decoding).
 * Write more tests!
 * These tests assume the test files are in the same directory as HuffmanTest.java
 */
public class HuffmanTest {

    @Test
    public void testCntFreq() throws IOException {
        int[] freqList = Huffman.cntFreq("file2.txt");
        int[] ansList = {2, 4, 8, 16, 0, 2, 0}; // counts for ascii 97..103
        for (int i = 0; i < ansList.length; i++) {
            assertEquals(ansList[i], freqList[97 + i], "mismatch at ascii " + (97 + i));
        }
    }

    @Test
    public void testComesBefore() {
        HuffmanLeaf d = new HuffmanLeaf(5, 'd');
        HuffmanLeaf k = new HuffmanLeaf(5, 'k');
        assertTrue(d.comesBefore(k));
        assertFalse(k.comesBefore(d));
    }

    @Test
    public void testCreateHeader() throws IOException {
        int[] freqList = Huffman.cntFreq("file2.txt");
        assertEquals("97 2 98 4 99 8 100 16 102 2", Huffman.createHeader(freqList));
    }
    @Test
    public void testCreateHeader2() throws IOException {
        int[] freqList = Huffman.cntFreq("a.txt");
        assertEquals("97 5", Huffman.createHeader(freqList));
    }
    @Test
    public void testCreateHeaderEmpty() throws IOException {
        int[] freqList = Huffman.cntFreq("empty");
        assertEquals("", Huffman.createHeader(freqList));
    }
    @Test
    public void testCreateHeaderThrow() throws IOException {
        assertThrows(FileNotFoundException.class, () -> Huffman.createHeader(Huffman.cntFreq("doesnotexist")));
    }

    @Test
    public void test01Textfile() throws IOException {
        Huffman.huffmanEncode("file1.txt", "file1_out.txt");
        assertTrue(compareFiles("file1_out.txt", "file1_soln.txt"));
    }
    @Test
    public void test02Textfile() throws IOException {
        Huffman.huffmanEncode("file2.txt", "file2_out.txt");
        assertTrue(compareFiles("file2_out.txt", "file2_soln.txt"));
    }
    @Test
    public void testMultTextfile() throws IOException {
        Huffman.huffmanEncode("multiline.txt", "mult_out.txt");
        assertTrue(compareFiles("mult_out.txt", "multiline_soln.txt"));
    }

    @Test
    public void pqueue(){
        PQueue example1 = PQueue.empty_queue();
        example1.enqueue(new HuffmanNode( 1,  4, null, null));
        example1.enqueue(new HuffmanNode( 1,  10, null, null));
        example1.enqueue(new HuffmanNode( 1,  16, null, null));
        example1.enqueue(new HuffmanNode( 1,  15, null, null));
        example1.enqueue(new HuffmanNode( 1,  1, null, null));
        example1.enqueue(new HuffmanNode( 1,  13, null, null));
        example1.enqueue(new HuffmanNode( 1,  12, null, null));
        example1.enqueue(new HuffmanNode( 1,  7, null, null));
        example1.enqueue(new HuffmanNode( 1,  9, null, null));
        example1.enqueue(new HuffmanNode( 1,  8, null, null));
        example1.enqueue(new HuffmanNode( 1,  14, null, null));
        example1.enqueue(new HuffmanNode( 1,  6, null, null));
        example1.enqueue(new HuffmanNode( 1,  5, null, null));
        example1.enqueue(new HuffmanNode( 1,  11, null, null));
        example1.enqueue(new HuffmanNode( 1,  3, null, null));
        example1.enqueue(new HuffmanNode( 1,  2, null, null));
        for (int i=1; i<16; i++){
            assertEquals(example1.dequeue().freq(), i);
        }
    }
    @Test
    public void decode() throws IOException {
        Huffman.huffmanEncode("beemovie.txt", "beemovie_out.txt");
        Huffman.huffmanDecode("beemovie_out.txt", "beemovie.out.out.txt");
        assertTrue(compareFiles("beemovie.txt", "beemovie.out.out.txt"));
    }
    @Test
    public void decode2() throws IOException {
        Huffman.huffmanEncode("a.txt", "a_out.txt");
        Huffman.huffmanDecode("a_out.txt", "a.out.out.txt");
        assertTrue(compareFiles("a.txt", "a.out.out.txt"));
    }
    /**
     * Compares two files line by line, trimming trailing CR/LF so the
     * comparison works the same on Windows and Unix-style line endings.
     */
    private static boolean compareFiles(String file1, String file2) throws IOException {
        try (BufferedReader r1 = new BufferedReader(new FileReader(file1));
             BufferedReader r2 = new BufferedReader(new FileReader(file2))) {
            String line1, line2;
            while (true) {
                line1 = r1.readLine();
                line2 = r2.readLine();
                if (line1 == null && line2 == null) {
                    return true;
                }
                if (line1 == null || line2 == null) {
                    return false;
                }
                if (!line1.trim().equals(line2.trim())) {
                    return false;
                }
            }
        }
    }
}