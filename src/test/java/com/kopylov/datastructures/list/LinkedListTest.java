package com.kopylov.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    @Test
    public void testAddAndChangeSize(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        assertEquals(4,linkedList.size());
    }

    @Test
    public void testAddValueByLastIndexAndChangeSize(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D",3);

        assertEquals(4,linkedList.size());
    }

    @Test
    public void testAddValueByIndexChangeSizeAndGetValueByIndex(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("D");
        linkedList.add("C",2);

        assertEquals(4,linkedList.size());
        assertEquals("C",linkedList.get(2));
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryToAddValueByIndexThroughTheEmptySpace(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("D");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ linkedList.add("C",4);} );
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenAddValueByNegativeIndex(){
        LinkedList linkedList = new LinkedList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ linkedList.add("A",-1);} );
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryToRemoveValueByIndexLessByZero(){
        LinkedList linkedList = new LinkedList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ linkedList.remove(-1);} );
    }

    @Test
    public void testEnsureCapacity(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("D");
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("D");
        linkedList.add("D");
        linkedList.add("D");
        assertEquals(8,linkedList.size());

    }

    @Test
    public void testRemoveByIndexChangeSizeAndGetNewValueByZeroIndex(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals(3,linkedList.size());
        linkedList.remove(0);
        assertEquals(2,linkedList.size());
        assertEquals("C",linkedList.get(1));
    }

    @Test
    public void testGetByIndex(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        assertEquals("B",linkedList.get(1));
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryGetValueWithIndexOutOfRange(){
        LinkedList linkedList = new LinkedList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ linkedList.get(1);} );
    }

    @Test
    public void testSetValueByIndexAndGetThisValue(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.set("D",1);

        assertEquals("D",linkedList.get(1));
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTrySetValueWithIndexOutOfRange(){
        LinkedList linkedList = new LinkedList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ linkedList.set("A",0);} );
    }

    @Test
    public void testClearAndChangeSize(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        assertEquals(3,linkedList.size());
        linkedList.clear();
        assertEquals(0,linkedList.size());
    }

    @Test
    public void testIsEmptyReturnTrueAfterClear(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testContainsReturnTrueAfterAddValue(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        assertTrue(linkedList.contains("A"));
    }

    @Test
    public void testContainsReturnFalse(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        assertFalse(linkedList.contains("C"));
    }

    @Test
    public void testContainsReturnFalseAfterRemove(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.remove(0);
        assertFalse(linkedList.contains("A"));
    }

    @Test
    public void testIndexOf(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("A");
        linkedList.add("C");
        assertEquals(0,linkedList.indexOf("A"));
    }

    @Test
    public void testLastIndexOf(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("A");
        linkedList.add("C");
        assertEquals(2,linkedList.lastIndexOf("A"));
    }

    @DisplayName("test add value to array return correct toString")
    @Test
    public void testLinkedListToString(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        assertEquals("[A,B,C,D]",linkedList.toString());
    }
}
