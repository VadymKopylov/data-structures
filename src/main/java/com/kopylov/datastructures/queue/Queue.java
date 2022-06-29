package com.kopylov.datastructures.queue;

public interface Queue {
    void enqueue(Object value);

    Object dequeue();

    Object peak();

    boolean contains(Object value);

    boolean isEmpty();

    int size();

    void clear();

    String toString();
}
