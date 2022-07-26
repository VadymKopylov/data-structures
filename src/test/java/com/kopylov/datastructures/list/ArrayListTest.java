package com.kopylov.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    @Test
    public void testAddToArrayAndChangeSize(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals(4,arrayList.size());
    }

    @Test
    public void testAddValueByLastIndexAndChangeSize(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D",3);

        assertEquals(4,arrayList.size());
    }

    @Test
    public void testAddValueByIndexChangeSizeAndGetValueByIndex(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("D");
        arrayList.add("C",2);

        assertEquals(4,arrayList.size());
        assertEquals("C",arrayList.get(2));
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryToAddValueByIndexThroughTheEmptySpace(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("D");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ arrayList.add("C",4);} );
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenAddValueByNegativeIndex(){
        ArrayList arrayList = new ArrayList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ arrayList.add("A",-1);} );
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryToRemoveValueByIndexLessByZero(){
        ArrayList arrayList = new ArrayList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ arrayList.remove(-1);} );
    }

    @Test
    public void testEnsureCapacity(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("D");
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("D");
        arrayList.add("D");
        arrayList.add("D");
        assertEquals(8,arrayList.size());

    }

    @Test
    public void testRemoveByIndexChangeSizeAndGetNewValueByZeroIndex(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals(3,arrayList.size());
        arrayList.remove(0);
        assertEquals(2,arrayList.size());
        assertEquals("C",arrayList.get(1));
    }

    @Test
    public void testGetByIndex(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        assertEquals("B",arrayList.get(1));
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTryGetValueWithIndexOutOfRange(){
        ArrayList arrayList = new ArrayList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ arrayList.get(1);} );
    }

    @Test
    public void testSetValueByIndexAndGetThisValue(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.set("D",1);

        assertEquals("D",arrayList.get(1));
    }

    @Test
    public void testThrowIndexOutOfBoundExceptionWhenTrySetValueWithIndexOutOfRange(){
        ArrayList arrayList = new ArrayList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{ arrayList.set("A",0);} );
    }

    @Test
    public void testClearAndChangeSize(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        assertEquals(3,arrayList.size());
        arrayList.clear();
        assertEquals(0,arrayList.size());
    }

    @Test
    public void testIsEmptyReturnTrueAfterClear(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testContainsReturnTrueAfterAddValue(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        assertTrue(arrayList.contains("A"));
    }

    @Test
    public void testContainsReturnFalse(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        assertFalse(arrayList.contains("C"));
    }

    @Test
    public void testContainsReturnFalseAfterRemove(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.remove(0);
        assertFalse(arrayList.contains("A"));
    }

    @Test
    public void testIndexOf(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("A");
        arrayList.add("C");
        assertEquals(0,arrayList.indexOf("A"));
    }

    @Test
    public void testLastIndexOf(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("A");
        arrayList.add("C");
        assertEquals(2,arrayList.lastIndexOf("A"));
    }

    @DisplayName("test add value to array return correct toString")
    @Test
    public void testArrayListToString(){
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        assertEquals("[A,B,C,D]",arrayList.toString());
    }
}
