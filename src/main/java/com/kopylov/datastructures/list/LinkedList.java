package com.kopylov.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Only index from 0 (inclusive) to " + size + " (inclusive) is supported");
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
            Node<T> nodeToMove = getNode(index);
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
        return remove(getNode(index));
    }

    @Override
    public T get(int index) {
        validateIndexInBounds(index);
        Node<T> findByIndex = getNode(index);
        return findByIndex.value;
    }

    @Override
    public T set(T value, int index) {
        validateIndexInBounds(index);
        Node<T> current = getNode(index);
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
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;
            private boolean canRemove = false;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                T result = current.value;
                current = current.next;
                canRemove = true;
                return result;
            }

            @Override
            public void remove() {
                if (canRemove) {
                    LinkedList.this.remove(current.prev);
                    canRemove = false;
                }
            }
        };
    }

    private T remove(Node<T> nodeToRemove) {
        Node<T> result = head;
        if (size == 1) {
            head = tail = null;
        } else if (nodeToRemove == head) {
            head = head.next;
            head.prev = null;
        } else if (nodeToRemove == tail) {
            result = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> previousNode = nodeToRemove.prev;
            Node<T> nextNode = nodeToRemove.next;
            previousNode.next = nextNode;
            nextNode.prev = previousNode;
            result = nodeToRemove;
        }
        size--;
        return result.value;
    }

    private Node<T> getNode(int index) {
        if (index <= size / 2) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
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
