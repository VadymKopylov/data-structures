package com.kopylov.datastructures.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapTest<K, V> {
    private final HashMap<String, Integer> map = new HashMap<>();


    @Test
    public void testAddToMapValueWithKeyAndChangeSize() {
        map.put("A", 0);
        map.put("B", 1);
        map.put("C", 2);
        map.put("D", 3);
        map.put("E", 4);
        map.put("F", 5);
        map.put("G", 6);
        map.put("H", 7);

        assertEquals(8, map.size);
    }

    @Test
    public void testAddToMapTwoValuesWithTheSameKeyAndCheckTheCorrectnessOfTheEntry() {
        map.put("A", 0);
        map.put("A", 1);

        assertEquals(1, map.get("A"));

    }
    @Test
    public void testAddValueInOneBucketOnly(){
        map.put("A", 1);
        map.put("F", 2);
        map.put("K", 3);
        assertEquals(1,map.get("A"));
        assertEquals(2,map.get("F"));
        assertEquals(3,map.get("K"));
    }

    @Test
    public void testAddValueToMapToReplaceTheOldOneAndReturnThePreviousValue() {
        map.put("A", 0);
        map.put("B", 1);
        assertEquals(1, map.put("B", 10));
    }

    @Test
    public void testContainsKeyReturnTrueAfterAddValue() {
        map.put("B", 5);
        map.put("D", 6);
        map.put("E", 7);
        map.put("G", 8);
        assertTrue(map.containsKey("G"));
        assertTrue(map.containsKey("D"));
        assertTrue(map.containsKey("B"));
        assertTrue(map.containsKey("E"));
    }

    @Test
    public void testContainsReturnFalseIfNoValueWithThisKeyIsAdded() {
        map.put("A", 0);
        map.put("B", 1);
        assertFalse(map.containsKey("C"));
    }

    @Test
    public void testRemove() {
        map.put("A", 0);
        map.put("B", 1);
        assertTrue(map.containsKey("A"));
        map.remove("A");
        assertFalse(map.containsKey("A"));
    }

    @Test
    public void testNullPointerExceptionWhenTryRemoveOnEmptyList() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            map.remove("A");
        });
    }
}
