package com.kopylov.datastructures.list;

import java.util.StringJoiner;

public class ArrayList implements List {
    private Object[] array;
    private int size;
    public ArrayList(){
        array = new Object[5];
    }

    @Override
    public void add(Object value) {
        ensureCapacity();
        add(value,size);
    }

    @Override
    public void add(Object value, int index) {
        ensureCapacity();
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Index is larger than size of the Array List( "+ size +" )");
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public Object remove(int index) {
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Index is out of range than size of the Array List( "+ size +" )");
        }
        System.arraycopy(array,index + 1,array,index,size - index - 1);
        array[size - 1] = null;
        size--;
        return array;
    }

    @Override
    public Object get(int index) {
        if(index > size-1 || index < 0){
            throw new IndexOutOfBoundsException("Value with this index is null");
        }
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        if(index > size-1 || index < 0){
            throw new IndexOutOfBoundsException("Value with this index is null");
        }
        array[index] = value;
        return array;
    }

    @Override
    public void clear() {
        for(int i = 0;i < size;i++){
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        for(int i = 0;i < size-1;i++){
            Object objectInArray = array[i];
            if(objectInArray.equals(value)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for(int i = size-1;i >= 0;i--){
            Object objectInArray = array[i];
            if(objectInArray.equals(value)){
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity(){
        if(array.length == size){
            Object[] ensureArray = new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, ensureArray, 0, array.length);
            array = ensureArray;
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",","[","]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(array[i].toString());
        }
        return stringJoiner.toString();
    }
}
