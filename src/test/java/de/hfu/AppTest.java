package de.hfu;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test für Standard App.
 */
public class AppTest {
    /**
     * Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void utilTest() {
        assertTrue(Util.istErstesHalbjahr(5));
        assertTrue(Util.istErstesHalbjahr(1));
        assertFalse(Util.istErstesHalbjahr(12));
        assertFalse(Util.istErstesHalbjahr(7));

        try {
            boolean ignored = Util.istErstesHalbjahr(0);
            fail();
        } catch (Exception ignored) {
        }

        try {
            boolean ignored = Util.istErstesHalbjahr(13);
            fail();
        } catch (Exception ignored) {
        }

    }

    @Test
    public void queueTest() {
        try {
            Queue q = new Queue(0);
            fail();
        } catch (Exception ignored) {
        }

        Queue queue = new Queue(3);

        queue.enqueue(2);

        assertEquals(2, queue.dequeue());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 2);
        assertEquals(queue.dequeue(), 4);

        try {
            queue.dequeue();
            fail();
        } catch (Exception ignored) {
        }

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);

        assertEquals(queue.dequeue(), 0);
        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 7);

    }

}
