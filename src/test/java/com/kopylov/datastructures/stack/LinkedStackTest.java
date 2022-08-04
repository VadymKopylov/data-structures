package com.kopylov.datastructures.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedStackTest {

    @Test
    public void testPushAndPopWorkCorrectlyAndChangeSize() {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("A");
        linkedStack.push("B");
        linkedStack.push("C");
        linkedStack.push("D");

        assertEquals(4, linkedStack.size());
        assertEquals("D",linkedStack.pop());
        assertEquals("C",linkedStack.pop());
        assertEquals("B", linkedStack.pop());
        assertEquals("A", linkedStack.pop());
        assertEquals(0, linkedStack.size());
        assertTrue(linkedStack.isEmpty());
    }

    @Test
    public void testPushAndPeek() {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("A");
        linkedStack.push("B");

        assertEquals(2, linkedStack.size());
        assertEquals("B", linkedStack.peak());
        assertEquals("B", linkedStack.peak());
        assertEquals(2, linkedStack.size());
    }

    @Test
    public void testIsEmptyReturnTrueOnNewStack() {
        LinkedStack linkedStack = new LinkedStack();

        assertTrue(linkedStack.isEmpty());
    }

    @Test
    public void testIsEmptyReturnFalseOnStackWithData() {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("A");

        assertFalse(linkedStack.isEmpty());
    }

    @Test
    public void testIsEmptyReturnTrueOnStackAfterClear() {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("B");
        linkedStack.push("A");
        linkedStack.clear();
        assertTrue(linkedStack.isEmpty());
    }

    @Test
    public void testContainsReturnTrue() {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("B");
        linkedStack.push("A");

        assertTrue(linkedStack.contains("A"));
    }

    @Test
    public void testContainsReturnFalse() {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("B");
        linkedStack.push("A");

        assertFalse(linkedStack.contains("C"));
    }

    @Test
    public void testContainsReturnFalseOnEmptyStack() {
        LinkedStack linkedStack = new LinkedStack();
        assertFalse(linkedStack.contains("C"));

    }

    @Test
    public void testThrowIllegalStateExceptionWhenPopOnEmptyState() {
        LinkedStack linkedStack = new LinkedStack();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            linkedStack.pop();
        });
    }
    @Test
    public void testStackWithValueToString(){
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("D");
        linkedStack.push("C");
        linkedStack.push("B");
        linkedStack.push("A");

        assertEquals("[A,B,C,D]",linkedStack.toString());
    }
}
