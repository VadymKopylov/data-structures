package com.kopylov.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedQueueTest {
        LinkedQueue linkedQueue = new LinkedQueue();

        @Test
        public void testQueueAndDequeueInArrayAndChangeSize(){
            linkedQueue.enqueue("1");
            linkedQueue.enqueue("2");
            linkedQueue.enqueue("3");
            assertEquals(3,linkedQueue.size());
            assertEquals("1",linkedQueue.dequeue());
            assertEquals("2",linkedQueue.dequeue());
            assertEquals("3",linkedQueue.dequeue());
            assertEquals(0,linkedQueue.size());
        }

        @Test
        public void testPeekWorkingCorrectly(){

            linkedQueue.enqueue("1");
            linkedQueue.enqueue("2");
            linkedQueue.enqueue("3");

            assertEquals(3,linkedQueue.size());
            assertEquals("1",linkedQueue.peak());
            assertEquals(3,linkedQueue.size());
        }

        @Test
        public void testThrowExceptionIfArrayDequeueOnEmptyState(){
            Assertions.assertThrows(IllegalStateException.class,() ->{
                linkedQueue.dequeue();
            });
        }

        @Test
        public void testReturnTrueIfArrayIsEmpty(){

            assertTrue(linkedQueue.isEmpty());
        }

        @Test
        public void testReturnTrueIfArrayContainsValue(){

            linkedQueue.enqueue("1");
            linkedQueue.enqueue("2");
            linkedQueue.enqueue("3");

            assertTrue(linkedQueue.contains("2"));
        }

        @Test
        public void testReturnFalseIfArrayNotContainsValue(){

            linkedQueue.enqueue("1");
            linkedQueue.enqueue("2");
            linkedQueue.enqueue("3");

            assertFalse(linkedQueue.contains("4"));
        }

        @Test
        public void testClearArrayAfterUse(){

            linkedQueue.enqueue("1");
            linkedQueue.enqueue("2");
            linkedQueue.enqueue("3");

            linkedQueue.clear();
            assertTrue(linkedQueue.isEmpty());
        }

        @Test
        public void testQueueWithValueToString(){
            linkedQueue.enqueue("1");
            linkedQueue.enqueue("2");
            linkedQueue.enqueue("3");

            assertEquals(3,linkedQueue.size());
            assertEquals("[1,2,3]",linkedQueue.toString());
        }
}
