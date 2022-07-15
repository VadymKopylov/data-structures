package com.kopylov.datastructures.list;

public class ArrayList implements List {
    private Object[] array;
    private int size;
    public ArrayList(){
        array = new Object[5];
    }
    @Override
    public void add(Object value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }
    @Override
    public void add(Object value, int index) {
        ensureCapacity();
        if(array[index-1] == null){
            throw new IndexOutOfBoundsException();
        }if(index <= size){
            Object[] updateArray = new Object[array.length];
            int count = 0;
            for(int i = 0;i < size+1; i++){
                if(index == i){
                    updateArray[i] = value;
                }else{
                    updateArray[i] = array[count];
                    count++;
                }

            }array = updateArray;

        }else{
            array[index] = value;
        }
        size++;
    }

    private void ensureCapacity(){
        if(array.length == size){
            Object[] ensureArray = new Object[array.length + (array.length / 2)];
            for(int i = 0;i < array.length;i++){
                ensureArray[i] = array[i];
            }
            array = ensureArray;
        }
    }

    @Override
    public Object remove(int index) {
        Object[] updateArray = new Object[array.length];
        int count = 0;
        for(int i = 0; i <= size;i ++){
            if(i == index){
                size--;
                continue;
            }else{
                updateArray[count] = array[i];
                count++;
            }
        }
        array = updateArray;
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
        for(int i = 0;i < size;i++){
            Object objectInArray = array[i];
            if(objectInArray.equals(value)){
                return true;
            }
        }
        return false;
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
