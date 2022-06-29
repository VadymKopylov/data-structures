package com.kopylov.datastructures.stack;

public class ArrayStack implements Stack{
    private int size;
    private Object[] array;

    public ArrayStack() {
        array = new Object[10];
    }

    @Override
    public void push(Object value) {
        array[size] = value;
        size++;
    }

    @Override
    public Object pop() {
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        Object result = array[size - 1];
        size--;
        return result;
    }

    @Override
    public Object peak() {
        return array[size - 1];
    }

    @Override
    public boolean contains(Object value) {
        for(int i = 0; i < size;i++){
            Object arrayInStack = array[i];
            if(value.equals(arrayInStack)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
    }
}
