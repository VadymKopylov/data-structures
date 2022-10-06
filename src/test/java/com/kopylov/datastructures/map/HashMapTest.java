package com.kopylov.datastructures.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapTest<K, V> {
    private final HashMap<String, Integer> map = new HashMap<>();


    @Test
    public void testAddValueWithKeyAndChangeSize() {
        map.put("A", 0);
        map.put("B", 1);
        map.put("C", 2);
        map.put("D", 3);
        map.put("E", 4);
        map.put("F", 5);
        map.put("G", 1);
        map.put("H", 2);

        assertEquals(8, map.size());
    }

    @Test
    public void whenAddValueWithNullKey_thenGetValueWithNullKey() {
        map.put(null, 0);

        assertEquals(0, map.get(null));
    }

    @Test
    public void whenValueWithNullKeyAddInTheMiddleOfTheList_thenGetValueWithNullKey() {
        map.put("A", 1);
        map.put(null, 0);
        map.put("F", 2);

        assertEquals(0, map.get(null));
    }

    @Test
    public void whenAddNullValue_thenGetThisValueByKey() {
        map.put("A", null);

        assertNull(map.get("A"));
    }

    @Test
    public void whenAddValuesInMap_thenReturnTrueAfterCallContainsKey() {
        map.put("B", 5);
        map.put("D", 6);
        assertTrue(map.containsKey("D"));
        assertTrue(map.containsKey("B"));
    }

    @Test
    public void whenNoValueWithThisKeyIsAdded_thenContainsReturnFalse() {
        map.put("A", 0);
        map.put("B", 1);
        assertFalse(map.containsKey("C"));
    }

    @Test
    public void whenRemoveValueFromMapWithNonExistentKey_thenReturnNull() {
        map.put("B", 1);
        assertNull(map.remove("A"));
    }

    @Test
    public void whenRemoveByNullKey_thenSizeShouldBeEqualZero() {
        map.put(null, 0);

        assertEquals(1, map.size());
        map.remove(null);
        assertEquals(0, map.size());

    }

    @Test
    public void whenRemoveValueFromMap_thenReturnTheValueOfTheKeyThatWasRemoved() {
        map.put("A", 0);
        map.put("B", 1);

        assertTrue(map.containsKey("A"));
        assertEquals(0, map.remove("A"));
        assertFalse(map.containsKey("A"));
    }

    @Test
    public void whenAddValuesWithSameKey_thenChangeOldValueToTheNewOne() {
        map.put("A", 0);
        map.put("A", 1);

        assertEquals(1, map.get("A"));
    }

    @Test
    public void testAddValueInOneBucketOnly() {
        map.put("A", 1);
        map.put("F", 2);
        map.put("K", 3);
        assertTrue(map.containsKey("A"));
        assertTrue(map.containsKey("F"));
        assertTrue(map.containsKey("K"));
    }

    @Test
    public void testAddValueToReplaceTheOldOneAndReturnThePreviousValue() {
        map.put("A", 0);
        map.put("B", 1);
        assertEquals(1, map.put("B", 10));
    }

    @Test
    public void whenAddValueInSecondBucketAndCallIterator_thenReturnTrueFromMethodHasNext() {
        map.put("B", 0);
        map.put("C", 1);
        map.put("D", 3);
        Iterator<HashMap.Entry<String, Integer>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenIteratorHasReturnedAllNextValues_thenThrowNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            map.put("A", 0);
            Iterator<HashMap.Entry<String, Integer>> iterator = map.iterator();
            assertTrue(iterator.hasNext());
            HashMap.Entry<String, Integer> entry = iterator.next();
            assertEquals(0, entry.getValue());
            assertEquals("A", entry.getKey());
            iterator.next();
        });
    }

    @Test
    public void whenTryRemoveOnEmptyMap_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            map.remove("A");
        });
    }

    @Test
    public void whenTryGetOnEmptyMap_thenNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            map.get("A");
        });
    }
}
