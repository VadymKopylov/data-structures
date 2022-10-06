package com.kopylov.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Only index from 0 to " + size + " is supported");
        }
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> nodeToMove = getByIndex(index);
            Node<T> previous = nodeToMove.prev;
            nodeToMove.prev = newNode;
            newNode.next = nodeToMove;
            newNode.prev = previous;
            previous.next = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndexInBounds(index);
        Node<T> result = head;
        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            result = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> nodeToRemove = getByIndex(index);
            Node<T> previousNode = nodeToRemove.prev;
            Node<T> nextNode = nodeToRemove.next;
            previousNode.next = nextNode;
            nextNode.prev = previousNode;
            result = nodeToRemove;
        }
        size--;
        return result.value;
    }

    @Override
    public T get(int index) {
        validateIndexInBounds(index);
        Node<T> findByIndex = getByIndex(index);
        return findByIndex.value;
    }

    @Override
    public T set(T value, int index) {
        validateIndexInBounds(index);
        Node<T> current = getByIndex(index);
        T valueBeforeSet = current.value;
        current.value = value;
        return valueBeforeSet;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(current.value));
            current = current.next;
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;
            private boolean canRemove = false;
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (index >= size || isEmpty()) {
                    throw new NoSuchElementException();
                }
                T result = current.value;
                current = current.next;
                canRemove = true;
                index++;
                return result;
            }

            @Override
            public void remove() {
                if (canRemove) {
                    LinkedList.this.remove(index - 1);
                    canRemove = false;
                }

            }
        };
    }

    private void validateIndexInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Only index from 0 to " + (size - 1) + " is supported");
        }
    }

    private Node<T> getByIndex(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        } else if (index == 0) {
            return head;
        } else if (index == size - 1) {
            return tail;
        } else if (index <= size / 2) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else if (index > size / 2) {
            Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
        return null;
    }

    private static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private T value;

        private Node(T value) {
            this.value = value;
        }
    }

}
