package com.kopylov.datastructures.list;

public interface List {
    void add(Object value);

    void add(Object value, int index);

    Object remove(int index);

    Object get(int index);

    Object set(Object value, int index);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(Object value);

    int indexOf(Object value);

    int lastIndexOf(Object value);

    String toString();


}
