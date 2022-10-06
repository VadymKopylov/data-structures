package com.kopylov.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_GROW_FACTOR = 2;
    private static final int DEFAULT_INITIAL_CAPACITY = 5;

    @SuppressWarnings("unchecked")
    ArrayList<Entry<K, V>>[] buckets = new ArrayList[DEFAULT_INITIAL_CAPACITY];

    int size;

    @Override
    public V put(K key, V value) {
        return put(key, value, buckets);
    }

    private V put(K key, V value, ArrayList<Entry<K, V>>[] buckets) {
        if (containsKey(key, buckets)) {
            int bucketIndex = getIndex(key, buckets);
            List<Entry<K, V>> list = buckets[bucketIndex];
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    V result = entry.value;
                    entry.value = value;
                    return result;
                }
            }
        } else if (isEmptyBucket(key, buckets)) {
            List<Entry<K, V>> list = getNewBucket(key, buckets);
            list.add(new Entry<>(key, value));
        } else {
            List<Entry<K, V>> list = getBucket(key, buckets);
            list.add(new Entry<>(key, value));
        }
        size++;
        if (buckets.length * DEFAULT_LOAD_FACTOR <= size) {
            ensureCapacity();
        }
        return null;
    }

    @Override
    public V get(K key) {
        List<Entry<K, V>> list;
        if (key == null) {
            list = buckets[0];
        } else {
            list = getBucket(key, buckets);
        }
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (isEmptyBucket(key, buckets)) {
            return false;
        } else {
            List<Entry<K, V>> list = getBucket(key, buckets);
            for (Entry<K, V> entry : list) {
                if (Objects.equals(entry.key, key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        if (size == 0) {
            throw new NullPointerException();
        }
        List<Entry<K, V>> list = getBucket(key, buckets);
        if (list == null) {
            return null;
        }
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.key, key)) {
                V result = entry.value;
                list.remove(getIndex(key, buckets));
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
    public String toString() {
        return null;
    }

    private boolean containsKey(K key, ArrayList<Entry<K, V>>[] buckets) {
        if (isEmptyBucket(key, buckets)) {
            return false;
        } else {
            List<Entry<K, V>> list = getBucket(key, buckets);
            for (Entry<K, V> entry : list) {
                if (Objects.equals(entry.key, key)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getIndex(K key, ArrayList<Entry<K, V>>[] buckets) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() % buckets.length;
    }

    private List<Entry<K, V>> getBucket(K key, ArrayList<Entry<K, V>>[] buckets) {
        int bucketIndex = getIndex(key, buckets);
        return buckets[bucketIndex];
    }

    private List<Entry<K, V>> getNewBucket(K key, ArrayList<Entry<K, V>>[] buckets) {
        int bucketIndex = getIndex(key, buckets);
        buckets[bucketIndex] = new ArrayList<>();
        return buckets[bucketIndex];
    }

    private boolean isEmptyBucket(K key, ArrayList<Entry<K, V>>[] buckets) {
        return getBucket(key, buckets) == null;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        ArrayList<Entry<K, V>>[] augmentedArray = new ArrayList[(buckets.length * DEFAULT_GROW_FACTOR)];
        shiftToNewBuckets(augmentedArray);
        buckets = augmentedArray;
    }

    private void shiftToNewBuckets(ArrayList<Entry<K, V>>[] augmentedArray) {
        for (Entry<K, V> entry : this) {
            put(entry.key, entry.value, augmentedArray);
            size--;
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int bucketIndex;
            private Iterator<Entry<K, V>> listIterator = moveToNextBucket();
            private boolean canRemove = false;

            @Override
            public boolean hasNext() {
                if (listIterator.hasNext()) {
                    return true;
                }
                if (bucketIndex == buckets.length - 1) {
                    return false;
                }
                bucketIndex++;
                if (buckets[bucketIndex] == null) {
                    while (buckets[bucketIndex] == null) {
                        if (bucketIndex == buckets.length - 1) {
                            return false;
                        }
                        bucketIndex++;
                    }
                }
                listIterator = buckets[bucketIndex].iterator();
                return true;
            }

            private Iterator<Entry<K, V>> moveToNextBucket() {
                if (buckets[bucketIndex] == null) {
                    bucketIndex++;
                }
                return buckets[bucketIndex].iterator();
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                canRemove = true;
                return listIterator.next();
            }

            @Override
            public void remove() {
                if (canRemove) {
                    listIterator.remove();
                    canRemove = false;
                }
            }
        };
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
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
