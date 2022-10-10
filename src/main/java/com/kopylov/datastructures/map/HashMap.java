package com.kopylov.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_GROW_FACTOR = 2;
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    Entry<K, V>[] buckets = new Entry[DEFAULT_INITIAL_CAPACITY];

    private int size;

    @Override
    public V put(K key, V value) {
        return put(key, value, buckets);
    }

    private V put(K key, V value, Entry<K, V>[] buckets) {
        int hash = 0;
        if (isEmptyBucket(key, buckets)) {
            buckets[getIndex(key, buckets)] = addNewEntry(key, value);
        } else if (containsKey(key, buckets)) {
            int bucketIndex = getIndex(key, buckets);
            Entry<K, V> entry = buckets[bucketIndex];
            V result = entry.value;
            entry.value = value;
            return result;
        } else {
            Entry<K, V> entry = getEntry(key, buckets);
            if (entry.next != null) {
                while (entry.next != null && entry.hash != hash) {
                    entry = entry.next;
                }
            }
            entry.next = addNewEntry(key, value);
        }
        size++;
        if (buckets.length * DEFAULT_LOAD_FACTOR <= size) {
            ensureCapacity();
        }
        return null;
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        Entry<K, V> currentEntry = buckets[getIndex(key, buckets)];
        while (currentEntry.hash != hash) {
            currentEntry = currentEntry.next;
        }
        if (Objects.equals(currentEntry.key, key)) {
            return currentEntry.value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, buckets);
    }

    @Override
    public V remove(K key) {
        int hash = getHash(key);
        int index = getIndex(key, buckets);
        if (size == 0) {
            throw new NullPointerException();
        }
        Entry<K, V> entry = getEntry(key, buckets);
        if (entry == null) {
            return null;
        }
        if (entry.next != null) {
            while (entry.hash != hash) {
                entry = entry.next;
            }
            if (Objects.equals(entry.key, key)) {
                V result = entry.value;
                size--;
                return result;
            }
        } else {
            if (Objects.equals(entry.key, key)) {
                V result = entry.value;
                buckets[index] = null;
                size--;
                return result;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int bucketIndex;
            private int counterValue;
            private Entry<K, V> current = buckets[bucketIndex];

            @Override
            public boolean hasNext() {
                if (size == 0 || size == counterValue) {
                    return false;
                }
                if (buckets[bucketIndex] == null) {
                    while (buckets[bucketIndex] == null) {
                        bucketIndex++;
                    }
                    current = buckets[bucketIndex];
                    return true;
                }
                if (counterValue == 0) {
                    return true;
                }
                if (current.next != null) {
                    current = current.next;
                    return true;
                }
                current = buckets[++bucketIndex];
                return true;
            }

            @Override
            public Entry<K, V> next() {
                if (current == null) {
                    hasNext();
                }
                if (counterValue == size) {
                    throw new NoSuchElementException();
                }
                counterValue++;
                return current;
            }

            @Override
            public void remove() {
                if (current != null) {
                    HashMap.this.remove(current.key);
                    current = null;
                }
            }
        };
    }

    private boolean containsKey(K key, Entry<K, V>[] buckets) {
        Entry<K, V> entry = getEntry(key, buckets);
        int hash = getHash(key);
        if(entry == null){
            return false;
        }
        if (entry.next != null) {
            while (entry.hash != hash) {
                entry = entry.next;
            }
            if (Objects.equals(entry.key, key)) {
                return true;
            }
        }
        if(entry.next == null) {
            if (entry.hash == hash) {
                return Objects.equals(entry.key, key);
            }
        }
        return false;
    }

    private int getHash(K key) {
        if (key == null) {
            return 0;
        } else if (key.hashCode() == Integer.MIN_VALUE) {
            return Math.abs(key.hashCode() + 1);
        }
        return Math.abs(key.hashCode());
    }

    private int getIndex(K key, Entry<K, V>[] buckets) {
        return getHash(key) % buckets.length;
    }

    private Entry<K, V> getEntry(K key, Entry<K, V>[] buckets) {
        int entryIndex = getIndex(key, buckets);
        return buckets[entryIndex];
    }

    private boolean isEmptyBucket(K key, Entry<K, V>[] buckets) {
        return getEntry(key, buckets) == null;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        Entry<K, V>[] augmentedArray = new Entry[(buckets.length * DEFAULT_GROW_FACTOR)];
        shiftToNewBuckets(augmentedArray);
        buckets = augmentedArray;
    }

    private void shiftToNewBuckets(Entry<K, V>[] augmentedArray) {
        for (Entry<K, V> entry : this) {
            put(entry.key, entry.value, augmentedArray);
            size--;
        }
    }

    private Entry<K, V> addNewEntry(K key, V value) {
        int hash = getHash(key);
        Entry<K, V> next = null;
        return new Entry<>(key, value, hash, next);

    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private int hash;
        private Entry<K, V> next;

        private Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}
