package com.kopylov.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractListTest<T> {
    List<Object> list = getList();

    abstract List<Object> getList();

    @DisplayName("test Add Null Value Work Correctly")
    @Test
    public void testAddNullValueWorkCorrectly() {
        list.add(null);
        assertNull(list.get(0));
    }

    @DisplayName("test Add And Change Size")
    @Test
    public void testAddAndChangeSize() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals(4, list.size());
    }

    @DisplayName("test Add Value By Last Index And Change Size")
    @Test
    public void testAddValueByLastIndexAndChangeSize() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D", 3);

        assertEquals(4, list.size());
    }

    @DisplayName("test Add Value By Index In The Middle Of List And Change Size")
    @Test
    public void testAddValueByIndexInTheMiddleOfListAndChangeSize() {
        list.add("A");
        list.add("B");
        list.add("D");
        list.add("C", 2);

        assertEquals(4, list.size());
    }

    @DisplayName("test Add Value By Index And Check The Correctness Of Addiction")
    @Test
    public void testAddValueByIndexAndCheckTheCorrectnessOfAddiction() {
        list.add("A");
        list.add("B");
        list.add("D");
        list.add("C", 2);

        assertEquals("C", list.get(2));
    }

    @DisplayName("test Add Value And Check Work Correctly Ensure Capacity")
    @Test
    public void testAddValueAndCheckWorkCorrectlyEnsureCapacity() {
        list.add("A");
        list.add("B");
        list.add("D");
        list.add("A");
        list.add("B");
        list.add("D");
        list.add("D");
        list.add("D");
        assertEquals(8, list.size());
    }

    @DisplayName("test Throw Index Out Of Bound Exception When Try To Add Value By Index Through The Empty Space")
    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryToAddValueByIndexThroughTheEmptySpace() {
        list.add("A");
        list.add("B");
        list.add("D");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("C", 4);
        });
    }

    @DisplayName("test Throw Index Out Of Bound Exception When Add Value By Negative Index")
    @Test
    public void testThrowIndexOutOfBoundExceptionWhenAddValueByNegativeIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("A", -1);
        });
    }

    @DisplayName("test Throw Index Out Of Bound Exception When Try To Remove Value By Index Less By Zero")
    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryToRemoveValueByIndexLessByZero() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
    }

    @DisplayName("test Throw Index Out Of Bound Exception When Get Value From Empty List")
    @Test
    public void testThrowIndexOutOfBoundExceptionWhenGetValueFromEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });
    }

    @DisplayName("test Throw Index Out Of Bound Exception When Try Set Value Of An Empty List")
    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTrySetValueOfAnEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("A", 0);
        });
    }

    @DisplayName("test Remove By Index And Get New Value By ero Index")
    @Test
    public void testRemoveByIndexAndGetNewValueByZeroIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove(0);
        assertEquals("B", list.get(0));
    }

    @DisplayName("test Remove By Index And heck Value Which It Returns")
    @Test
    public void testRemoveByIndexAndCheckValueWhichItReturns() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.remove(1));
    }

    @DisplayName("test Remove By Index From List With One Value And Change Size")
    @Test
    public void testRemoveByIndexFromListWithOneValueAndChangeSize() {
        list.add("A");

        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }

    @DisplayName("test Get By Index")
    @Test
    public void testGetByIndex() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.get(1));
    }

    @DisplayName("test Set Value By Index And Return Old Value")
    @Test
    public void testSetValueByIndexAndReturnOldValue() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.set("D", 1));
    }

    @DisplayName("test Change Size After Clear")
    @Test
    public void testChangeSizeAfterClear() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @DisplayName("test IsEmpty Return True After Clear")
    @Test
    public void testIsEmptyReturnTrueAfterClear() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.clear();
        assertTrue(list.isEmpty());
    }

    @DisplayName("test Contains Return True After Add Value")
    @Test
    public void testContainsReturnTrueAfterAddValue() {
        list.add("A");
        list.add("B");
        assertTrue(list.contains("A"));
    }

    @DisplayName("test Contains Return False After Add")
    @Test
    public void testContainsReturnFalseAfterAdd() {
        list.add("A");
        list.add("B");
        assertFalse(list.contains("C"));
    }

    @DisplayName("test Contains Return False After Remove")
    @Test
    public void testContainsReturnFalseAfterRemove() {
        list.add("A");
        list.add("B");
        list.remove(0);
        assertFalse(list.contains("A"));
    }

    @DisplayName("test Contains Return True After Remove Duplicate Values")
    @Test
    public void testContainsReturnTrueAfterRemoveDuplicateValues() {
        list.add("B");
        list.add("B");
        list.remove(0);
        assertTrue(list.contains("B"));
    }

    @DisplayName("test IndexOf From Start")
    @Test
    public void testIndexOfFromStart() {
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");
        assertEquals(0, list.indexOf("A"));
    }

    @DisplayName("test IndexOf Work Correctly If List Have One Value")
    @Test
    public void testIndexOfWorkCorrectlyIfListHaveOneValue() {
        list.add("A");

        assertEquals(0, list.indexOf("A"));
    }

    @DisplayName("test Last IndexOf Work Correctly If List Have One Value")
    @Test
    public void testLastIndexOfWorkCorrectlyIfListHaveOneValue() {
        list.add("A");

        assertEquals(0, list.lastIndexOf("A"));
    }

    @DisplayName("test Last IndexOf From The End")
    @Test
    public void testLastIndexOfFromTheEnd() {
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");
        assertEquals(2, list.lastIndexOf("A"));
    }

    @DisplayName("test Last IndexOf From Empty List")
    @Test
    public void testLastIndexOfFromEmptyList() {
        assertEquals(-1, list.lastIndexOf("A"));
    }

    @DisplayName("test add value to array return correct toString")
    @Test
    public void testCorrectnessOfInformationOutput() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        assertEquals("[A,B,C,D]", list.toString());
    }

    @DisplayName("test Correctness Of Information Output If Value Is Null")
    @Test
    public void testCorrectnessOfInformationOutputIfValueIsNull() {
        list.add(null);
        list.add(null);
        list.add(null);

        assertEquals("[null,null,null]", list.toString());
    }

    @DisplayName("test Iterator Work Correctly")
    @Test
    public void testIteratorWorkCorrectly() {
        list.add("A");
        list.add("B");
        Iterator<Object> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(2,list.size());
        iterator.remove();
        assertEquals(1,list.size());

    }
}
