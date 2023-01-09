package com.kopylov.datastructures.list;

import java.util.StringJoiner;

public abstract class AbstractList<T> implements List<T> {
    protected int size;

    public void add(T value) {
        add(value, size);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    protected void validateIndexInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Only index from 0 (inclusive) to " + size + " (exclusive) is supported");
        }
    }
}
