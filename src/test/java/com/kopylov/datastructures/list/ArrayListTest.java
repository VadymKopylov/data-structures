package com.kopylov.datastructures.list;

public class ArrayListTest<T> extends AbstractListTest<T> {

    @Override
    List<Object> getList() {
        return new ArrayList<Object>();
    }
}
