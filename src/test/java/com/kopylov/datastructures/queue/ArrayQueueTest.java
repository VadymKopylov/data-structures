package com.kopylov.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {
    ArrayQueue arrayQueue = new ArrayQueue();

    @Test
    public void testQueueAndDequeueInArrayAndChangeSize(){
        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");
        assertEquals(3,arrayQueue.size());
        assertEquals("1",arrayQueue.dequeue());
        assertEquals("2",arrayQueue.dequeue());
        assertEquals("3",arrayQueue.dequeue());
        assertEquals(0,arrayQueue.size());
    }

    @Test
    public void testPeekWorkingCorrectly(){

        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");

        assertEquals(3,arrayQueue.size());
        assertEquals("1",arrayQueue.peak());
        assertEquals(3,arrayQueue.size());
    }

    @Test
    public void testThrowExceptionIfArrayDequeueOnEmptyState(){
        Assertions.assertThrows(IllegalStateException.class,() ->{
            arrayQueue.dequeue();
        });
    }

    @Test
    public void testReturnTrueIfArrayIsEmpty(){

        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testReturnTrueIfArrayContainsValue(){

        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");

        assertTrue(arrayQueue.contains("2"));
    }

    @Test
    public void testReturnFalseIfArrayNotContainsValue(){

        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");

        assertFalse(arrayQueue.contains("4"));
    }

    @Test
    public void testClearArrayAfterUse(){

        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");

        arrayQueue.clear();
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testQueueWithValueToString(){
        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");

        assertEquals(3,arrayQueue.size());
        assertEquals("[1,2,3]",arrayQueue.toString());
    }
}
