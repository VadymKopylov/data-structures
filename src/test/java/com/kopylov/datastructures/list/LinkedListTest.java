package com.kopylov.datastructures.list;

public class LinkedListTest<T> extends AbstractListTest<T> {

    @Override
    List<Object> getList() {
        return new LinkedList<Object>();
    }
}