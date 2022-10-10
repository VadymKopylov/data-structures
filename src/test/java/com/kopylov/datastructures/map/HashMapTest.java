package com.kopylov.datastructures.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapTest<K, V> {
    private final HashMap<String, Integer> map = new HashMap<>();

    @DisplayName("Add values in map with default capacity and change size")
    @Test
    public void whenAddValuesInMapWithDefaultCapacity_thenChangeSize() {
        map.put("Alex", 0);
        map.put("Ben", 1);

        assertEquals(2, map.size());
    }

    @DisplayName("Add value and increase capacity")
    @Test
    public void whenAddValuesInMapAndIncreaseCapacity_thenChangeSizeAndCheckCorrectMovement() {
        map.put("Alex", 0);
        map.put("Ben", 2);
        map.put("Ford", 5);
        map.put("SomeName", 5);

        assertEquals(4, map.size());
        assertTrue(map.containsKey("Alex"));
        assertTrue(map.containsKey("Ben"));
        assertTrue(map.containsKey("Ford"));
        assertTrue(map.containsKey("SomeName"));
    }

    @DisplayName("Add value with null key and get this value")
    @Test
    public void whenAddValueWithNullKey_thenGetValueWithNullKey() {
        map.put(null, 0);

        assertEquals(0, map.get(null));
    }

    @DisplayName("Add value with null key in the middle and get this value ")
    @Test
    public void whenAddValueWithNullKeyAddInTheMiddleOfTheMap_thenGetValueWithNullKey() {
        map.put("A", 1);
        map.put(null, 0);
        map.put("F", 2);

        assertEquals(0, map.get(null));
    }

    @DisplayName("Add null value and get this value by key")
    @Test
    public void whenAddNullValue_thenGetThisValueByKey() {
        map.put("A", null);

        assertNull(map.get("A"));
    }

    @DisplayName("Add values in map and return true after call method ContainsKey")
    @Test
    public void whenAddValuesInMap_thenReturnTrueAfterCallContainsKey() {
        map.put("B", 5);
        map.put("D", 6);
        map.put("Alex", 0);
        map.put("Ben", 2);
        map.put("Ford", 5);
        map.put("SomeName", 5);

        assertTrue(map.containsKey("D"));
        assertTrue(map.containsKey("B"));
    }

    @DisplayName("Method ContainsKey return false if no value with this key is added")
    @Test
    public void whenNoValueWithThisKeyIsAdded_thenContainsReturnFalse() {
        map.put("A", 0);
        map.put("B", 1);

        assertFalse(map.containsKey("C"));
    }

    @DisplayName("Remove value with no existent key return null after call method remove")
    @Test
    public void whenRemoveValueFromMapWithNonExistentKey_thenReturnNull() {
        map.put("B", 1);

        assertNull(map.remove("A"));
    }

    @DisplayName("Remove value by null key and size should be equal zero ")
    @Test
    public void whenRemoveByNullKey_thenSizeShouldBeEqualZero() {
        map.put(null, 0);

        assertEquals(1, map.size());
        map.remove(null);
        assertEquals(0, map.size());

    }

    @DisplayName("Remove value from map and return value of the key that was removed")
    @Test
    public void whenRemoveValueFromMap_thenReturnTheValueOfTheKeyThatWasRemoved() {
        map.put("A", 0);
        map.put("B", 1);

        assertTrue(map.containsKey("A"));
        assertEquals(0, map.remove("A"));
        assertFalse(map.containsKey("A"));
    }

    @DisplayName("Add value with same key and change old value to the new one")
    @Test
    public void whenAddValuesWithSameKey_thenChangeOldValueToTheNewOne() {
        map.put("A", 0);
        map.put("A", 1);

        assertEquals(1, map.get("A"));
    }

    @DisplayName("Add v replace the old one and return the previous value")
    @Test
    public void testAddValueToReplaceTheOldOneAndReturnThePreviousValue() {
        map.put("A", 0);
        map.put("B", 1);

        assertEquals(1, map.put("B", 10));
    }

    @DisplayName("Add value then call iterators method hasNext and return true")
    @Test
    public void whenAddValueAndCallIterator_thenReturnTrueFromMethodHasNext() {
        map.put("A", 0);
        map.put("B", 1);
        Iterator<HashMap.Entry<String, Integer>> iterator = map.iterator();

        assertTrue(iterator.hasNext());
    }

    @DisplayName("When iterator has returned all value then throw NoSuchElementException")
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

    @DisplayName("Remove on empty map and throw NullPointerException")
    @Test
    public void whenRemoveOnEmptyMap_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            map.remove("A");
        });
    }

    @DisplayName("Get on empty map ant throw NullPointerException")
    @Test
    public void whenGetOnEmptyMap_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            map.get("A");
        });
    }
}
