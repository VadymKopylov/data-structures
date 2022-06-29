package com.kopylov.datastructures.stack;

public interface Stack {
    void push(Object value);

    Object pop();

    Object peak();

    boolean contains(Object value);

    boolean isEmpty();

    int size();

    void clear();
}
