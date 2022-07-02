package com.kopylov.datastructures.queue;

public class ArrayQueue implements Queue{
    private Object[] array;
    private int size;
    private int count;

    public ArrayQueue() {
        array = new Object[10];
    }

    @Override
    public void enqueue(Object value) {
        array[size] = value;
        size++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        Object result = array[0];
        for(int i = 0;i < size;i++){
            array[i] = array[i+1];
        }
        size--;
        return result;
    }

    @Override
    public Object peak() {
        return array[count];
    }

    @Override
    public boolean contains(Object value) {
        for(int i = 0;i < size;i++){
            Object arrayInQueue = array[i];
            if(arrayInQueue.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for(int i = 0;i < size;i++){
            array[i] = null;
        }
        size = 0;
    }
    @Override
    public String toString() {
        String result = "";
            for(int i = 0;i < size;i++){
                result = result + array[i];
                if(i < size-1){
                    result = result + ",";
                }
            }
            return "[" + result + "]";
    }
}
