package com.kopylov.datastructures.list;

import java.util.Iterator;

public interface List<T> extends Iterable<T> {
    void add(T value);

    void add(T value, int index);

    T remove(int index);

    T get(int index);

    T set(T value, int index);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(T value);

    int indexOf(T value);

    int lastIndexOf(T value);

    String toString();

    Iterator<T> iterator();
}
