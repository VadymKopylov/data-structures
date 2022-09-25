package com.kopylov.datastructures.map;

import java.util.Iterator;

public interface Map<K, V> extends Iterable<Entry<K,V>> {
    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    V remove(K key);

    int size();

    default Iterator<Entry<K,V>> iterator() {
        throw new UnsupportedOperationException();
    }
}

