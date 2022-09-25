package com.kopylov.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {

    private static double DEFAULT_LOAD_FACTOR = 0.75;
    private static int DEFAULT_GROW_FACTOR = 2;
    private static int DEFAULT_INITIAL_CAPACITY = 2;

    ArrayList<Entry<K, V>>[] buckets = new ArrayList[5];

    int size;

    @Override
    public V put(K key, V value) {
        return put(key, value, buckets);
    }

    private V put(K key, V value, ArrayList<Entry<K, V>>[] buckets) {
        ensureCapacity();
        if (containsKey(key)) {
            int bucketIndex = getIndex(key);
            List<Entry<K, V>> list = buckets[bucketIndex];
            for (Entry entry : list) {
                if (entry.key.equals(key)) {
                    V result = (V) entry.value;
                    entry.value = value;
                    return result;
                }
            }
        } else if (isEmptyBucket(key)) {
            List<Entry<K, V>> list = getNewBucket(key);
            list.add(new Entry<>(key, value));
        } else {
            List<Entry<K, V>> list = getBucket(key);
            list.add(new Entry<>(key, value));
        }
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        List<Entry<K, V>> list = getBucket(key);
        for (Entry entry : list) {
            if (entry.key.equals(key)) {
                V result = (V) entry.value;
                return result;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (isEmptyBucket(key)) {
            return false;
        } else {
            List<Entry<K, V>> list = getBucket(key);
            for (Entry entry : list) {
                if (Objects.equals(entry.key, key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        List<Entry<K, V>> list = getBucket(key);
        for (Entry entry : list) {
            if (Objects.equals(entry.key, key)) {
                V result = (V) entry.value;
                list.remove(getIndex(key));
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

    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    private List<Entry<K, V>> getBucket(K key) {
        int bucketIndex = getIndex(key);
        List<Entry<K, V>> list = buckets[bucketIndex];
        return list;
    }

    private List<Entry<K, V>> getNewBucket(K key) {
        int bucketIndex = getIndex(key);
        buckets[bucketIndex] = new ArrayList<>();
        List<Entry<K, V>> list = buckets[bucketIndex];
        return list;
    }

    private boolean isEmptyBucket(K key) {
        List<Entry<K, V>> list = getBucket(key);
        if (Objects.equals(list, null)) {
            return true;
        }
        return false;
    }

    private void ensureCapacity() {
        if (buckets.length * DEFAULT_LOAD_FACTOR <= size) {
            ArrayList<Entry<K, V>>[] augmentedArray = new ArrayList[(buckets.length * DEFAULT_GROW_FACTOR)];
            shiftToNewBuckets(augmentedArray);
            buckets = augmentedArray;

        }
    }

    private void shiftToNewBuckets(ArrayList<Entry<K,V>>[] augmentedArray) {
        for(int i = 0; i < buckets.length;i++){
            for(Entry<K,V> entry : this){
                Entry<K,V> entry = buckets[i].get(j);
                put(entry.key,entry.value, augmentedArray);
            }
        }
    }

    @Override
    public Iterator<Entry<K,V>> iterator() {
        Iterator<Entry<K,V>> newIterator = new Iterator<>() {
            private int bucketIndex;
            private Iterator<Entry<K, V>> listIterator = buckets[bucketIndex].iterator();

            @Override
            public boolean hasNext() {
                if (bucketIndex == buckets.length - 1) {
                    return false;
                }
                if (listIterator == null || !listIterator.hasNext()) {
                    while (buckets[bucketIndex].size() == 0) {
                        bucketIndex++;
                    }
                }
                if (listIterator.hasNext()) {
                    bucketIndex++;
                    return true;
                }
                return false;
            }

            @Override
            public Entry<K,V> next() {
                if (hasNext()) {
                    Entry<K, V> entry = listIterator.next();
                    return entry;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                listIterator.remove();
            }
        };
        return newIterator;
    }

    private class Entry<K, V> {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
