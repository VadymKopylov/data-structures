package com.kopylov.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> extends AbstractList<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;

    private T[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Insert index must be from 0 (inclusive) to " + size + "(inclusive)");
        }
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index to remove must be from 0 (inclusive) to " + size + "(exclusive)");
        }
        T result = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return result;
    }

    @Override
    public T get(int index) {
        validateIndexInBounds(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        validateIndexInBounds(index);
        T valueToReturn = array[index];
        array[index] = value;
        return valueToReturn;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            T objectInArray = array[i];
            if (Objects.equals(objectInArray, value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            T objectInArray = array[i];
            if (Objects.equals(objectInArray, value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int current;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if (current >= size || isEmpty()) {
                throw new NoSuchElementException();
            }
            T result = array[current];
            current++;
            canRemove = true;
            return result;
        }

        @Override
        public void remove() {
            if (canRemove) {
                ArrayList.this.remove(current - 1);
                canRemove = false;
            }
        }
    }

    private void ensureCapacity() {
        if (array.length == size) {
            T[] augmentedArray = (T[]) new Object[(int) (array.length * 1.5) + 1];
            System.arraycopy(array, 0, augmentedArray, 0, array.length);
            array = augmentedArray;
        }
    }
}
